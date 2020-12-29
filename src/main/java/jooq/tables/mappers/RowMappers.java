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
            pojo.setCreationTime(row.getOffsetDateTime("creation_time"));
            pojo.setLastModificationTime(row.getOffsetDateTime("last_modification_time"));
            return pojo;
        };
    }

    public static Function<Row,jooq.tables.pojos.DbArticleComment> getDbArticleCommentMapper() {
        return row -> {
            jooq.tables.pojos.DbArticleComment pojo = new jooq.tables.pojos.DbArticleComment();
            pojo.setId(row.getString("id"));
            pojo.setContent(row.getString("content"));
            pojo.setAuthorId(row.getString("author_id"));
            pojo.setVersion(row.getInteger("version"));
            pojo.setCreationTime(row.getOffsetDateTime("creation_time"));
            pojo.setLastModificationTime(row.getOffsetDateTime("last_modification_time"));
            pojo.setArticleId(row.getString("article_id"));
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
            pojo.setCreationTime(row.getOffsetDateTime("creation_time"));
            pojo.setLastModificationTime(row.getOffsetDateTime("last_modification_time"));
            return pojo;
        };
    }

    public static Function<Row,jooq.tables.pojos.DbPermission> getDbPermissionMapper() {
        return row -> {
            jooq.tables.pojos.DbPermission pojo = new jooq.tables.pojos.DbPermission();
            pojo.setId(row.getString("id"));
            pojo.setLastModificationTime(row.getOffsetDateTime("last_modification_time"));
            pojo.setCreationTime(row.getOffsetDateTime("creation_time"));
            return pojo;
        };
    }

    public static Function<Row,jooq.tables.pojos.DbRole> getDbRoleMapper() {
        return row -> {
            jooq.tables.pojos.DbRole pojo = new jooq.tables.pojos.DbRole();
            pojo.setId(row.getString("id"));
            pojo.setLastModificationTime(row.getOffsetDateTime("last_modification_time"));
            pojo.setCreationTime(row.getOffsetDateTime("creation_time"));
            return pojo;
        };
    }

    public static Function<Row,jooq.tables.pojos.DbRolesPermissions> getDbRolesPermissionsMapper() {
        return row -> {
            jooq.tables.pojos.DbRolesPermissions pojo = new jooq.tables.pojos.DbRolesPermissions();
            pojo.setRoleId(row.getString("role_id"));
            pojo.setPermissionId(row.getString("permission_id"));
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
