package org.openapitools.vertxweb.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.vertxweb.server.model.WebSocketMessage;
import org.openapitools.vertxweb.server.model.WebSocketServerChatMessageAllOf;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebSocketServerChatMessage extends WebSocketMessage  {
  
  private String sender;
  private String message;

  public WebSocketServerChatMessage () {

  }

  public WebSocketServerChatMessage (String sender, String message) {
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
    WebSocketServerChatMessage webSocketServerChatMessage = (WebSocketServerChatMessage) o;
    return Objects.equals(sender, webSocketServerChatMessage.sender) &&
        Objects.equals(message, webSocketServerChatMessage.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sender, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WebSocketServerChatMessage {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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
