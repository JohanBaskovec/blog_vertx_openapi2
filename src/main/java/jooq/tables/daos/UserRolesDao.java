/*
 * This file is generated by jOOQ.
 */
package jooq.tables.daos;


import io.github.jklingsporn.vertx.jooq.shared.reactive.AbstractReactiveVertxDAO;

import java.util.Collection;

import jooq.tables.UserRoles;
import jooq.tables.records.UserRolesRecord;

import org.jooq.Configuration;
import org.jooq.Record2;


import java.util.List;
import io.vertx.core.Future;
import io.github.jklingsporn.vertx.jooq.classic.reactivepg.ReactiveClassicQueryExecutor;
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRolesDao extends AbstractReactiveVertxDAO<UserRolesRecord, jooq.tables.pojos.UserRoles, Record2<String, String>, Future<List<jooq.tables.pojos.UserRoles>>, Future<jooq.tables.pojos.UserRoles>, Future<Integer>, Future<Record2<String, String>>> implements io.github.jklingsporn.vertx.jooq.classic.VertxDAO<UserRolesRecord,jooq.tables.pojos.UserRoles,Record2<String, String>> {

    /**
     * @param configuration Used for rendering, so only SQLDialect must be set and must be one of the POSTGREs types.
     * @param delegate A configured AsyncSQLClient that is used for query execution
     */
    public UserRolesDao(Configuration configuration, io.vertx.sqlclient.SqlClient delegate) {
        super(UserRoles.USER_ROLES, jooq.tables.pojos.UserRoles.class, new ReactiveClassicQueryExecutor<UserRolesRecord,jooq.tables.pojos.UserRoles,Record2<String, String>>(configuration,delegate,jooq.tables.mappers.RowMappers.getUserRolesMapper()));
    }

    @Override
    protected Record2<String, String> getId(jooq.tables.pojos.UserRoles object) {
        return compositeKeyRecord(object.getUsername(), object.getRole());
    }

    /**
     * Find records that have <code>role IN (values)</code> asynchronously
     */
    public Future<List<jooq.tables.pojos.UserRoles>> findManyByRole(Collection<String> values) {
        return findManyByCondition(UserRoles.USER_ROLES.ROLE.in(values));
    }

    /**
     * Find records that have <code>role IN (values)</code> asynchronously limited by the given limit
     */
    public Future<List<jooq.tables.pojos.UserRoles>> findManyByRole(Collection<String> values, int limit) {
        return findManyByCondition(UserRoles.USER_ROLES.ROLE.in(values),limit);
    }

    @Override
    public ReactiveClassicQueryExecutor<UserRolesRecord,jooq.tables.pojos.UserRoles,Record2<String, String>> queryExecutor(){
        return (ReactiveClassicQueryExecutor<UserRolesRecord,jooq.tables.pojos.UserRoles,Record2<String, String>>) super.queryExecutor();
    }
}
