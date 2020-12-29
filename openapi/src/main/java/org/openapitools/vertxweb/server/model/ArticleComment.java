package org.openapitools.vertxweb.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openapitools.vertxweb.server.model.ArticleCommentAllOf;
import org.openapitools.vertxweb.server.model.ObjectBase;
import org.openapitools.vertxweb.server.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleComment   {
  
  private String id;
  private Long lastModificationTime;
  private Long creationTime;
  private String articleId;
  private String content;
  private User author;
  private Integer version;

  public ArticleComment () {

  }

  public ArticleComment (String id, Long lastModificationTime, Long creationTime, String articleId, String content, User author, Integer version) {
    this.id = id;
    this.lastModificationTime = lastModificationTime;
    this.creationTime = creationTime;
    this.articleId = articleId;
    this.content = content;
    this.author = author;
    this.version = version;
  }

    
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

    
  @JsonProperty("lastModificationTime")
  public Long getLastModificationTime() {
    return lastModificationTime;
  }
  public void setLastModificationTime(Long lastModificationTime) {
    this.lastModificationTime = lastModificationTime;
  }

    
  @JsonProperty("creationTime")
  public Long getCreationTime() {
    return creationTime;
  }
  public void setCreationTime(Long creationTime) {
    this.creationTime = creationTime;
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
    ArticleComment articleComment = (ArticleComment) o;
    return Objects.equals(id, articleComment.id) &&
        Objects.equals(lastModificationTime, articleComment.lastModificationTime) &&
        Objects.equals(creationTime, articleComment.creationTime) &&
        Objects.equals(articleId, articleComment.articleId) &&
        Objects.equals(content, articleComment.content) &&
        Objects.equals(author, articleComment.author) &&
        Objects.equals(version, articleComment.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastModificationTime, creationTime, articleId, content, author, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArticleComment {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lastModificationTime: ").append(toIndentedString(lastModificationTime)).append("\n");
    sb.append("    creationTime: ").append(toIndentedString(creationTime)).append("\n");
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
