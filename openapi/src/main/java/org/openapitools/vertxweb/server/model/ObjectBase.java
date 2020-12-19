package org.openapitools.vertxweb.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectBase   {
  
  private String id;
  private Long lastModificationTime;
  private Long creationTime;

  public ObjectBase () {

  }

  public ObjectBase (String id, Long lastModificationTime, Long creationTime) {
    this.id = id;
    this.lastModificationTime = lastModificationTime;
    this.creationTime = creationTime;
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjectBase objectBase = (ObjectBase) o;
    return Objects.equals(id, objectBase.id) &&
        Objects.equals(lastModificationTime, objectBase.lastModificationTime) &&
        Objects.equals(creationTime, objectBase.creationTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastModificationTime, creationTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjectBase {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lastModificationTime: ").append(toIndentedString(lastModificationTime)).append("\n");
    sb.append("    creationTime: ").append(toIndentedString(creationTime)).append("\n");
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
