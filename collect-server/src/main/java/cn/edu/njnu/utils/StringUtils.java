package cn.edu.njnu.utils;

import java.util.Random;

public class StringUtils {
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer randomSB = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            randomSB.append(base.charAt(number));
        }
        return randomSB.toString();
    }
}
