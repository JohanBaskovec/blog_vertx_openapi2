package com.example.generator;

import io.github.jklingsporn.vertx.jooq.generate.VertxGeneratorStrategy;
import org.jooq.meta.Definition;

public class JooqGeneratorStrategy extends VertxGeneratorStrategy {
  @Override
  public String getJavaClassName(Definition definition, Mode mode) {
    return "Db" + super.getJavaClassName(definition, mode);
  }
}
