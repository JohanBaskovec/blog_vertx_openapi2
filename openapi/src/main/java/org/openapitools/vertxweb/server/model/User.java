package org.openapitools.vertxweb.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.vertxweb.server.model.Authorization;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User   {
  
  private String username;
  private Long lastModificationTime;
  private Long creationTime;
  private String password;
  private Integer version;
  private List<Authorization> authorizations = new ArrayList<>();

  public User () {

  }

  public User (String username, Long lastModificationTime, Long creationTime, String password, Integer version, List<Authorization> authorizations) {
    this.username = username;
    this.lastModificationTime = lastModificationTime;
    this.creationTime = creationTime;
    this.password = password;
    this.version = version;
    this.authorizations = authorizations;
  }

    
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
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

    
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

    
  @JsonProperty("version")
  public Integer getVersion() {
    return version;
  }
  public void setVersion(Integer version) {
    this.version = version;
  }

    
  @JsonProperty("authorizations")
  public List<Authorization> getAuthorizations() {
    return authorizations;
  }
  public void setAuthorizations(List<Authorization> authorizations) {
    this.authorizations = authorizations;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(username, user.username) &&
        Objects.equals(lastModificationTime, user.lastModificationTime) &&
        Objects.equals(creationTime, user.creationTime) &&
        Objects.equals(password, user.password) &&
        Objects.equals(version, user.version) &&
        Objects.equals(authorizations, user.authorizations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, lastModificationTime, creationTime, password, version, authorizations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    lastModificationTime: ").append(toIndentedString(lastModificationTime)).append("\n");
    sb.append("    creationTime: ").append(toIndentedString(creationTime)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    authorizations: ").append(toIndentedString(authorizations)).append("\n");
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
