package com.example.starter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ext.auth.authentication.Credentials;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.AuthenticationHandlerImpl;
import io.vertx.ext.web.handler.impl.HttpStatusException;

public class AppAuthenticationHandler extends AuthenticationHandlerImpl<AppAuthenticationProvider> {
  public AppAuthenticationHandler(AppAuthenticationProvider authProvider) {
    super(authProvider);
  }

  @Override
  public void parseCredentials(RoutingContext context, Handler<AsyncResult<Credentials>> handler) {
    handler.handle(Future.failedFuture(new HttpStatusException(401)));
  }
}
