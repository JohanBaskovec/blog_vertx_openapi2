package com.example.starter;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.impl.UserImpl;

public class AppUser extends UserImpl {
  public AppUser(String username) {
    super(new JsonObject().put("username", username));
  }

  String getUsername() {
    return this.principal().getString("username");
  }
}
