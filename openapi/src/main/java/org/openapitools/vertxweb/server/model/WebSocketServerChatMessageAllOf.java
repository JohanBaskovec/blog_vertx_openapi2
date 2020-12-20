package org.openapitools.vertxweb.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebSocketServerChatMessageAllOf   {
  
  private String sender;
  private String message;

  public WebSocketServerChatMessageAllOf () {

  }

  public WebSocketServerChatMessageAllOf (String sender, String message) {
    this.sender = sender;
    this.message = message;
  }

    
  @JsonProperty("sender")
  public String getSender() {
    return sender;
  }
  public void setSender(String sender) {
    this.sender = sender;
  }

    
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WebSocketServerChatMessageAllOf webSocketServerChatMessageAllOf = (WebSocketServerChatMessageAllOf) o;
    return Objects.equals(sender, webSocketServerChatMessageAllOf.sender) &&
        Objects.equals(message, webSocketServerChatMessageAllOf.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sender, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WebSocketServerChatMessageAllOf {\n");
    
    sb.append("    sender: ").append(toIndentedString(sender)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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
