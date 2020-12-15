/*
 * This file is generated by jOOQ.
 */
package jooq.tables;


import java.util.Arrays;
import java.util.List;

import jooq.Keys;
import jooq.Public;
import jooq.tables.records.RolesPermissionsRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RolesPermissions extends TableImpl<RolesPermissionsRecord> {

    private static final long serialVersionUID = -1184395343;

    /**
     * The reference instance of <code>public.roles_permissions</code>
     */
    public static final RolesPermissions ROLES_PERMISSIONS = new RolesPermissions();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RolesPermissionsRecord> getRecordType() {
        return RolesPermissionsRecord.class;
    }

    /**
     * The column <code>public.roles_permissions.role_id</code>.
     */
    public final TableField<RolesPermissionsRecord, String> ROLE_ID = createField(DSL.name("role_id"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.roles_permissions.permission_id</code>.
     */
    public final TableField<RolesPermissionsRecord, String> PERMISSION_ID = createField(DSL.name("permission_id"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * Create a <code>public.roles_permissions</code> table reference
     */
    public RolesPermissions() {
        this(DSL.name("roles_permissions"), null);
    }

    /**
     * Create an aliased <code>public.roles_permissions</code> table reference
     */
    public RolesPermissions(String alias) {
        this(DSL.name(alias), ROLES_PERMISSIONS);
    }

    /**
     * Create an aliased <code>public.roles_permissions</code> table reference
     */
    public RolesPermissions(Name alias) {
        this(alias, ROLES_PERMISSIONS);
    }

    private RolesPermissions(Name alias, Table<RolesPermissionsRecord> aliased) {
        this(alias, aliased, null);
    }

    private RolesPermissions(Name alias, Table<RolesPermissionsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> RolesPermissions(Table<O> child, ForeignKey<O, RolesPermissionsRecord> key) {
        super(child, key, ROLES_PERMISSIONS);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<ForeignKey<RolesPermissionsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<RolesPermissionsRecord, ?>>asList(Keys.ROLES_PERMISSIONS__ROLES_PERMISSIONS_ROLE_ID_FK, Keys.ROLES_PERMISSIONS__ROLES_PERMISSIONS_PERMISSION_ID_FK);
    }

    public Role role() {
        return new Role(this, Keys.ROLES_PERMISSIONS__ROLES_PERMISSIONS_ROLE_ID_FK);
    }

    public Permission permission() {
        return new Permission(this, Keys.ROLES_PERMISSIONS__ROLES_PERMISSIONS_PERMISSION_ID_FK);
    }

    @Override
    public RolesPermissions as(String alias) {
        return new RolesPermissions(DSL.name(alias), this);
    }

    @Override
    public RolesPermissions as(Name alias) {
        return new RolesPermissions(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RolesPermissions rename(String name) {
        return new RolesPermissions(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RolesPermissions rename(Name name) {
        return new RolesPermissions(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
