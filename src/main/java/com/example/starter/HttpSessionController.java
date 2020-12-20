package com.example.starter;

import io.vertx.ext.web.RoutingContext;
import io.vertx.pgclient.PgPool;
import org.jooq.Configuration;

public class HttpSessionController {
  final private Configuration configuration;
  final private PgPool pool;
  final private UserService userService;

  public HttpSessionController(Configuration configuration, PgPool pool, UserService userService) {
    this.configuration = configuration;
    this.pool = pool;
    this.userService = userService;
  }

  public void getCurrentAuthenticatedUser(RoutingContext routingContext) {
    try {
        userService.getAuthenticatedUser(routingContext)
        .onSuccess(routingContext::json)
        .onFailure(routingContext::fail);
    } catch (Throwable t) {
      routingContext.fail(t);
    }
  }

  public void logout(RoutingContext routingContext) {
    routingContext.session().destroy();
    routingContext.response().setStatusCode(200).end();
  }
}
