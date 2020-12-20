package com.example.starter;

import com.example.starter.db.UserMapper;
import io.vertx.core.Future;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.HttpStatusException;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import io.vertx.pgclient.PgPool;
import jooq.tables.daos.DbBlogUserDao;
import jooq.tables.daos.DbRoleDao;
import jooq.tables.daos.DbRolesPermissionsDao;
import jooq.tables.daos.DbUserRolesDao;
import jooq.tables.pojos.DbBlogUser;
import jooq.tables.pojos.DbRole;
import jooq.tables.pojos.DbRolesPermissions;
import jooq.tables.pojos.DbUserRoles;
import org.jooq.Configuration;
import org.openapitools.vertxweb.server.model.Authorization;
import org.openapitools.vertxweb.server.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserController {
  final private Configuration configuration;
  final private PgPool pool;
  final private UserMapper userMapper = new UserMapper();
  final private AuthorizationService authorizationService;

  public UserController(Configuration configuration, PgPool pool, AuthorizationService authorizationService) {
    this.configuration = configuration;
    this.pool = pool;
    this.authorizationService = authorizationService;
  }

  public void getUserByUsername(RoutingContext routingContext) {
    DbBlogUserDao dao = new DbBlogUserDao(configuration, pool);
    RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
    String id = requestParameters.pathParameter("username").getString();
    Holder<User> userHolder = new Holder<>();
    dao.findOneById(id)
      .compose((DbBlogUser dbBlogUser) -> {
        if (dbBlogUser == null) {
          throw new HttpStatusException(404);
        }
        User user = userMapper.fromDb(dbBlogUser);
        userHolder.value = user;

        return authorizationService.getAuthorizationsOfUser(configuration, pool, user.getUsername());
      })
      .compose((List<Authorization> authorizations) -> {
        userHolder.value.setAuthorizations(authorizations);
        return Future.succeededFuture(userHolder.value);
      })
      .onSuccess(routingContext::json)
      .onFailure(routingContext::fail);
  }
}
