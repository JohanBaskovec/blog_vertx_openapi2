/*
 * This file is generated by jOOQ.
 */
package jooq.tables.interfaces;


import io.github.jklingsporn.vertx.jooq.shared.UnexpectedJsonValueType;
import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;

import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public interface DbIRolesPermissions extends VertxPojo, Serializable {

    /**
     * Setter for <code>public.roles_permissions.role_id</code>.
     */
    public DbIRolesPermissions setRoleId(String value);

    /**
     * Getter for <code>public.roles_permissions.role_id</code>.
     */
    public String getRoleId();

    /**
     * Setter for <code>public.roles_permissions.permission_id</code>.
     */
    public DbIRolesPermissions setPermissionId(String value);

    /**
     * Getter for <code>public.roles_permissions.permission_id</code>.
     */
    public String getPermissionId();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface DbIRolesPermissions
     */
    public void from(DbIRolesPermissions from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface DbIRolesPermissions
     */
    public <E extends DbIRolesPermissions> E into(E into);

    @Override
    public default DbIRolesPermissions fromJson(io.vertx.core.json.JsonObject json) {
        try {
            setRoleId(json.getString("role_id"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("role_id","java.lang.String",e);
        }
        try {
            setPermissionId(json.getString("permission_id"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("permission_id","java.lang.String",e);
        }
        return this;
    }


    @Override
    public default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("role_id",getRoleId());
        json.put("permission_id",getPermissionId());
        return json;
    }

}
