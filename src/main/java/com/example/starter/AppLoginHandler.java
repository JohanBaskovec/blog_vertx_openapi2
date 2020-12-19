package com.example.starter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.impl.NoStackTraceThrowable;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authentication.Credentials;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.auth.authorization.PermissionBasedAuthorization;
import io.vertx.ext.auth.authorization.RoleBasedAuthorization;
import org.openapitools.vertxweb.server.model.Authorization;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.AuthenticationHandlerImpl;
import io.vertx.ext.web.handler.impl.HttpStatusException;
import io.vertx.ext.web.impl.RoutingContextInternal;
import org.openapitools.vertxweb.server.model.PermissionAuthorization;
import org.openapitools.vertxweb.server.model.RoleAuthorization;
import org.openapitools.vertxweb.server.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AppLoginHandler extends AuthenticationHandlerImpl<AppAuthenticationProvider> {
  static final HttpStatusException UNAUTHORIZED = new HttpStatusException(401);
  static final HttpStatusException BAD_REQUEST = new HttpStatusException(400);
  static final HttpStatusException BAD_METHOD = new HttpStatusException(405);
  private final AppAuthorizationProvider authorizationProvider;

  public AppLoginHandler(AppAuthenticationProvider authProvider, AppAuthorizationProvider authorizationProvider) {
    super(authProvider);
    this.authorizationProvider = authorizationProvider;
  }

  @Override
  public void parseCredentials(RoutingContext context, Handler<AsyncResult<Credentials>> handler) {
    HttpServerRequest req = context.request();
    if (req.method() != HttpMethod.POST) {
      handler.handle(Future.failedFuture(BAD_METHOD)); // Must be a POST
    } else {
      if (!((RoutingContextInternal) context).seenHandler(RoutingContextInternal.BODY_HANDLER)) {
        handler.handle(Future.failedFuture(new NoStackTraceThrowable("BodyHandler is required to process POST requests")));
      } else {
        JsonObject body = context.getBodyAsJson();
        if (body == null) {
          handler.handle(Future.failedFuture(BAD_REQUEST));
        } else {
          String username = body.getString("username");
          String password = body.getString("password");
          if (username == null || password == null) {
            handler.handle(Future.failedFuture(BAD_REQUEST));
          } else {
            handler.handle(Future.succeededFuture(new UsernamePasswordCredentials(username, password)));
          }
        }
      }
    }
  }


  @Override
  public void postAuthentication(RoutingContext ctx) {
    try {
      AppUser sessionUser = (AppUser) ctx.user();
      authorizationProvider.getAuthorizations(sessionUser)
        .compose((Void result) -> UserService.getAuthenticatedUser(ctx))
        .onSuccess(ctx::json)
        .onFailure(ctx::fail);
    } catch (Throwable t) {
      ctx.fail(t);
    }
  }
}
