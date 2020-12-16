/*
 * This file is generated by jOOQ.
 */
package jooq.tables.pojos;


import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;

import jooq.tables.interfaces.DbIUserRoles;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbUserRoles implements VertxPojo, DbIUserRoles {

    private static final long serialVersionUID = -42234995;

    private String username;
    private String role;

    public DbUserRoles() {}

    public DbUserRoles(DbIUserRoles value) {
        this.username = value.getUsername();
        this.role = value.getRole();
    }

    public DbUserRoles(
        String username,
        String role
    ) {
        this.username = username;
        this.role = role;
    }

    public DbUserRoles(io.vertx.core.json.JsonObject json) {
        this();
        fromJson(json);
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public DbUserRoles setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String getRole() {
        return this.role;
    }

    @Override
    public DbUserRoles setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DbUserRoles (");

        sb.append(username);
        sb.append(", ").append(role);

        sb.append(")");
        return sb.toString();
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
}