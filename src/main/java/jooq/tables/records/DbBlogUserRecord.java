/*
 * This file is generated by jOOQ.
 */
package jooq.tables.records;


import io.github.jklingsporn.vertx.jooq.shared.internal.VertxPojo;

import java.time.OffsetDateTime;

import jooq.tables.DbBlogUser;
import jooq.tables.interfaces.DbIBlogUser;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DbBlogUserRecord extends UpdatableRecordImpl<DbBlogUserRecord> implements VertxPojo, Record6<String, String, String, Integer, OffsetDateTime, OffsetDateTime>, DbIBlogUser {

    private static final long serialVersionUID = 1683574079;

    /**
     * Setter for <code>public.blog_user.username</code>.
     */
    @Override
    public DbBlogUserRecord setUsername(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.blog_user.username</code>.
     */
    @Override
    public String getUsername() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.blog_user.password</code>.
     */
    @Override
    public DbBlogUserRecord setPassword(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.blog_user.password</code>.
     */
    @Override
    public String getPassword() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.blog_user.password_salt</code>.
     */
    @Override
    public DbBlogUserRecord setPasswordSalt(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.blog_user.password_salt</code>.
     */
    @Override
    public String getPasswordSalt() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.blog_user.version</code>.
     */
    @Override
    public DbBlogUserRecord setVersion(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>public.blog_user.version</code>.
     */
    @Override
    public Integer getVersion() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>public.blog_user.creation_time</code>.
     */
    @Override
    public DbBlogUserRecord setCreationTime(OffsetDateTime value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>public.blog_user.creation_time</code>.
     */
    @Override
    public OffsetDateTime getCreationTime() {
        return (OffsetDateTime) get(4);
    }

    /**
     * Setter for <code>public.blog_user.last_modification_time</code>.
     */
    @Override
    public DbBlogUserRecord setLastModificationTime(OffsetDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>public.blog_user.last_modification_time</code>.
     */
    @Override
    public OffsetDateTime getLastModificationTime() {
        return (OffsetDateTime) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<String, String, String, Integer, OffsetDateTime, OffsetDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<String, String, String, Integer, OffsetDateTime, OffsetDateTime> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return DbBlogUser.BLOG_USER.USERNAME;
    }

    @Override
    public Field<String> field2() {
        return DbBlogUser.BLOG_USER.PASSWORD;
    }

    @Override
    public Field<String> field3() {
        return DbBlogUser.BLOG_USER.PASSWORD_SALT;
    }

    @Override
    public Field<Integer> field4() {
        return DbBlogUser.BLOG_USER.VERSION;
    }

    @Override
    public Field<OffsetDateTime> field5() {
        return DbBlogUser.BLOG_USER.CREATION_TIME;
    }

    @Override
    public Field<OffsetDateTime> field6() {
        return DbBlogUser.BLOG_USER.LAST_MODIFICATION_TIME;
    }

    @Override
    public String component1() {
        return getUsername();
    }

    @Override
    public String component2() {
        return getPassword();
    }

    @Override
    public String component3() {
        return getPasswordSalt();
    }

    @Override
    public Integer component4() {
        return getVersion();
    }

    @Override
    public OffsetDateTime component5() {
        return getCreationTime();
    }

    @Override
    public OffsetDateTime component6() {
        return getLastModificationTime();
    }

    @Override
    public String value1() {
        return getUsername();
    }

    @Override
    public String value2() {
        return getPassword();
    }

    @Override
    public String value3() {
        return getPasswordSalt();
    }

    @Override
    public Integer value4() {
        return getVersion();
    }

    @Override
    public OffsetDateTime value5() {
        return getCreationTime();
    }

    @Override
    public OffsetDateTime value6() {
        return getLastModificationTime();
    }

    @Override
    public DbBlogUserRecord value1(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public DbBlogUserRecord value2(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public DbBlogUserRecord value3(String value) {
        setPasswordSalt(value);
        return this;
    }

    @Override
    public DbBlogUserRecord value4(Integer value) {
        setVersion(value);
        return this;
    }

    @Override
    public DbBlogUserRecord value5(OffsetDateTime value) {
        setCreationTime(value);
        return this;
    }

    @Override
    public DbBlogUserRecord value6(OffsetDateTime value) {
        setLastModificationTime(value);
        return this;
    }

    @Override
    public DbBlogUserRecord values(String value1, String value2, String value3, Integer value4, OffsetDateTime value5, OffsetDateTime value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    @Override
    public void from(DbIBlogUser from) {
        setUsername(from.getUsername());
        setPassword(from.getPassword());
        setPasswordSalt(from.getPasswordSalt());
        setVersion(from.getVersion());
        setCreationTime(from.getCreationTime());
        setLastModificationTime(from.getLastModificationTime());
    }

    @Override
    public <E extends DbIBlogUser> E into(E into) {
        into.from(this);
        return into;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DbBlogUserRecord
     */
    public DbBlogUserRecord() {
        super(DbBlogUser.BLOG_USER);
    }

    /**
     * Create a detached, initialised DbBlogUserRecord
     */
    public DbBlogUserRecord(String username, String password, String passwordSalt, Integer version, OffsetDateTime creationTime, OffsetDateTime lastModificationTime) {
        super(DbBlogUser.BLOG_USER);

        set(0, username);
        set(1, password);
        set(2, passwordSalt);
        set(3, version);
        set(4, creationTime);
        set(5, lastModificationTime);
    }

    public DbBlogUserRecord(io.vertx.core.json.JsonObject json) {
        this();
        fromJson(json);
    }
}
