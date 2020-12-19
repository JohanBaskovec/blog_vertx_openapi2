package com.example.starter;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.impl.UserImpl;
import org.openapitools.vertxweb.server.model.Role;

import java.util.Date;
import java.util.List;

public class AppUser extends UserImpl {
  public AppUser(String username) {
    super(
      new JsonObject().put("username", username),
      new JsonObject().put("lastFetch", new Date().getTime())
    );
  }

  String getUsername() {
    return this.principal().getString("username");
  }

}
