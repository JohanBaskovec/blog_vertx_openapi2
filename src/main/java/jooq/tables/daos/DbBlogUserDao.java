/*
 * This file is generated by jOOQ.
 */
package jooq.tables.daos;


import io.github.jklingsporn.vertx.jooq.shared.reactive.AbstractReactiveVertxDAO;

import java.time.OffsetDateTime;
import java.util.Collection;

import jooq.tables.DbBlogUser;
import jooq.tables.records.DbBlogUserRecord;

import org.jooq.Configuration;


import java.util.List;
import io.vertx.core.Future;
import io.github.jklingsporn.vertx.jooq.classic.reactivepg.ReactiveClassicQueryExecutor;
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbBlogUserDao extends AbstractReactiveVertxDAO<DbBlogUserRecord, jooq.tables.pojos.DbBlogUser, String, Future<List<jooq.tables.pojos.DbBlogUser>>, Future<jooq.tables.pojos.DbBlogUser>, Future<Integer>, Future<String>> implements io.github.jklingsporn.vertx.jooq.classic.VertxDAO<DbBlogUserRecord,jooq.tables.pojos.DbBlogUser,String> {

    /**
     * @param configuration Used for rendering, so only SQLDialect must be set and must be one of the POSTGREs types.
     * @param delegate A configured AsyncSQLClient that is used for query execution
     */
    public DbBlogUserDao(Configuration configuration, io.vertx.sqlclient.SqlClient delegate) {
        super(DbBlogUser.BLOG_USER, jooq.tables.pojos.DbBlogUser.class, new ReactiveClassicQueryExecutor<DbBlogUserRecord,jooq.tables.pojos.DbBlogUser,String>(configuration,delegate,jooq.tables.mappers.RowMappers.getDbBlogUserMapper()));
    }

    @Override
    protected String getId(jooq.tables.pojos.DbBlogUser object) {
        return object.getUsername();
    }

    /**
     * Find records that have <code>password IN (values)</code> asynchronously
     */
    public Future<List<jooq.tables.pojos.DbBlogUser>> findManyByDbPassword(Collection<String> values) {
        return findManyByCondition(DbBlogUser.BLOG_USER.PASSWORD.in(values));
    }

    /**
     * Find records that have <code>password IN (values)</code> asynchronously limited by the given limit
     */
    public Future<List<jooq.tables.pojos.DbBlogUser>> findManyByDbPassword(Collection<String> values, int limit) {
        return findManyByCondition(DbBlogUser.BLOG_USER.PASSWORD.in(values),limit);
    }

    /**
     * Find records that have <code>password_salt IN (values)</code> asynchronously
     */
    public Future<List<jooq.tables.pojos.DbBlogUser>> findManyByDbPasswordSalt(Collection<String> values) {
        return findManyByCondition(DbBlogUser.BLOG_USER.PASSWORD_SALT.in(values));
    }

    /**
     * Find records that have <code>password_salt IN (values)</code> asynchronously limited by the given limit
     */
    public Future<List<jooq.tables.pojos.DbBlogUser>> findManyByDbPasswordSalt(Collection<String> values, int limit) {
        return findManyByCondition(DbBlogUser.BLOG_USER.PASSWORD_SALT.in(values),limit);
    }

    /**
     * Find records that have <code>version IN (values)</code> asynchronously
     */
    public Future<List<jooq.tables.pojos.DbBlogUser>> findManyByDbVersion(Collection<Integer> values) {
        return findManyByCondition(DbBlogUser.BLOG_USER.VERSION.in(values));
    }

    /**
     * Find records that have <code>version IN (values)</code> asynchronously limited by the given limit
     */
    public Future<List<jooq.tables.pojos.DbBlogUser>> findManyByDbVersion(Collection<Integer> values, int limit) {
        return findManyByCondition(DbBlogUser.BLOG_USER.VERSION.in(values),limit);
    }

    /**
     * Find records that have <code>creation_time IN (values)</code> asynchronously
     */
    public Future<List<jooq.tables.pojos.DbBlogUser>> findManyByDbCreationTime(Collection<OffsetDateTime> values) {
        return findManyByCondition(DbBlogUser.BLOG_USER.CREATION_TIME.in(values));
    }

    /**
     * Find records that have <code>creation_time IN (values)</code> asynchronously limited by the given limit
     */
    public Future<List<jooq.tables.pojos.DbBlogUser>> findManyByDbCreationTime(Collection<OffsetDateTime> values, int limit) {
        return findManyByCondition(DbBlogUser.BLOG_USER.CREATION_TIME.in(values),limit);
    }

    /**
     * Find records that have <code>last_modification_time IN (values)</code> asynchronously
     */
    public Future<List<jooq.tables.pojos.DbBlogUser>> findManyByDbLastModificationTime(Collection<OffsetDateTime> values) {
        return findManyByCondition(DbBlogUser.BLOG_USER.LAST_MODIFICATION_TIME.in(values));
    }

    /**
     * Find records that have <code>last_modification_time IN (values)</code> asynchronously limited by the given limit
     */
    public Future<List<jooq.tables.pojos.DbBlogUser>> findManyByDbLastModificationTime(Collection<OffsetDateTime> values, int limit) {
        return findManyByCondition(DbBlogUser.BLOG_USER.LAST_MODIFICATION_TIME.in(values),limit);
    }

    @Override
    public ReactiveClassicQueryExecutor<DbBlogUserRecord,jooq.tables.pojos.DbBlogUser,String> queryExecutor(){
        return (ReactiveClassicQueryExecutor<DbBlogUserRecord,jooq.tables.pojos.DbBlogUser,String>) super.queryExecutor();
    }
}
