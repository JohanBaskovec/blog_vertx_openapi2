/*
 * This file is generated by jOOQ.
 */
package jooq.tables;


import java.util.Arrays;
import java.util.List;

import jooq.DbPublic;
import jooq.Indexes;
import jooq.Keys;
import jooq.tables.records.DbPermissionRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row1;
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
public class DbPermission extends TableImpl<DbPermissionRecord> {

    private static final long serialVersionUID = -401780502;

    /**
     * The reference instance of <code>public.permission</code>
     */
    public static final DbPermission PERMISSION = new DbPermission();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbPermissionRecord> getRecordType() {
        return DbPermissionRecord.class;
    }

    /**
     * The column <code>public.permission.id</code>.
     */
    public final TableField<DbPermissionRecord, String> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * Create a <code>public.permission</code> table reference
     */
    public DbPermission() {
        this(DSL.name("permission"), null);
    }

    /**
     * Create an aliased <code>public.permission</code> table reference
     */
    public DbPermission(String alias) {
        this(DSL.name(alias), PERMISSION);
    }

    /**
     * Create an aliased <code>public.permission</code> table reference
     */
    public DbPermission(Name alias) {
        this(alias, PERMISSION);
    }

    private DbPermission(Name alias, Table<DbPermissionRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbPermission(Name alias, Table<DbPermissionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> DbPermission(Table<O> child, ForeignKey<O, DbPermissionRecord> key) {
        super(child, key, PERMISSION);
    }

    @Override
    public Schema getSchema() {
        return DbPublic.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PERMISSION_ID_UINDEX);
    }

    @Override
    public UniqueKey<DbPermissionRecord> getPrimaryKey() {
        return Keys.PERMISSION_PK;
    }

    @Override
    public List<UniqueKey<DbPermissionRecord>> getKeys() {
        return Arrays.<UniqueKey<DbPermissionRecord>>asList(Keys.PERMISSION_PK);
    }

    @Override
    public DbPermission as(String alias) {
        return new DbPermission(DSL.name(alias), this);
    }

    @Override
    public DbPermission as(Name alias) {
        return new DbPermission(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbPermission rename(String name) {
        return new DbPermission(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbPermission rename(Name name) {
        return new DbPermission(name, null);
    }

    // -------------------------------------------------------------------------
    // Row1 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row1<String> fieldsRow() {
        return (Row1) super.fieldsRow();
    }
}