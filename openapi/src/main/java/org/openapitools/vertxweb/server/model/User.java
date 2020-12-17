package org.openapitools.vertxweb.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.vertxweb.server.model.Role;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User   {
  
  private String username;
  private String password;
  private Integer version;
  private List<Role> roles = new ArrayList<>();

  public User () {

  }

  public User (String username, String password, Integer version, List<Role> roles) {
    this.username = username;
    this.password = password;
    this.version = version;
    this.roles = roles;
  }

    
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
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

    
  @JsonProperty("roles")
  public List<Role> getRoles() {
    return roles;
  }
  public void setRoles(List<Role> roles) {
    this.roles = roles;
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
        Objects.equals(password, user.password) &&
        Objects.equals(version, user.version) &&
        Objects.equals(roles, user.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, version, roles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
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
