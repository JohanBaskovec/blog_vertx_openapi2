package com.example.starter;

import com.example.starter.db.ArticleMapper;
import com.example.starter.db.UserMapper;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.impl.HttpStatusException;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;
import io.vertx.pgclient.PgPool;
import jooq.tables.daos.DbArticleDao;
import jooq.tables.daos.DbBlogUserDao;
import jooq.tables.pojos.DbArticle;
import org.jooq.Configuration;
import org.openapitools.vertxweb.server.model.Article;
import org.openapitools.vertxweb.server.model.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ArticleController {
  final private Configuration configuration;
  final private PgPool pool;
  final private UserMapper userMapper = new UserMapper();
  final private ArticleMapper articleMapper = new ArticleMapper();

  public ArticleController(Configuration configuration, PgPool pool) {
    this.configuration = configuration;
    this.pool = pool;
  }

  public void insertArticle(RoutingContext routingContext) {
    RequestParameters params = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
    JsonObject body = params.body().getJsonObject();
    DbArticleDao dao = new DbArticleDao(configuration, pool);
    DbArticle dbArticle = new DbArticle(body);
    AppUser currentSessionUser = (AppUser) routingContext.user();
    dbArticle.setAuthorId(currentSessionUser.getUsername());

    dao.insert(dbArticle).onSuccess((Integer success) -> {
      routingContext.response().setStatusCode(204).end();
    }).onFailure(routingContext::fail);
  }

  public void updateArticle(RoutingContext routingContext) {
    RequestParameters params = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);

    JsonObject body = params.body().getJsonObject();
    Article article = body.mapTo(Article.class);
    DbArticleDao dao = new DbArticleDao(configuration, pool);
    dao.findOneById(article.getId()).compose(dbArticle -> {
      dbArticle.setContent(article.getContent());
      dbArticle.setTitle(article.getTitle());
      dbArticle.setLastModificationTime(LocalDateTime.now());
      return dao.update(dbArticle);
    }).onSuccess((Integer i) -> routingContext.response().setStatusCode(204).end())
      .onFailure(routingContext::fail);
  }

  public void getAllArticles(RoutingContext routingContext) {
    DbArticleDao dao = new DbArticleDao(configuration, pool);
    Holder<List<DbArticle>> dbArticlesHolder = new Holder<>();
    dao.findAll().compose((List<DbArticle> dbArticles) -> {
      dbArticlesHolder.value = dbArticles;
      Set<String> authorIds = dbArticles.stream().map(DbArticle::getAuthorId).collect(Collectors.toSet());
      DbBlogUserDao dbBlogUserDao = new DbBlogUserDao(configuration, pool);
      return dbBlogUserDao.findManyByIds(authorIds);
    }).compose(dbBlogUsers -> {
      Map<String, User> userMap = dbBlogUsers.stream()
        .map(userMapper::fromDb)
        .collect(Collectors.toMap(User::getUsername, user -> user));

      List<Article> articles = new ArrayList<>();
      for (DbArticle dbArticle : dbArticlesHolder.value) {
        Article article = articleMapper.fromDb(dbArticle);
        article.setAuthor(userMap.get(dbArticle.getAuthorId()));
        articles.add(article);
      }
      return Future.succeededFuture(articles);
    }).onSuccess(routingContext::json)
      .onFailure(routingContext::fail);
  }

  public void getArticleById(RoutingContext routingContext) {
    DbArticleDao dao = new DbArticleDao(configuration, pool);
    RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
    String id = requestParameters.pathParameter("id").getString();
    Holder<DbArticle> dbArticleHolder = new Holder<>();
    dao.findOneById(id).compose((DbArticle dbArticle) -> {
      if (dbArticle == null) {
        throw new HttpStatusException(404);
      }
      dbArticleHolder.value = dbArticle;
      String authorId = dbArticle.getAuthorId();
      DbBlogUserDao dbBlogUserDao = new DbBlogUserDao(configuration, pool);
      return dbBlogUserDao.findOneById(authorId);
    }).compose(dbBlogUser -> {
      User user = null;
      if (dbBlogUser != null) {
        user = userMapper.fromDb(dbBlogUser);
      }
      Article article = articleMapper.fromDb(dbArticleHolder.value);
      article.setAuthor(user);
      return Future.succeededFuture(article);
    }).onSuccess(routingContext::json)
      .onFailure(routingContext::fail);
  }

  public void getAllArticlesOfUser(RoutingContext routingContext) {
    DbArticleDao dao = new DbArticleDao(configuration, pool);
    RequestParameters requestParameters = routingContext.get(ValidationHandler.REQUEST_CONTEXT_KEY);
    String username = requestParameters.pathParameter("username").getString();
    dao.findManyByDbAuthorId(Collections.singletonList(username)).compose((List<DbArticle> dbArticles) -> {
      List<Article> articles = dbArticles.stream().map(articleMapper::fromDb).collect(Collectors.toList());
      return Future.succeededFuture(articles);
    }).onSuccess(routingContext::json)
      .onFailure(routingContext::fail);
  }
}
