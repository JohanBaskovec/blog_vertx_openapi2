package com.example.starter;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.impl.UserImpl;

import java.util.Date;

public class AppUser extends UserImpl {
  public AppUser(String username) {
    super(
      new JsonObject().put("username", username),
      new JsonObject().put("lastFetch", new Date().getTime())
    );
  }

  public String getUsername() {
    return this.principal().getString("username");
  }
}
