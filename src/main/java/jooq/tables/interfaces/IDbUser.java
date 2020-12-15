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
public interface IDbUser extends VertxPojo, Serializable {

    /**
     * Setter for <code>public.db_user.username</code>.
     */
    public IDbUser setUsername(String value);

    /**
     * Getter for <code>public.db_user.username</code>.
     */
    public String getUsername();

    /**
     * Setter for <code>public.db_user.password</code>.
     */
    public IDbUser setPassword(String value);

    /**
     * Getter for <code>public.db_user.password</code>.
     */
    public String getPassword();

    /**
     * Setter for <code>public.db_user.password_salt</code>.
     */
    public IDbUser setPasswordSalt(String value);

    /**
     * Getter for <code>public.db_user.password_salt</code>.
     */
    public String getPasswordSalt();

    /**
     * Setter for <code>public.db_user.version</code>.
     */
    public IDbUser setVersion(Integer value);

    /**
     * Getter for <code>public.db_user.version</code>.
     */
    public Integer getVersion();

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * Load data from another generated Record/POJO implementing the common interface IDbUser
     */
    public void from(IDbUser from);

    /**
     * Copy data into another generated Record/POJO implementing the common interface IDbUser
     */
    public <E extends IDbUser> E into(E into);

    @Override
    public default IDbUser fromJson(io.vertx.core.json.JsonObject json) {
        try {
            setUsername(json.getString("username"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("username","java.lang.String",e);
        }
        try {
            setPassword(json.getString("password"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("password","java.lang.String",e);
        }
        try {
            setPasswordSalt(json.getString("password_salt"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("password_salt","java.lang.String",e);
        }
        try {
            setVersion(json.getInteger("version"));
        } catch (java.lang.ClassCastException e) {
            throw new UnexpectedJsonValueType("version","java.lang.Integer",e);
        }
        return this;
    }


    @Override
    public default io.vertx.core.json.JsonObject toJson() {
        io.vertx.core.json.JsonObject json = new io.vertx.core.json.JsonObject();
        json.put("username",getUsername());
        json.put("password",getPassword());
        json.put("password_salt",getPasswordSalt());
        json.put("version",getVersion());
        return json;
    }

}
