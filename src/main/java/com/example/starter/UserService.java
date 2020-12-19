package com.example.starter;

import com.example.starter.AppUser;
import io.vertx.core.Future;
import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.PermissionBasedAuthorization;
import io.vertx.ext.auth.authorization.RoleBasedAuthorization;
import io.vertx.ext.web.RoutingContext;
import org.openapitools.vertxweb.server.model.PermissionAuthorization;
import org.openapitools.vertxweb.server.model.RoleAuthorization;
import org.openapitools.vertxweb.server.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserService {
  public static Future<User> getAuthenticatedUser(RoutingContext context) {
    try {
      AppUser sessionUser = (AppUser) context.user();
      User user = new User();
      user.setUsername(sessionUser.getUsername());
      Set<Authorization> vertxAuthorizationList = sessionUser.authorizations().get("jooq-client");
      List<org.openapitools.vertxweb.server.model.Authorization> authorizations = new ArrayList<>();
      for (io.vertx.ext.auth.authorization.Authorization auth : vertxAuthorizationList) {
        if (auth instanceof RoleBasedAuthorization) {
          String roleString = ((RoleBasedAuthorization) auth).getRole();
          RoleAuthorization roleAuthorization = new RoleAuthorization();
          roleAuthorization.setRole(roleString);
          roleAuthorization.setType("role");
          authorizations.add(roleAuthorization);
        } else if (auth instanceof PermissionBasedAuthorization) {
          String permissionString = ((PermissionBasedAuthorization) auth).getPermission();
          PermissionAuthorization permissionAuthorization = new PermissionAuthorization(permissionString);
          permissionAuthorization.setType("permission");
          authorizations.add(permissionAuthorization);
        }
      }
      user.setAuthorizations(authorizations);
      return Future.succeededFuture(user);
    } catch (Throwable t) {
      return Future.failedFuture(t);
    }
  }
}
