package com.example.starter;

import io.github.jklingsporn.vertx.jooq.classic.reactivepg.ReactiveClassicQueryExecutor;
import io.github.jklingsporn.vertx.jooq.shared.reactive.ReactiveQueryExecutor;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authorization.Authorization;
import io.vertx.ext.auth.authorization.AuthorizationProvider;
import io.vertx.ext.auth.authorization.PermissionBasedAuthorization;
import io.vertx.ext.auth.authorization.RoleBasedAuthorization;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.Row;
import jooq.tables.DbRole;
import jooq.tables.DbRolesPermissions;
import jooq.tables.DbUserRoles;
import jooq.tables.daos.DbUserRolesDao;
import org.jooq.Configuration;

import java.util.*;
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
      ReactiveQueryExecutor<Future<List<Row>>, Future<Row>, Future<Integer>> executor = new ReactiveClassicQueryExecutor<>(jooqConfiguration, pool, jooq.tables.mappers.RowMappers.getDbUserRolesMapper());
      executor.findManyRow(
        dslContext -> dslContext.select(
          DbRole.ROLE.ID.as("role_id"),
          DbRolesPermissions.ROLES_PERMISSIONS.PERMISSION_ID.as("permission_id"))
          .from(DbUserRoles.USER_ROLES
            .join(DbRole.ROLE).on(DbRole.ROLE.ID.eq(DbUserRoles.USER_ROLES.ROLE))
            .leftJoin(DbRolesPermissions.ROLES_PERMISSIONS).on(DbRole.ROLE.ID.eq(DbRolesPermissions.ROLES_PERMISSIONS.ROLE_ID)))
          .where(DbUserRoles.USER_ROLES.USERNAME.eq(username))
      ).<Void>compose((List<Row> rows) -> {
        String lastRole = "";
        for (Row row : rows) {
          String role = row.getString("role_id");
          if (!role.equals(lastRole)) {
            authorizations.add(RoleBasedAuthorization.create(role));
            lastRole = role;
          }
          String permission = row.getString("permission_id");
          if (permission != null) {
            authorizations.add(PermissionBasedAuthorization.create(permission));
          }
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
