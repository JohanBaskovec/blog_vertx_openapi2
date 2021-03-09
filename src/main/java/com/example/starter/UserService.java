package com.example.starter;

import com.example.starter.db.UserMapper;
import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.HttpStatusException;
import io.vertx.pgclient.PgPool;
import jooq.tables.daos.DbBlogUserDao;
import jooq.tables.pojos.DbBlogUser;
import org.jooq.Configuration;
import org.openapitools.vertxweb.server.model.RoleAuthorization;
import org.openapitools.vertxweb.server.model.User;

import java.util.List;

public class UserService {
  final private Configuration jooqConfiguration;
  final private PgPool sqlClient;
  final private UserMapper userMapper = new UserMapper();
  final private AuthorizationService authorizationService;

  public UserService(Configuration jooqConfiguration, PgPool sqlClient, AuthorizationService authorizationService) {
    this.jooqConfiguration = jooqConfiguration;
    this.sqlClient = sqlClient;
    this.authorizationService = authorizationService;
  }

  public Future<DbBlogUserWithPermissions> getAuthenticatedUser(RoutingContext context) {
    try {
      SessionUser sessionUser = (SessionUser) context.user();
      return this.getUserByUsername(sessionUser.getUsername());
    } catch (Throwable t) {
      return Future.failedFuture(t);
    }
  }

  public Future<DbBlogUserWithPermissions> getUserByUsername(String username) {
    DbBlogUserDao dao = new DbBlogUserDao(jooqConfiguration, sqlClient);
    Holder<DbBlogUserWithPermissions> userHolder = new Holder<>();
    return dao.findOneById(username)
      .compose((DbBlogUser dbBlogUser) -> {
        if (dbBlogUser == null) {
          throw new HttpStatusException(404);
        }
        User user = userMapper.fromDb(dbBlogUser);
        userHolder.value = new DbBlogUserWithPermissions(dbBlogUser);

        return authorizationService.getAuthorizationsOfUser(jooqConfiguration, sqlClient, user.getUsername());
      })
      .compose((List<RoleAuthorization> authorizations) -> {
        userHolder.value.setAuthorizations(authorizations);
        return Future.succeededFuture(userHolder.value);
      });
  }

}
