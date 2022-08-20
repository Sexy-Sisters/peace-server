package com.example.wakeUp.global.Utils;

import java.util.Random;

public class RandomUtil {

    private static final int MIN = 97;
    private static final int MAX = 122;
    private static final int CODE_LENGTH = 16;
    private static final Random random = new Random();

    public static String issue() {
        return random.ints(MIN, MAX+1)
                .limit(CODE_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
