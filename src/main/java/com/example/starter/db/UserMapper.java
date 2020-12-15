package com.example.starter.db;

import jooq.tables.pojos.DbUser;
import org.apache.commons.beanutils.PropertyUtils;
import org.openapitools.vertxweb.server.model.User;

public class UserMapper {
  public User fromDb(DbUser dbUser) {
    User user = new User();
    try {
      PropertyUtils.copyProperties(user, dbUser);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return user;
  }
}
