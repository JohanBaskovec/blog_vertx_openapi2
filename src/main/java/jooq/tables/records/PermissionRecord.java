/*
 * This file is generated by jOOQ.
 */
package jooq.tables.records;


import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;

import jooq.tables.Permission;
import jooq.tables.interfaces.IPermission;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PermissionRecord extends UpdatableRecordImpl<PermissionRecord> implements VertxPojo, Record1<String>, IPermission {

    private static final long serialVersionUID = 1968752623;

    /**
     * Setter for <code>public.permission.id</code>.
     */
    @Override
    public PermissionRecord setId(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.permission.id</code>.
     */
    @Override
    public String getId() {
        return (String) get(0);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record1 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row1<String> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    @Override
    public Row1<String> valuesRow() {
        return (Row1) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Permission.PERMISSION.ID;
    }

    @Override
    public String component1() {
        return getId();
    }

    @Override
    public String value1() {
        return getId();
    }

    @Override
    public PermissionRecord value1(String value) {
        setId(value);
        return this;
    }

    @Override
    public PermissionRecord values(String value1) {
        value1(value1);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(IPermission from) {
        setId(from.getId());
    }

    @Override
    public <E extends IPermission> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PermissionRecord
     */
    public PermissionRecord() {
        super(Permission.PERMISSION);
    }

    /**
     * Create a detached, initialised PermissionRecord
     */
    public PermissionRecord(String id) {
        super(Permission.PERMISSION);

        set(0, id);
    }

    public PermissionRecord(io.vertx.core.json.JsonObject json) {
        this();
        fromJson(json);
    }
}
