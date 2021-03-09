package com.example.starter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.HashingStrategy;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.AuthenticationProvider;
import io.vertx.ext.auth.authentication.CredentialValidationException;
import io.vertx.ext.auth.authentication.Credentials;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.auth.authorization.Authorizations;
import io.vertx.ext.auth.authorization.PermissionBasedAuthorization;
import io.vertx.ext.auth.authorization.RoleBasedAuthorization;
import jooq.tables.daos.DbBlogUserDao;
import jooq.tables.pojos.DbBlogUser;
import org.openapitools.vertxweb.server.model.PermissionAuthorization;
import org.openapitools.vertxweb.server.model.RoleAuthorization;

public class AppAuthenticationProvider implements AuthenticationProvider {
  private final HashingStrategy strategy = HashingStrategy.load();
  private final UserService userService;

  public AppAuthenticationProvider(
    UserService userService
  ) {
    this.userService = userService;
  }

  @Override
  public void authenticate(JsonObject credentials, Handler<AsyncResult<User>> resultHandler) {
    authenticate(new UsernamePasswordCredentials(credentials), resultHandler);
  }

  @Override
  public void authenticate(Credentials credentials, Handler<AsyncResult<User>> resultHandler) {
    try {
      UsernamePasswordCredentials authInfo = (UsernamePasswordCredentials) credentials;
      authInfo.checkValid(null);

      userService.getUserByUsername(authInfo.getUsername())
        .onSuccess((DbBlogUserWithPermissions dbUser) -> {
          if (dbUser == null) {
            // Unknown user/password
            resultHandler.handle(Future.failedFuture("Invalid username/password"));
          } else {
            if (strategy.verify(dbUser.getPassword(), authInfo.getPassword())) {
              resultHandler.handle(Future.succeededFuture(dbUserToSessionUser(dbUser)));
            } else {
              resultHandler.handle(Future.failedFuture("Invalid username/password"));
            }
          }
        })
        .onFailure(t -> resultHandler.handle(Future.failedFuture(t)));
    } catch (ClassCastException | CredentialValidationException e) {
      resultHandler.handle(Future.failedFuture(e));
    }
  }

  public SessionUser dbUserToSessionUser(DbBlogUserWithPermissions dbUser) {
    SessionUser sessionUser = new SessionUser(dbUser.getUsername());
    Authorizations sessionUserAuthorizations = sessionUser.authorizations();
    for (RoleAuthorization roleAuthorization : dbUser.getAuthorizations()) {
      RoleBasedAuthorization vertxRole = RoleBasedAuthorization.create(roleAuthorization.getRole());
      sessionUserAuthorizations.add(roleAuthorization.getProviderId(), vertxRole);
      for (PermissionAuthorization permissionAuthorization : roleAuthorization.getPermissions()) {
        PermissionBasedAuthorization vertxPermission = PermissionBasedAuthorization.create(permissionAuthorization.getPermission());
        sessionUserAuthorizations.add(permissionAuthorization.getProviderId(), vertxPermission);
      }
    }
    return sessionUser;
  }
}
