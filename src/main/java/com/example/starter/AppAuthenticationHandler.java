package com.example.starter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authentication.Credentials;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.AuthenticationHandlerImpl;
import io.vertx.ext.web.handler.impl.HttpStatusException;
import io.vertx.pgclient.PgPool;
import jooq.tables.daos.DbBlogUserDao;
import org.jooq.Configuration;

import java.util.Date;

public class AppAuthenticationHandler extends AuthenticationHandlerImpl<AppAuthenticationProvider> {
  final private Configuration jooqConfiguration;
  final private PgPool pool;
  public AppAuthenticationHandler(AppAuthenticationProvider authProvider, Configuration jooqConfiguration, PgPool pool) {
    super(authProvider);
    this.jooqConfiguration = jooqConfiguration;
    this.pool = pool;
  }

  @Override
  public void parseCredentials(RoutingContext context, Handler<AsyncResult<Credentials>> handler) {
    handler.handle(Future.failedFuture(new HttpStatusException(401)));
  }

  @Override
  public void postAuthentication(RoutingContext ctx) {
    // When updating the user in the database,
    // it isn't updated in the session, so we do it here.
    // The user is kept in the session for one minute. On the
    // next request that requires authentication, we recreate the user.

    // TODO:
    // The user is kept in the session for one minute. After one
    // minute, we check if the user has been modified in the database.
    // If it's been modified, or its role/permissions have been modified,
    // we fetch it and recreate the User in the session.
    // (we need to add a lastModified column on user, and update it when its
    // roles or role's permissions have been modified too)

    AppUser user = (AppUser) ctx.user();
    JsonObject userAttributes = user.attributes();
    Long lastFetch = userAttributes.getLong("lastFetch");
    long now = new Date().getTime();
    if (now - lastFetch > 60 * 1000) {
      DbBlogUserDao userDao = new DbBlogUserDao(jooqConfiguration, pool);
      userDao.findOneById(user.getUsername()).onSuccess(userInDb -> {
        if (userInDb == null) {
          // User has been deleted
          ctx.session().destroy();
          ctx.response().setStatusCode(401).end();
        } else {
          ctx.setUser(new AppUser(userInDb.getUsername()));
        }
        ctx.next();
      });
      userAttributes.put("lastFetch", now);
    } else {
      ctx.next();
    }
  }
}
