package org.openapitools.vertxweb.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openapitools.vertxweb.server.model.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article   {
  
  private String id;
  private String title;
  private String content;
  private User author;
  private Integer version;

  public Article () {

  }

  public Article (String id, String title, String content, User author, Integer version) {
    this.id = id;
    this.title = title;
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

    
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
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
    Article article = (Article) o;
    return Objects.equals(id, article.id) &&
        Objects.equals(title, article.title) &&
        Objects.equals(content, article.content) &&
        Objects.equals(author, article.author) &&
        Objects.equals(version, article.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, content, author, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Article {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
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
