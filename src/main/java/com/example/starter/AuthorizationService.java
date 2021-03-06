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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthorizationService {
  public Future<List<RoleAuthorization>> getAuthorizationsOfUser(
    Configuration jooqConfiguration,
    SqlClient sqlClient,
    String username
  ) {
    try {
      DbUserRolesDao dbUserRolesDao = new DbUserRolesDao(jooqConfiguration, sqlClient);

      Map<String, RoleAuthorization> roleAuthorizationMap = new HashMap<>();
      return dbUserRolesDao.findManyByCondition(jooq.tables.DbUserRoles.USER_ROLES.USERNAME.eq(username))
        .compose((List<DbUserRoles> userRoles) -> {
          DbRoleDao dbRoleDao = new DbRoleDao(jooqConfiguration, sqlClient);
          List<String> roleIds = userRoles.stream().map(DbUserRoles::getRole).collect(Collectors.toList());
          return dbRoleDao.findManyByCondition(jooq.tables.DbRole.ROLE.ID.in(roleIds));
        })
        .compose((List<DbRole> dbRoles) -> {
          List<String> roleIds = dbRoles.stream().map(DbRole::getId).collect(Collectors.toList());
          for (DbRole role : dbRoles) {
            roleAuthorizationMap.put(role.getId(), AuthorizationService.newRoleAuthorization("postgres", role.getId()));
          }
          DbRolesPermissionsDao rolesPermissionsDao = new DbRolesPermissionsDao(jooqConfiguration, sqlClient);
          return rolesPermissionsDao.findManyByCondition(jooq.tables.DbRolesPermissions.ROLES_PERMISSIONS.ROLE_ID.in(roleIds));
        })
        .compose((List<DbRolesPermissions> dbRolesPermissions) -> {
          for (DbRolesPermissions drp : dbRolesPermissions) {
            RoleAuthorization role = roleAuthorizationMap.get(drp.getRoleId());
            role.getPermissions().add(AuthorizationService.newPermissionAuthorization("postgres", drp.getPermissionId()));
          }
          return Future.succeededFuture(new ArrayList<>(roleAuthorizationMap.values()));
        });
    } catch (Throwable t) {
      return Future.failedFuture(t);
    }
  }

  public static RoleAuthorization newRoleAuthorization(String providerId, String role) {
    RoleAuthorization roleAuthorization = new RoleAuthorization();
    roleAuthorization.setRole(role);
    roleAuthorization.setType("role");
    roleAuthorization.setProviderId(providerId);
    return roleAuthorization;
  }

  public static PermissionAuthorization newPermissionAuthorization(String providerId, String permission) {
    PermissionAuthorization permissionAuthorization = new PermissionAuthorization();
    permissionAuthorization.setPermission(permission);
    permissionAuthorization.setType("permission");
    permissionAuthorization.setProviderId(providerId);
    return permissionAuthorization;
  }
}
