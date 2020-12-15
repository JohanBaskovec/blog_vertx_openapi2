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

    public static Function<Row,jooq.tables.pojos.DbUser> getDbUserMapper() {
        return row -> {
            jooq.tables.pojos.DbUser pojo = new jooq.tables.pojos.DbUser();
            pojo.setUsername(row.getString("username"));
            pojo.setPassword(row.getString("password"));
            pojo.setPasswordSalt(row.getString("password_salt"));
            pojo.setVersion(row.getInteger("version"));
            return pojo;
        };
    }

    public static Function<Row,jooq.tables.pojos.Permission> getPermissionMapper() {
        return row -> {
            jooq.tables.pojos.Permission pojo = new jooq.tables.pojos.Permission();
            pojo.setId(row.getString("id"));
            return pojo;
        };
    }

    public static Function<Row,jooq.tables.pojos.Role> getRoleMapper() {
        return row -> {
            jooq.tables.pojos.Role pojo = new jooq.tables.pojos.Role();
            pojo.setId(row.getString("id"));
            return pojo;
        };
    }

    public static Function<Row,jooq.tables.pojos.UserRoles> getUserRolesMapper() {
        return row -> {
            jooq.tables.pojos.UserRoles pojo = new jooq.tables.pojos.UserRoles();
            pojo.setUsername(row.getString("username"));
            pojo.setRole(row.getString("role"));
            return pojo;
        };
    }

}
