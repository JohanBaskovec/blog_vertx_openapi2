package com.example.starter;

import io.vertx.ext.web.RoutingContext;
import io.vertx.pgclient.PgPool;
import jooq.tables.daos.DbBlogUserDao;
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
    DbBlogUserDao dbBlogUserDao = new DbBlogUserDao(configuration, pool);
    dbBlogUserDao.findOneById(sessionUser.principal().getString("username"))
      .onSuccess(dbBlogUser -> {
        User user = new User();
        user.setUsername(dbBlogUser.getUsername());
        routingContext.json(user);
      }).onFailure(t -> routingContext.fail(t));
  }

  public void logout(RoutingContext routingContext) {
    routingContext.session().destroy();
    routingContext.response().setStatusCode(200).end();
  }
}
