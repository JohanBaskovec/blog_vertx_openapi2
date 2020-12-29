package org.openapitools.vertxweb.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleCommentCreationFormData   {
  
  private String id;
  private String articleId;
  private String content;

  public ArticleCommentCreationFormData () {

  }

  public ArticleCommentCreationFormData (String id, String articleId, String content) {
    this.id = id;
    this.articleId = articleId;
    this.content = content;
  }

    
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ArticleCommentCreationFormData articleCommentCreationFormData = (ArticleCommentCreationFormData) o;
    return Objects.equals(id, articleCommentCreationFormData.id) &&
        Objects.equals(articleId, articleCommentCreationFormData.articleId) &&
        Objects.equals(content, articleCommentCreationFormData.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, articleId, content);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ArticleCommentCreationFormData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    articleId: ").append(toIndentedString(articleId)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
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
