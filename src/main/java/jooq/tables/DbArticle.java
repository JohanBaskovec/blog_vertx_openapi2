/*
 * This file is generated by jOOQ.
 */
package jooq.tables;


import java.util.Arrays;
import java.util.List;

import jooq.Indexes;
import jooq.Keys;
import jooq.Public;
import jooq.tables.records.DbArticleRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbArticle extends TableImpl<DbArticleRecord> {

    private static final long serialVersionUID = 470528192;

    /**
     * The reference instance of <code>public.db_article</code>
     */
    public static final DbArticle DB_ARTICLE = new DbArticle();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbArticleRecord> getRecordType() {
        return DbArticleRecord.class;
    }

    /**
     * The column <code>public.db_article.id</code>.
     */
    public final TableField<DbArticleRecord, String> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "");

    /**
     * The column <code>public.db_article.title</code>.
     */
    public final TableField<DbArticleRecord, String> TITLE = createField(DSL.name("title"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.db_article.content</code>.
     */
    public final TableField<DbArticleRecord, String> CONTENT = createField(DSL.name("content"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.db_article.author_id</code>.
     */
    public final TableField<DbArticleRecord, String> AUTHOR_ID = createField(DSL.name("author_id"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.db_article.version</code>.
     */
    public final TableField<DbArticleRecord, Integer> VERSION = createField(DSL.name("version"), org.jooq.impl.SQLDataType.INTEGER.defaultValue(org.jooq.impl.DSL.field("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * Create a <code>public.db_article</code> table reference
     */
    public DbArticle() {
        this(DSL.name("db_article"), null);
    }

    /**
     * Create an aliased <code>public.db_article</code> table reference
     */
    public DbArticle(String alias) {
        this(DSL.name(alias), DB_ARTICLE);
    }

    /**
     * Create an aliased <code>public.db_article</code> table reference
     */
    public DbArticle(Name alias) {
        this(alias, DB_ARTICLE);
    }

    private DbArticle(Name alias, Table<DbArticleRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbArticle(Name alias, Table<DbArticleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> DbArticle(Table<O> child, ForeignKey<O, DbArticleRecord> key) {
        super(child, key, DB_ARTICLE);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ARTICLE_ID_UINDEX);
    }

    @Override
    public UniqueKey<DbArticleRecord> getPrimaryKey() {
        return Keys.ARTICLE_PK;
    }

    @Override
    public List<UniqueKey<DbArticleRecord>> getKeys() {
        return Arrays.<UniqueKey<DbArticleRecord>>asList(Keys.ARTICLE_PK);
    }

    @Override
    public List<ForeignKey<DbArticleRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<DbArticleRecord, ?>>asList(Keys.DB_ARTICLE__ARTICLE_USER_USERNAME_FK);
    }

    public DbUser dbUser() {
        return new DbUser(this, Keys.DB_ARTICLE__ARTICLE_USER_USERNAME_FK);
    }

    @Override
    public DbArticle as(String alias) {
        return new DbArticle(DSL.name(alias), this);
    }

    @Override
    public DbArticle as(Name alias) {
        return new DbArticle(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbArticle rename(String name) {
        return new DbArticle(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbArticle rename(Name name) {
        return new DbArticle(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<String, String, String, String, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
