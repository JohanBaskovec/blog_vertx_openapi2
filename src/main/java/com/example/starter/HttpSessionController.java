package com.example.starter;

import io.vertx.ext.web.RoutingContext;
import io.vertx.pgclient.PgPool;
import jooq.tables.daos.DbUserDao;
import org.jooq.Configuration;
import org.openapitools.vertxweb.server.model.User;

public class HttpSessionController {
  final private Configuration configuration;
  final private PgPool pool;

  public HttpSessionController(Configuration configuration, PgPool pool) {
    this.configuration = configuration;
    this.pool = pool;
  }

  public void getCurrentAuthenticatedUser(RoutingContext routingContext) {
    AppUser sessionUser = (AppUser) routingContext.user();
    DbUserDao dbUserDao = new DbUserDao(configuration, pool);
    dbUserDao.findOneById(sessionUser.principal().getString("username"))
      .onSuccess(dbUser -> {
        User user = new User();
        user.setUsername(dbUser.getUsername());
        routingContext.json(user);
      }).onFailure(t -> routingContext.fail(t));
  }

  public void logout(RoutingContext routingContext) {
    routingContext.session().destroy();
    routingContext.response().setStatusCode(200).end();
  }
}
