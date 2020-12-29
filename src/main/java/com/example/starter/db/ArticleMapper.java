package com.example.starter.db;

import com.example.starter.Mapping;
import jooq.tables.pojos.DbArticle;
import org.openapitools.vertxweb.server.model.Article;

public class ArticleMapper {
  public Article fromDb(DbArticle dbArticle) {
    Article article = new Article();
    try {
      article.setId(dbArticle.getId());
      article.setTitle(dbArticle.getTitle());
      article.setContent(dbArticle.getContent());
      article.setCreationTime(Mapping.offsetDateTimeToTimeStamp(dbArticle.getCreationTime()));
      article.setLastModificationTime(Mapping.offsetDateTimeToTimeStamp(dbArticle.getLastModificationTime()));
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return article;
  }
}
