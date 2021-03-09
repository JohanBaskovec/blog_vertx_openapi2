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
import org.openapitools.vertxweb.server.model.RoleAuthorization;
import org.openapitools.vertxweb.server.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  public void getUserByUsername(RoutingContext routingContext) {
    RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
    String username = requestParameters.pathParameter("username").getString();
    this.userService.getUserByUsername(username)
      .onSuccess(routingContext::json)
      .onFailure(routingContext::fail);
  }
}
