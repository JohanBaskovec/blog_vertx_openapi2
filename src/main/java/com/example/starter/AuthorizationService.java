package com.example.starter;

import io.vertx.core.Future;
import io.vertx.sqlclient.SqlClient;
import jooq.tables.daos.DbRoleDao;
import jooq.tables.daos.DbRolesPermissionsDao;
import jooq.tables.daos.DbUserRolesDao;
import jooq.tables.pojos.DbRole;
import jooq.tables.pojos.DbRolesPermissions;
import jooq.tables.pojos.DbUserRoles;
import org.jooq.Configuration;
import org.openapitools.vertxweb.server.model.Authorization;
import org.openapitools.vertxweb.server.model.PermissionAuthorization;
import org.openapitools.vertxweb.server.model.RoleAuthorization;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorizationService {
  public Future<List<Authorization>> getAuthorizationsOfUser(
    Configuration jooqConfiguration,
    SqlClient sqlClient,
    String username
  ) {
    try {
      DbUserRolesDao dbUserRolesDao = new DbUserRolesDao(jooqConfiguration, sqlClient);

      List<Authorization> authorizations = new ArrayList<>();
      return dbUserRolesDao.findManyByCondition(jooq.tables.DbUserRoles.USER_ROLES.USERNAME.eq(username))
        .compose((List<DbUserRoles> userRoles) -> {
          DbRoleDao dbRoleDao = new DbRoleDao(jooqConfiguration, sqlClient);
          List<String> roleIds = userRoles.stream().map(DbUserRoles::getRole).collect(Collectors.toList());
          return dbRoleDao.findManyByCondition(jooq.tables.DbRole.ROLE.ID.in(roleIds));
        })
        .compose((List<DbRole> dbRoles) -> {
          List<String> roleIds = dbRoles.stream().map(DbRole::getId).collect(Collectors.toList());
          for (String roleId : roleIds) {
            authorizations.add(AuthorizationService.newRoleAuthorization(roleId));
          }
          DbRolesPermissionsDao rolesPermissionsDao = new DbRolesPermissionsDao(jooqConfiguration, sqlClient);
          return rolesPermissionsDao.findManyByCondition(jooq.tables.DbRolesPermissions.ROLES_PERMISSIONS.ROLE_ID.in(roleIds));
        })
        .compose((List<DbRolesPermissions> dbRolesPermissions) -> {
          for (DbRolesPermissions drp : dbRolesPermissions) {
            authorizations.add(AuthorizationService.newPermissionAuthorization(drp.getPermissionId()));
          }
          return Future.succeededFuture(authorizations);
        });
    } catch (Throwable t) {
      return Future.failedFuture(t);
    }
  }

  public static RoleAuthorization newRoleAuthorization(String role) {
    RoleAuthorization roleAuthorization = new RoleAuthorization();
    roleAuthorization.setRole(role);
    roleAuthorization.setType("role");
    return roleAuthorization;
  }

  public static PermissionAuthorization newPermissionAuthorization(String permission) {
    PermissionAuthorization permissionAuthorization = new PermissionAuthorization();
    permissionAuthorization.setPermission(permission);
    permissionAuthorization.setType("permission");
    return permissionAuthorization;
  }
}
