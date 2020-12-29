package com.example.starter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class Mapping {
  public static Long offsetDateTimeToTimeStamp(OffsetDateTime dateTime) {
    if (dateTime == null) {
      return null;
    }
    return dateTime.toEpochSecond() * 1000;
  }
}
