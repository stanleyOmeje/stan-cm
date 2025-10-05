package com.stan.stancommons.util;

public class StringUtil {

    public static boolean isNotBlank(String str) {
        if (str == null) {
            return false;
        }

        return str.length() > 0;
    }
}
