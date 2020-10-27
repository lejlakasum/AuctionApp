package com.example.auctionapp.Util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimeUtility {

    public static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

    public static Long LocalDateTimeToTimestamp(LocalDateTime dateTime) {

        return dateTime.toInstant(ZONE_OFFSET).toEpochMilli();
    }

    public static LocalDateTime timestampToLocalDateTime(Long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZONE_OFFSET);
    }
}
