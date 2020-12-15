package com.example.starter.db;

import jooq.tables.pojos.DbArticle;
import org.apache.commons.beanutils.PropertyUtils;
import org.openapitools.vertxweb.server.model.Article;

public class ArticleMapper {
  public Article fromDb(DbArticle dbArticle) {
    Article article = new Article();
    try {
      PropertyUtils.copyProperties(article, dbArticle);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return article;
  }
}
