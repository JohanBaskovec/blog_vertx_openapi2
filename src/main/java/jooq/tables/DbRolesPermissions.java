/*
 * This file is generated by jOOQ.
 */
package jooq.tables;


import java.util.Arrays;
import java.util.List;

import jooq.DbPublic;
import jooq.Keys;
import jooq.tables.records.DbRolesPermissionsRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
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
public class DbRolesPermissions extends TableImpl<DbRolesPermissionsRecord> {

    private static final long serialVersionUID = 546445644;

    /**
     * The reference instance of <code>public.roles_permissions</code>
     */
    public static final DbRolesPermissions ROLES_PERMISSIONS = new DbRolesPermissions();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DbRolesPermissionsRecord> getRecordType() {
        return DbRolesPermissionsRecord.class;
    }

    /**
     * The column <code>public.roles_permissions.role_id</code>.
     */
    public final TableField<DbRolesPermissionsRecord, String> ROLE_ID = createField(DSL.name("role_id"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.roles_permissions.permission_id</code>.
     */
    public final TableField<DbRolesPermissionsRecord, String> PERMISSION_ID = createField(DSL.name("permission_id"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * Create a <code>public.roles_permissions</code> table reference
     */
    public DbRolesPermissions() {
        this(DSL.name("roles_permissions"), null);
    }

    /**
     * Create an aliased <code>public.roles_permissions</code> table reference
     */
    public DbRolesPermissions(String alias) {
        this(DSL.name(alias), ROLES_PERMISSIONS);
    }

    /**
     * Create an aliased <code>public.roles_permissions</code> table reference
     */
    public DbRolesPermissions(Name alias) {
        this(alias, ROLES_PERMISSIONS);
    }

    private DbRolesPermissions(Name alias, Table<DbRolesPermissionsRecord> aliased) {
        this(alias, aliased, null);
    }

    private DbRolesPermissions(Name alias, Table<DbRolesPermissionsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> DbRolesPermissions(Table<O> child, ForeignKey<O, DbRolesPermissionsRecord> key) {
        super(child, key, ROLES_PERMISSIONS);
    }

    @Override
    public Schema getSchema() {
        return DbPublic.PUBLIC;
    }

    @Override
    public UniqueKey<DbRolesPermissionsRecord> getPrimaryKey() {
        return Keys.ROLES_PERMISSIONS_PK;
    }

    @Override
    public List<UniqueKey<DbRolesPermissionsRecord>> getKeys() {
        return Arrays.<UniqueKey<DbRolesPermissionsRecord>>asList(Keys.ROLES_PERMISSIONS_PK);
    }

    @Override
    public List<ForeignKey<DbRolesPermissionsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<DbRolesPermissionsRecord, ?>>asList(Keys.ROLES_PERMISSIONS__ROLES_PERMISSIONS_ROLE_ID_FK, Keys.ROLES_PERMISSIONS__ROLES_PERMISSIONS_PERMISSION_ID_FK);
    }

    public DbRole role() {
        return new DbRole(this, Keys.ROLES_PERMISSIONS__ROLES_PERMISSIONS_ROLE_ID_FK);
    }

    public DbPermission permission() {
        return new DbPermission(this, Keys.ROLES_PERMISSIONS__ROLES_PERMISSIONS_PERMISSION_ID_FK);
    }

    @Override
    public DbRolesPermissions as(String alias) {
        return new DbRolesPermissions(DSL.name(alias), this);
    }

    @Override
    public DbRolesPermissions as(Name alias) {
        return new DbRolesPermissions(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public DbRolesPermissions rename(String name) {
        return new DbRolesPermissions(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public DbRolesPermissions rename(Name name) {
        return new DbRolesPermissions(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
