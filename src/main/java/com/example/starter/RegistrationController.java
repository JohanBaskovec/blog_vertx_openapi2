package com.example.starter;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.HashingStrategy;
import io.vertx.ext.auth.VertxContextPRNG;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import io.vertx.pgclient.PgPool;
import jooq.tables.daos.DbBlogUserDao;
import jooq.tables.daos.DbUserRolesDao;
import jooq.tables.pojos.DbBlogUser;
import jooq.tables.pojos.DbUserRoles;
import org.jooq.Configuration;
import org.openapitools.vertxweb.server.model.RegistrationForm;

import java.util.Base64;

public class RegistrationController {
  final private Configuration configuration;
  final private PgPool pool;

  public RegistrationController(Configuration configuration, PgPool pool) {
    this.configuration = configuration;
    this.pool = pool;
  }

  public void register(RoutingContext routingContext) {
    RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
    JsonObject body = requestParameters.body().getJsonObject();
    RegistrationForm registrationForm = body.mapTo(RegistrationForm.class);
    HashingStrategy strategy = HashingStrategy.load();
    byte saltBytes[] = new byte[32];
    VertxContextPRNG.current().nextBytes(saltBytes);
    String salt = Base64.getMimeEncoder().encodeToString(saltBytes);

    String passwordHash = strategy.hash(
      "pbkdf2", // hashing algorithm (OWASP recommended)
      null,
      salt,
      registrationForm.getPassword());
    DbBlogUser dbBlogUser = new DbBlogUser();
    dbBlogUser.setPassword(passwordHash);
    dbBlogUser.setPasswordSalt(salt);
    dbBlogUser.setUsername(registrationForm.getUsername());

    pool.withTransaction(connection -> {
      DbBlogUserDao userDao = new DbBlogUserDao(configuration, connection);
      return userDao.insert(dbBlogUser)
        .compose((Integer result) -> {
          DbUserRolesDao userRolesDao = new DbUserRolesDao(configuration, connection);
          DbUserRoles dbUserRoles = new DbUserRoles(dbBlogUser.getUsername(), "user");
          return userRolesDao.insert(dbUserRoles);
        });
    })
      .onSuccess((Integer result) -> routingContext.response()
        .setStatusCode(204).end()).onFailure(routingContext::fail);
  }
}
