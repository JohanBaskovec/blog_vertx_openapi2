package com.example.starter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.HashingStrategy;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.AuthenticationProvider;
import io.vertx.ext.auth.authentication.CredentialValidationException;
import io.vertx.ext.auth.authentication.Credentials;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import jooq.tables.daos.DbUserDao;
import jooq.tables.pojos.DbUser;

public class AppAuthenticationProvider implements AuthenticationProvider {
  private final DbUserDao userDao;
  private final HashingStrategy strategy = HashingStrategy.load();

  public AppAuthenticationProvider(DbUserDao userDao) {
    this.userDao = userDao;
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

      Future<DbUser> user$ = userDao.findOneById(authInfo.getUsername());
      user$.onSuccess(user -> {
        if (user == null) {
          // Unknown user/password
          resultHandler.handle(Future.failedFuture("Invalid username/password"));
        } else {
          if (strategy.verify(user.getPassword(), authInfo.getPassword())) {
            resultHandler.handle(Future.succeededFuture(new AppUser(authInfo.getUsername())));
          } else {
            resultHandler.handle(Future.failedFuture("Invalid username/password"));
          }
        }
      }).onFailure(t -> resultHandler.handle(Future.failedFuture(t)));
    } catch (ClassCastException | CredentialValidationException e) {
      resultHandler.handle(Future.failedFuture(e));
    }
  }
}
