package com.example.starter;

import jooq.tables.interfaces.DbIBlogUser;
import jooq.tables.pojos.DbBlogUser;
import org.openapitools.vertxweb.server.model.RoleAuthorization;

import java.util.ArrayList;
import java.util.List;

public class DbBlogUserWithPermissions extends DbBlogUser {
  private List<RoleAuthorization> authorizations = new ArrayList<>();

  public DbBlogUserWithPermissions(DbIBlogUser value) {
    super(value);
  }

  public List<RoleAuthorization> getAuthorizations() {
    return authorizations;
  }

  public void setAuthorizations(List<RoleAuthorization> authorizations) {
    this.authorizations = authorizations;
  }
}
