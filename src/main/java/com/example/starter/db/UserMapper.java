package com.example.starter.db;

import jooq.tables.pojos.DbBlogUser;
import org.apache.commons.beanutils.PropertyUtils;
import org.openapitools.vertxweb.server.model.User;

public class UserMapper {
  public User fromDb(DbBlogUser dbBlogUser) {
    User user = new User();
    try {
      PropertyUtils.copyProperties(user, dbBlogUser);
    } catch (Throwable t) {
      throw new RuntimeException(t);
    }
    return user;
  }
}