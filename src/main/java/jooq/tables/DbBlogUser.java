/*
 * This file is generated by jOOQ.
 */
package jooq.tables;


import java.util.Arrays;
import java.util.List;

import jooq.DbPublic;
import jooq.Keys;
import jooq.tables.records.DbBlogUserRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
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
public class DbBlogUser extends TableImpl<DbBlogUserRecord> {

    private static final long serialVersionUID = 202378939;

    /**
     * The reference instance of <code>public.blog_user</code>
     */
    public static final DbBlogUser BLOG_USER = new DbBlogUser();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbBlogUserRecord> getRecordType() {
        return DbBlogUserRecord.class;
    }

    /**
     * The column <code>public.blog_user.username</code>.
     */
    public final TableField<DbBlogUserRecord, String> USERNAME = createField(DSL.name("username"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.blog_user.password</code>.
     */
    public final TableField<DbBlogUserRecord, String> PASSWORD = createField(DSL.name("password"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.blog_user.password_salt</code>.
     */
    public final TableField<DbBlogUserRecord, String> PASSWORD_SALT = createField(DSL.name("password_salt"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.blog_user.version</code>.
     */
    public final TableField<DbBlogUserRecord, Integer> VERSION = createField(DSL.name("version"), org.jooq.impl.SQLDataType.INTEGER.defaultValue(org.jooq.impl.DSL.field("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * Create a <code>public.blog_user</code> table reference
     */
    public DbBlogUser() {
        this(DSL.name("blog_user"), null);
    }

    /**
     * Create an aliased <code>public.blog_user</code> table reference
     */
    public DbBlogUser(String alias) {
        this(DSL.name(alias), BLOG_USER);
    }

    /**
     * Create an aliased <code>public.blog_user</code> table reference
     */
    public DbBlogUser(Name alias) {
        this(alias, BLOG_USER);
    }

    private DbBlogUser(Name alias, Table<DbBlogUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbBlogUser(Name alias, Table<DbBlogUserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> DbBlogUser(Table<O> child, ForeignKey<O, DbBlogUserRecord> key) {
        super(child, key, BLOG_USER);
    }

    @Override
    public Schema getSchema() {
        return DbPublic.PUBLIC;
    }

    @Override
    public UniqueKey<DbBlogUserRecord> getPrimaryKey() {
        return Keys.PK_USERNAME;
    }

    @Override
    public List<UniqueKey<DbBlogUserRecord>> getKeys() {
        return Arrays.<UniqueKey<DbBlogUserRecord>>asList(Keys.PK_USERNAME);
    }

    @Override
    public DbBlogUser as(String alias) {
        return new DbBlogUser(DSL.name(alias), this);
    }

    @Override
    public DbBlogUser as(Name alias) {
        return new DbBlogUser(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbBlogUser rename(String name) {
        return new DbBlogUser(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbBlogUser rename(Name name) {
        return new DbBlogUser(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<String, String, String, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}