package org.openapitools.vertxweb.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openapitools.vertxweb.server.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleCommentAllOf   {
  
  private String articleId;
  private String content;
  private User author;
  private Integer version;

  public ArticleCommentAllOf () {

  }

  public ArticleCommentAllOf (String articleId, String content, User author, Integer version) {
    this.articleId = articleId;
    this.content = content;
    this.author = author;
    this.version = version;
  }

    
  @JsonProperty("articleId")
  public String getArticleId() {
    return articleId;
  }
  public void setArticleId(String articleId) {
    this.articleId = articleId;
  }

    
  @JsonProperty("content")
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }

    
  @JsonProperty("author")
  public User getAuthor() {
    return author;
  }
  public void setAuthor(User author) {
    this.author = author;
  }

    
  @JsonProperty("version")
  public Integer getVersion() {
    return version;
  }
  public void setVersion(Integer version) {
    this.version = version;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArticleCommentAllOf articleCommentAllOf = (ArticleCommentAllOf) o;
    return Objects.equals(articleId, articleCommentAllOf.articleId) &&
        Objects.equals(content, articleCommentAllOf.content) &&
        Objects.equals(author, articleCommentAllOf.author) &&
        Objects.equals(version, articleCommentAllOf.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(articleId, content, author, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArticleCommentAllOf {\n");
    
    sb.append("    articleId: ").append(toIndentedString(articleId)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
