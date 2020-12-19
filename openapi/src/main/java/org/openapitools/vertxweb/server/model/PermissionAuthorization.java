package org.openapitools.vertxweb.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openapitools.vertxweb.server.model.Authorization;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionAuthorization extends Authorization  {
  
  private String permission;

  public PermissionAuthorization () {

  }

  public PermissionAuthorization (String permission) {
    this.permission = permission;
  }

    
  @JsonProperty("permission")
  public String getPermission() {
    return permission;
  }
  public void setPermission(String permission) {
    this.permission = permission;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PermissionAuthorization permissionAuthorization = (PermissionAuthorization) o;
    return Objects.equals(permission, permissionAuthorization.permission);
  }

  @Override
  public int hashCode() {
    return Objects.hash(permission);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PermissionAuthorization {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    permission: ").append(toIndentedString(permission)).append("\n");
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
