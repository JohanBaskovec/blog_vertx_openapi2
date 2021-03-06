/*
 * This file is generated by jOOQ.
 */
package jooq.tables.daos;


import io.github.jklingsporn.vertx.jooq.shared.reactive.AbstractReactiveVertxDAO;

import java.time.OffsetDateTime;
import java.util.Collection;

import jooq.tables.DbArticleComment;
import jooq.tables.records.DbArticleCommentRecord;

import org.jooq.Configuration;


import java.util.List;
import io.vertx.core.Future;
import io.github.jklingsporn.vertx.jooq.classic.reactivepg.ReactiveClassicQueryExecutor;
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbArticleCommentDao extends AbstractReactiveVertxDAO<DbArticleCommentRecord, jooq.tables.pojos.DbArticleComment, String, Future<List<jooq.tables.pojos.DbArticleComment>>, Future<jooq.tables.pojos.DbArticleComment>, Future<Integer>, Future<String>> implements io.github.jklingsporn.vertx.jooq.classic.VertxDAO<DbArticleCommentRecord,jooq.tables.pojos.DbArticleComment,String> {

    /**
     * @param configuration Used for rendering, so only SQLDialect must be set and must be one of the POSTGREs types.
     * @param delegate A configured AsyncSQLClient that is used for query execution
     */
    public DbArticleCommentDao(Configuration configuration, io.vertx.sqlclient.SqlClient delegate) {
        super(DbArticleComment.ARTICLE_COMMENT, jooq.tables.pojos.DbArticleComment.class, new ReactiveClassicQueryExecutor<DbArticleCommentRecord,jooq.tables.pojos.DbArticleComment,String>(configuration,delegate,jooq.tables.mappers.RowMappers.getDbArticleCommentMapper()));
    }

    @Override
    protected String getId(jooq.tables.pojos.DbArticleComment object) {
        return object.getId();
    }

    /**
     * Find records that have <code>content IN (values)</code> asynchronously
     */
    public Future<List<jooq.tables.pojos.DbArticleComment>> findManyByDbContent(Collection<String> values) {
        return findManyByCondition(DbArticleComment.ARTICLE_COMMENT.CONTENT.in(values));
    }

    /**
     * Find records that have <code>content IN (values)</code> asynchronously limited by the given limit
     */
    public Future<List<jooq.tables.pojos.DbArticleComment>> findManyByDbContent(Collection<String> values, int limit) {
        return findManyByCondition(DbArticleComment.ARTICLE_COMMENT.CONTENT.in(values),limit);
    }

    /**
     * Find records that have <code>author_id IN (values)</code> asynchronously
     */
    public Future<List<jooq.tables.pojos.DbArticleComment>> findManyByDbAuthorId(Collection<String> values) {
        return findManyByCondition(DbArticleComment.ARTICLE_COMMENT.AUTHOR_ID.in(values));
    }

    /**
     * Find records that have <code>author_id IN (values)</code> asynchronously limited by the given limit
     */
    public Future<List<jooq.tables.pojos.DbArticleComment>> findManyByDbAuthorId(Collection<String> values, int limit) {
        return findManyByCondition(DbArticleComment.ARTICLE_COMMENT.AUTHOR_ID.in(values),limit);
    }

    /**
     * Find records that have <code>version IN (values)</code> asynchronously
     */
    public Future<List<jooq.tables.pojos.DbArticleComment>> findManyByDbVersion(Collection<Integer> values) {
        return findManyByCondition(DbArticleComment.ARTICLE_COMMENT.VERSION.in(values));
    }

    /**
     * Find records that have <code>version IN (values)</code> asynchronously limited by the given limit
     */
    public Future<List<jooq.tables.pojos.DbArticleComment>> findManyByDbVersion(Collection<Integer> values, int limit) {
        return findManyByCondition(DbArticleComment.ARTICLE_COMMENT.VERSION.in(values),limit);
    }

    /**
     * Find records that have <code>creation_time IN (values)</code> asynchronously
     */
    public Future<List<jooq.tables.pojos.DbArticleComment>> findManyByDbCreationTime(Collection<OffsetDateTime> values) {
        return findManyByCondition(DbArticleComment.ARTICLE_COMMENT.CREATION_TIME.in(values));
    }

    /**
     * Find records that have <code>creation_time IN (values)</code> asynchronously limited by the given limit
     */
    public Future<List<jooq.tables.pojos.DbArticleComment>> findManyByDbCreationTime(Collection<OffsetDateTime> values, int limit) {
        return findManyByCondition(DbArticleComment.ARTICLE_COMMENT.CREATION_TIME.in(values),limit);
    }

    /**
     * Find records that have <code>last_modification_time IN (values)</code> asynchronously
     */
    public Future<List<jooq.tables.pojos.DbArticleComment>> findManyByDbLastModificationTime(Collection<OffsetDateTime> values) {
        return findManyByCondition(DbArticleComment.ARTICLE_COMMENT.LAST_MODIFICATION_TIME.in(values));
    }

    /**
     * Find records that have <code>last_modification_time IN (values)</code> asynchronously limited by the given limit
     */
    public Future<List<jooq.tables.pojos.DbArticleComment>> findManyByDbLastModificationTime(Collection<OffsetDateTime> values, int limit) {
        return findManyByCondition(DbArticleComment.ARTICLE_COMMENT.LAST_MODIFICATION_TIME.in(values),limit);
    }

    /**
     * Find records that have <code>article_id IN (values)</code> asynchronously
     */
    public Future<List<jooq.tables.pojos.DbArticleComment>> findManyByDbArticleId(Collection<String> values) {
        return findManyByCondition(DbArticleComment.ARTICLE_COMMENT.ARTICLE_ID.in(values));
    }

    /**
     * Find records that have <code>article_id IN (values)</code> asynchronously limited by the given limit
     */
    public Future<List<jooq.tables.pojos.DbArticleComment>> findManyByDbArticleId(Collection<String> values, int limit) {
        return findManyByCondition(DbArticleComment.ARTICLE_COMMENT.ARTICLE_ID.in(values),limit);
    }

    @Override
    public ReactiveClassicQueryExecutor<DbArticleCommentRecord,jooq.tables.pojos.DbArticleComment,String> queryExecutor(){
        return (ReactiveClassicQueryExecutor<DbArticleCommentRecord,jooq.tables.pojos.DbArticleComment,String>) super.queryExecutor();
    }
}
