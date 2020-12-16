/*
 * This file is generated by jOOQ.
 */
package jooq.tables.records;


import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;

import jooq.tables.DbArticle;
import jooq.tables.interfaces.DbIArticle;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbArticleRecord extends UpdatableRecordImpl<DbArticleRecord> implements VertxPojo, Record5<String, String, String, String, Integer>, DbIArticle {

    private static final long serialVersionUID = 542853662;

    /**
     * Setter for <code>public.article.id</code>.
     */
    @Override
    public DbArticleRecord setId(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.article.id</code>.
     */
    @Override
    public String getId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.article.title</code>.
     */
    @Override
    public DbArticleRecord setTitle(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.article.title</code>.
     */
    @Override
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.article.content</code>.
     */
    @Override
    public DbArticleRecord setContent(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.article.content</code>.
     */
    @Override
    public String getContent() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.article.author_id</code>.
     */
    @Override
    public DbArticleRecord setAuthorId(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.article.author_id</code>.
     */
    @Override
    public String getAuthorId() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.article.version</code>.
     */
    @Override
    public DbArticleRecord setVersion(Integer value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.article.version</code>.
     */
    @Override
    public Integer getVersion() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<String, String, String, String, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<String, String, String, String, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return DbArticle.ARTICLE.ID;
    }

    @Override
    public Field<String> field2() {
        return DbArticle.ARTICLE.TITLE;
    }

    @Override
    public Field<String> field3() {
        return DbArticle.ARTICLE.CONTENT;
    }

    @Override
    public Field<String> field4() {
        return DbArticle.ARTICLE.AUTHOR_ID;
    }

    @Override
    public Field<Integer> field5() {
        return DbArticle.ARTICLE.VERSION;
    }

    @Override
    public String component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getTitle();
    }

    @Override
    public String component3() {
        return getContent();
    }

    @Override
    public String component4() {
        return getAuthorId();
    }

    @Override
    public Integer component5() {
        return getVersion();
    }

    @Override
    public String value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getTitle();
    }

    @Override
    public String value3() {
        return getContent();
    }

    @Override
    public String value4() {
        return getAuthorId();
    }

    @Override
    public Integer value5() {
        return getVersion();
    }

    @Override
    public DbArticleRecord value1(String value) {
        setId(value);
        return this;
    }

    @Override
    public DbArticleRecord value2(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public DbArticleRecord value3(String value) {
        setContent(value);
        return this;
    }

    @Override
    public DbArticleRecord value4(String value) {
        setAuthorId(value);
        return this;
    }

    @Override
    public DbArticleRecord value5(Integer value) {
        setVersion(value);
        return this;
    }

    @Override
    public DbArticleRecord values(String value1, String value2, String value3, String value4, Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(DbIArticle from) {
        setId(from.getId());
        setTitle(from.getTitle());
        setContent(from.getContent());
        setAuthorId(from.getAuthorId());
        setVersion(from.getVersion());
    }

    @Override
    public <E extends DbIArticle> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DbArticleRecord
     */
    public DbArticleRecord() {
        super(DbArticle.ARTICLE);
    }

    /**
     * Create a detached, initialised DbArticleRecord
     */
    public DbArticleRecord(String id, String title, String content, String authorId, Integer version) {
        super(DbArticle.ARTICLE);

        set(0, id);
        set(1, title);
        set(2, content);
        set(3, authorId);
        set(4, version);
    }

    public DbArticleRecord(io.vertx.core.json.JsonObject json) {
        this();
        fromJson(json);
    }
}
