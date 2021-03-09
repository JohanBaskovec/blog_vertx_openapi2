package com.example.starter;

import com.example.starter.db.UserMapper;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import io.vertx.pgclient.PgPool;
import jooq.Tables;
import jooq.tables.daos.DbArticleCommentDao;
import jooq.tables.daos.DbBlogUserDao;
import jooq.tables.pojos.DbArticleComment;
import org.jooq.Configuration;
import org.openapitools.vertxweb.server.model.ArticleComment;
import org.openapitools.vertxweb.server.model.ArticleCommentCreationFormData;
import org.openapitools.vertxweb.server.model.User;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ArticleCommentController {
  final private Configuration configuration;
  final private PgPool pool;
  final private UserMapper userMapper = new UserMapper();

  public ArticleCommentController(Configuration configuration, PgPool pool) {
    this.configuration = configuration;
    this.pool = pool;
  }

  public void insertArticleComment(RoutingContext routingContext) {
    RequestParameters params = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
    JsonObject body = params.body().getJsonObject();
    ArticleCommentCreationFormData form = body.mapTo(ArticleCommentCreationFormData.class);
    DbArticleCommentDao dao = new DbArticleCommentDao(configuration, pool);
    DbArticleComment dbArticleComment = new DbArticleComment();
    dbArticleComment.setId(form.getId());
    dbArticleComment.setArticleId(form.getArticleId());
    dbArticleComment.setContent(form.getContent());
    SessionUser currentSessionUser = (SessionUser) routingContext.user();
    dbArticleComment.setAuthorId(currentSessionUser.getUsername());

    dao.insert(dbArticleComment)
      .onSuccess((Integer success) -> {
        routingContext.response().setStatusCode(204).end();
      })
      .onFailure(routingContext::fail);
  }

  public void updateArticleComment(RoutingContext routingContext) {
    RequestParameters params = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

    JsonObject body = params.body().getJsonObject();
    ArticleComment articleComment = body.mapTo(ArticleComment.class);
    DbArticleCommentDao dao = new DbArticleCommentDao(configuration, pool);
    dao.findOneById(articleComment.getId()).compose(dbArticleComment -> {
      dbArticleComment.setContent(articleComment.getContent());
      dbArticleComment.setLastModificationTime(OffsetDateTime.now());
      return dao.update(dbArticleComment);
    }).onSuccess((Integer i) -> routingContext.response().setStatusCode(204).end())
      .onFailure(routingContext::fail);
  }

  public void getAllCommentsOfArticle(RoutingContext routingContext) {
    RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
    String articleId = requestParameters.pathParameter("articleId").getString();

    DbArticleCommentDao dao = new DbArticleCommentDao(configuration, pool);
    Holder<List<DbArticleComment>> dbArticleCommentsHolder = new Holder<>();

    dao.queryExecutor().findMany(dslContext -> dslContext
      .selectFrom(Tables.ARTICLE_COMMENT)
      .where(Tables.ARTICLE_COMMENT.ARTICLE_ID.eq(articleId))
      .orderBy(Tables.ARTICLE_COMMENT.CREATION_TIME.asc())
    ).compose((List<DbArticleComment> dbArticleComments) -> {
      dbArticleCommentsHolder.value = dbArticleComments;
      Set<String> authorIds = dbArticleComments.stream().map(DbArticleComment::getAuthorId).collect(Collectors.toSet());
      DbBlogUserDao dbBlogUserDao = new DbBlogUserDao(configuration, pool);
      return dbBlogUserDao.findManyByIds(authorIds);
    }).compose(dbBlogUsers -> {
      Map<String, User> userMap = dbBlogUsers.stream()
        .map(userMapper::fromDb)
        .collect(Collectors.toMap(User::getUsername, user -> user));

      List<ArticleComment> articleComments = new ArrayList<>();
      for (DbArticleComment dbArticleComment : dbArticleCommentsHolder.value) {
        ArticleComment articleComment = new ArticleComment();
        try {
          articleComment.setAuthor(userMap.get(dbArticleComment.getAuthorId()));
          articleComment.setId(dbArticleComment.getId());
          articleComment.setContent(dbArticleComment.getContent());
          articleComment.setArticleId(dbArticleComment.getArticleId());
          articleComment.setCreationTime(Mapping.offsetDateTimeToTimeStamp(dbArticleComment.getCreationTime()));
          articleComment.setLastModificationTime(Mapping.offsetDateTimeToTimeStamp(dbArticleComment.getLastModificationTime()));
        } catch (Throwable t) {
          throw new RuntimeException(t);
        }
        articleComments.add(articleComment);
      }
      return Future.succeededFuture(articleComments);
    }).onSuccess(routingContext::json)
      .onFailure(routingContext::fail);
  }
}
