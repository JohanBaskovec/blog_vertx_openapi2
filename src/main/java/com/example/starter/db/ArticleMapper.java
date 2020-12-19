package com.example.starter.db;

import com.example.starter.Mapping;
import jooq.tables.pojos.DbArticle;
import org.apache.commons.beanutils.PropertyUtils;
import org.openapitools.vertxweb.server.model.Article;

import java.time.ZoneOffset;

public class ArticleMapper {
  public Article fromDb(DbArticle dbArticle) {
    Article article = new Article();
    try {
      article.setId(dbArticle.getId());
      article.setTitle(dbArticle.getTitle());
      article.setContent(dbArticle.getContent());
      article.setCreationTime(Mapping.localDateTimeToTimeStamp(dbArticle.getCreationTime()));
      article.setLastModificationTime(Mapping.localDateTimeToTimeStamp(dbArticle.getLastModificationTime()));
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return article;
  }
}
