package org.openapitools.vertxweb.server;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import org.openapitools.vertxweb.server.model.*;

public class WebSocketService {
  public WebSocketMessage parseMessage(Buffer message) {
    JsonObject jsonObject = new JsonObject(message);
    String type = jsonObject.getString("type");
    if (type == null) {
      throw new RuntimeException("Invalid message format, no type field.");
    }
    switch (type) {
      case "WebSocketClientChatMessage":
        return jsonObject.mapTo(WebSocketClientChatMessage.class);
      case "WebSocketServerChatMessage":
        return jsonObject.mapTo(WebSocketServerChatMessage.class);
      default:
        throw new RuntimeException("Invalid message format, invalid type field.");
    }
  }

  public WebSocketServerChatMessage newServerChatMessage(
    String sender,
    String message
  ) {
    WebSocketServerChatMessage webSocketServerChatMessage = new WebSocketServerChatMessage(sender, message);
    webSocketServerChatMessage.setType(WebSocketMessage.TypeEnum.WEBSOCKETSERVERCHATMESSAGE);
    return webSocketServerChatMessage;
  }
}
