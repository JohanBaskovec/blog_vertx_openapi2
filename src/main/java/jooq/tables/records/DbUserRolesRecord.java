/*
 * This file is generated by jOOQ.
 */
package jooq.tables.records;


import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;

import jooq.tables.DbUserRoles;
import jooq.tables.interfaces.DbIUserRoles;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbUserRolesRecord extends UpdatableRecordImpl<DbUserRolesRecord> implements VertxPojo, Record2<String, String>, DbIUserRoles {

    private static final long serialVersionUID = 1916465287;

    /**
     * Setter for <code>public.user_roles.username</code>.
     */
    @Override
    public DbUserRolesRecord setUsername(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.user_roles.username</code>.
     */
    @Override
    public String getUsername() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.user_roles.role</code>.
     */
    @Override
    public DbUserRolesRecord setRole(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.user_roles.role</code>.
     */
    @Override
    public String getRole() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<String, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<String, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return DbUserRoles.USER_ROLES.USERNAME;
    }

    @Override
    public Field<String> field2() {
        return DbUserRoles.USER_ROLES.ROLE;
    }

    @Override
    public String component1() {
        return getUsername();
    }

    @Override
    public String component2() {
        return getRole();
    }

    @Override
    public String value1() {
        return getUsername();
    }

    @Override
    public String value2() {
        return getRole();
    }

    @Override
    public DbUserRolesRecord value1(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public DbUserRolesRecord value2(String value) {
        setRole(value);
        return this;
    }

    @Override
    public DbUserRolesRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(DbIUserRoles from) {
        setUsername(from.getUsername());
        setRole(from.getRole());
    }

    @Override
    public <E extends DbIUserRoles> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DbUserRolesRecord
     */
    public DbUserRolesRecord() {
        super(DbUserRoles.USER_ROLES);
    }

    /**
     * Create a detached, initialised DbUserRolesRecord
     */
    public DbUserRolesRecord(String username, String role) {
        super(DbUserRoles.USER_ROLES);

        set(0, username);
        set(1, role);
    }

    public DbUserRolesRecord(io.vertx.core.json.JsonObject json) {
        this();
        fromJson(json);
    }
}
