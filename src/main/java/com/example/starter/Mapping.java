package com.example.starter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Mapping {
  public static Long localDateTimeToTimeStamp(LocalDateTime localDateTime) {
    if (localDateTime == null) {
      return null;
    }
    return localDateTime.toEpochSecond(ZoneOffset.UTC) * 1000;
  }
}
