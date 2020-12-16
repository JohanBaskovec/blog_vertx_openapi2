package jooq.tables.mappers;

import io.vertx.sqlclient.Row;
import java.util.function.Function;

public class RowMappers {

    private RowMappers(){}

    public static Function<Row,jooq.tables.pojos.DbArticle> getDbArticleMapper() {
        return row -> {
            jooq.tables.pojos.DbArticle pojo = new jooq.tables.pojos.DbArticle();
            pojo.setId(row.getString("id"));
            pojo.setTitle(row.getString("title"));
            pojo.setContent(row.getString("content"));
            pojo.setAuthorId(row.getString("author_id"));
            pojo.setVersion(row.getInteger("version"));
            return pojo;
        };
    }

    public static Function<Row,jooq.tables.pojos.DbBlogUser> getDbBlogUserMapper() {
        return row -> {
            jooq.tables.pojos.DbBlogUser pojo = new jooq.tables.pojos.DbBlogUser();
            pojo.setUsername(row.getString("username"));
            pojo.setPassword(row.getString("password"));
            pojo.setPasswordSalt(row.getString("password_salt"));
            pojo.setVersion(row.getInteger("version"));
            return pojo;
        };
    }

    public static Function<Row,jooq.tables.pojos.DbPermission> getDbPermissionMapper() {
        return row -> {
            jooq.tables.pojos.DbPermission pojo = new jooq.tables.pojos.DbPermission();
            pojo.setId(row.getString("id"));
            return pojo;
        };
    }

    public static Function<Row,jooq.tables.pojos.DbRole> getDbRoleMapper() {
        return row -> {
            jooq.tables.pojos.DbRole pojo = new jooq.tables.pojos.DbRole();
            pojo.setId(row.getString("id"));
            return pojo;
        };
    }

    public static Function<Row,jooq.tables.pojos.DbUserRoles> getDbUserRolesMapper() {
        return row -> {
            jooq.tables.pojos.DbUserRoles pojo = new jooq.tables.pojos.DbUserRoles();
            pojo.setUsername(row.getString("username"));
            pojo.setRole(row.getString("role"));
            return pojo;
        };
    }

}