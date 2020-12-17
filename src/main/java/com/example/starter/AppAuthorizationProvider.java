package com.example.starter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.authorization.PermissionBasedAuthorization;
import io.vertx.ext.auth.authorization.RoleBasedAuthorization;
import io.vertx.sqlclient.Pool;
import jooq.tables.daos.DbRoleDao;
import jooq.tables.daos.DbRolesPermissionsDao;
import jooq.tables.daos.DbUserRolesDao;
import jooq.tables.pojos.DbRole;
import jooq.tables.pojos.DbRolesPermissions;
import jooq.tables.pojos.DbUserRoles;
import org.jooq.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AppAuthorizationProvider implements AuthorizationProvider {
  final private Configuration jooqConfiguration;
  final private Pool pool;

  public AppAuthorizationProvider(Configuration jooqConfiguration, Pool pool) {
    this.jooqConfiguration = jooqConfiguration;
    this.pool = pool;
  }

  @Override
  public String getId() {
    return "jooq-client";
  }

  @Override
  public void getAuthorizations(User user, Handler<AsyncResult<Void>> resultHandler) {
    AppUser appUser = (AppUser) user;
    String username = appUser.getUsername();
    if (username != null) {
      Set<Authorization> authorizations = new HashSet<>();
      //Map<String, Role> roles = new HashMap<>();
      DbUserRolesDao dbUserRolesDao = new DbUserRolesDao(jooqConfiguration, pool);
      dbUserRolesDao.findManyByCondition(jooq.tables.DbUserRoles.USER_ROLES.USERNAME.eq(username))
        .compose((List<DbUserRoles> userRoles) -> {
          DbRoleDao dbRoleDao = new DbRoleDao(jooqConfiguration, pool);
          List<String> roleIds = userRoles.stream().map(DbUserRoles::getRole).collect(Collectors.toList());
          return dbRoleDao.findManyByCondition(jooq.tables.DbRole.ROLE.ID.in(roleIds));
        })
        .compose((List<DbRole> dbRoles) -> {
          List<String> roleIds = dbRoles.stream().map(DbRole::getId).collect(Collectors.toList());
          for (String id : roleIds) {
            authorizations.add(RoleBasedAuthorization.create(id));
            //roles.put(id, new Role());
          }
          DbRolesPermissionsDao rolesPermissionsDao = new DbRolesPermissionsDao(jooqConfiguration, pool);
          return rolesPermissionsDao.findManyByCondition(jooq.tables.DbRolesPermissions.ROLES_PERMISSIONS.ROLE_ID.in(roleIds));
        })
        .<Void>compose((List<DbRolesPermissions> dbRolesPermissions) -> {
          for (DbRolesPermissions drp : dbRolesPermissions) {
            authorizations.add(PermissionBasedAuthorization.create(drp.getPermissionId()));
            //roles.get(drp.getRoleId()).getPermissions().add(new Permission(drp.getPermissionId()));
          }
          user.authorizations().add(getId(), authorizations);
          return Future.succeededFuture();
        })
        .onSuccess((Void result) -> {
          resultHandler.handle(Future.succeededFuture());
        })
        .onFailure((Throwable t) -> {
          resultHandler.handle(Future.failedFuture(t));
        });
    } else {
      resultHandler.handle(Future.failedFuture("Couldn't get username!"));
    }
  }
}
