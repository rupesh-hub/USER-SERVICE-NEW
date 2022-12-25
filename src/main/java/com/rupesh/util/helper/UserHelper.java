package com.rupesh.util.helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

public class UserHelper {

    public static String generateUserId() {
        return UUID.randomUUID().toString();
    }


    public static String generatePassword() {
        return UUID.randomUUID().toString();
    }

    public static ZonedDateTime toZonedDateTime(final LocalDateTime localDateTime) {
        LocalDateTime ldt = LocalDateTime.of(localDateTime.toLocalDate(), localDateTime.toLocalTime());
        ZoneId zoneId = ZoneId.systemDefault();
        return ldt.atZone(zoneId);
    }

    public static String encodePassword(final String rawPassword) {
        return rawPassword;
    }

}
