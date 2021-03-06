/*
 * This file is generated by jOOQ.
 */
package jooq.tables;


import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import jooq.DbPublic;
import jooq.Indexes;
import jooq.Keys;
import jooq.tables.records.DbRoleRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
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
public class DbRole extends TableImpl<DbRoleRecord> {

    private static final long serialVersionUID = 346326216;

    /**
     * The reference instance of <code>public.role</code>
     */
    public static final DbRole ROLE = new DbRole();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbRoleRecord> getRecordType() {
        return DbRoleRecord.class;
    }

    /**
     * The column <code>public.role.id</code>.
     */
    public final TableField<DbRoleRecord, String> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.role.last_modification_time</code>.
     */
    public final TableField<DbRoleRecord, OffsetDateTime> LAST_MODIFICATION_TIME = createField(DSL.name("last_modification_time"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE, this, "");

    /**
     * The column <code>public.role.creation_time</code>.
     */
    public final TableField<DbRoleRecord, OffsetDateTime> CREATION_TIME = createField(DSL.name("creation_time"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "");

    /**
     * Create a <code>public.role</code> table reference
     */
    public DbRole() {
        this(DSL.name("role"), null);
    }

    /**
     * Create an aliased <code>public.role</code> table reference
     */
    public DbRole(String alias) {
        this(DSL.name(alias), ROLE);
    }

    /**
     * Create an aliased <code>public.role</code> table reference
     */
    public DbRole(Name alias) {
        this(alias, ROLE);
    }

    private DbRole(Name alias, Table<DbRoleRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbRole(Name alias, Table<DbRoleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> DbRole(Table<O> child, ForeignKey<O, DbRoleRecord> key) {
        super(child, key, ROLE);
    }

    @Override
    public Schema getSchema() {
        return DbPublic.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ROLE_ID_UINDEX);
    }

    @Override
    public UniqueKey<DbRoleRecord> getPrimaryKey() {
        return Keys.ROLE_PK;
    }

    @Override
    public List<UniqueKey<DbRoleRecord>> getKeys() {
        return Arrays.<UniqueKey<DbRoleRecord>>asList(Keys.ROLE_PK);
    }

    @Override
    public DbRole as(String alias) {
        return new DbRole(DSL.name(alias), this);
    }

    @Override
    public DbRole as(Name alias) {
        return new DbRole(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbRole rename(String name) {
        return new DbRole(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbRole rename(Name name) {
        return new DbRole(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, OffsetDateTime, OffsetDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
