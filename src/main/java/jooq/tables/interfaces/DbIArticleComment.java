/*
 * This file is generated by jOOQ.
 */
package jooq.tables.interfaces;


import io.github.jklingsporn.vertx.jooq.shared.UnexpectedJsonValueType;
import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;

import java.io.Serializable;
import java.time.OffsetDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface DbIArticleComment extends VertxPojo, Serializable {

    /**
     * Setter for <code>public.article_comment.id</code>.
     */
    public DbIArticleComment setId(String value);

    /**
     * Getter for <code>public.article_comment.id</code>.
     */
    public String getId();

    /**
     * Setter for <code>public.article_comment.content</code>.
     */
    public DbIArticleComment setContent(String value);

    /**
     * Getter for <code>public.article_comment.content</code>.
     */
    public String getContent();

    /**
     * Setter for <code>public.article_comment.author_id</code>.
     */
    public DbIArticleComment setAuthorId(String value);

    /**
     * Getter for <code>public.article_comment.author_id</code>.
     */
    public String getAuthorId();

    /**
     * Setter for <code>public.article_comment.version</code>.
     */
    public DbIArticleComment setVersion(Integer value);

    /**
     * Getter for <code>public.article_comment.version</code>.
     */
    public Integer getVersion();

    /**
     * Setter for <code>public.article_comment.creation_time</code>.
     */
    public DbIArticleComment setCreationTime(OffsetDateTime value);

    /**
     * Getter for <code>public.article_comment.creation_time</code>.
     */
    public OffsetDateTime getCreationTime();

    /**
     * Setter for <code>public.article_comment.last_modification_time</code>.
     */
    public DbIArticleComment setLastModificationTime(OffsetDateTime value);

    /**
     * Getter for <code>public.article_comment.last_modification_time</code>.
     */
    public OffsetDateTime getLastModificationTime();

    /**
     * Setter for <code>public.article_comment.article_id</code>.
     */
    public DbIArticleComment setArticleId(String value);

    /**
     * Getter for <code>public.article_comment.article_id</code>.
     */
    public String getArticleId();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface DbIArticleComment
     */
    public void from(DbIArticleComment from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface DbIArticleComment
     */
    public <E extends DbIArticleComment> E into(E into);

    @Override
    public default DbIArticleComment fromJson(io.vertx.core.json.JsonObject json) {
        try {
            setId(json.getString("id"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("id","java.lang.String",e);
        }
        try {
            setContent(json.getString("content"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("content","java.lang.String",e);
        }
        try {
            setAuthorId(json.getString("author_id"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("author_id","java.lang.String",e);
        }
        try {
            setVersion(json.getInteger("version"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("version","java.lang.Integer",e);
        }
        try {
            String creation_timeString = json.getString("creation_time");
            setCreationTime(creation_timeString == null?null:java.time.OffsetDateTime.parse(creation_timeString));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("creation_time","java.time.OffsetDateTime",e);
        }
        try {
            String last_modification_timeString = json.getString("last_modification_time");
            setLastModificationTime(last_modification_timeString == null?null:java.time.OffsetDateTime.parse(last_modification_timeString));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("last_modification_time","java.time.OffsetDateTime",e);
        }
        try {
            setArticleId(json.getString("article_id"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("article_id","java.lang.String",e);
        }
        return this;
    }


    @Override
    public default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("id",getId());
        json.put("content",getContent());
        json.put("author_id",getAuthorId());
        json.put("version",getVersion());
        json.put("creation_time",getCreationTime()==null?null:getCreationTime().toString());
        json.put("last_modification_time",getLastModificationTime()==null?null:getLastModificationTime().toString());
        json.put("article_id",getArticleId());
        return json;
    }

}
