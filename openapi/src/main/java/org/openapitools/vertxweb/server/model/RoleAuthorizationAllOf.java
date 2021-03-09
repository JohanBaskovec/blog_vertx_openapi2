package org.openapitools.vertxweb.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.vertxweb.server.model.PermissionAuthorization;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleAuthorizationAllOf   {
  
  private String role;
  private List<PermissionAuthorization> permissions = new ArrayList<>();

  public RoleAuthorizationAllOf () {

  }

  public RoleAuthorizationAllOf (String role, List<PermissionAuthorization> permissions) {
    this.role = role;
    this.permissions = permissions;
  }

    
  @JsonProperty("role")
  public String getRole() {
    return role;
  }
  public void setRole(String role) {
    this.role = role;
  }

    
  @JsonProperty("permissions")
  public List<PermissionAuthorization> getPermissions() {
    return permissions;
  }
  public void setPermissions(List<PermissionAuthorization> permissions) {
    this.permissions = permissions;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoleAuthorizationAllOf roleAuthorizationAllOf = (RoleAuthorizationAllOf) o;
    return Objects.equals(role, roleAuthorizationAllOf.role) &&
        Objects.equals(permissions, roleAuthorizationAllOf.permissions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(role, permissions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoleAuthorizationAllOf {\n");
    
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
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
