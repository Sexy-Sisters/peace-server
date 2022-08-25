package com.example.wakeUp.global.Utils;

import java.time.LocalDateTime;

public class DateUtil {

    public static LocalDateTime getToday() {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(
                now.getYear(),
                now.getMonth(),
                now.getDayOfMonth(),
                0, 0, 0
        );
    }

    public static LocalDateTime getTomorrow() {
        return DateUtil.getToday().plusDays(1L);
    }
}
