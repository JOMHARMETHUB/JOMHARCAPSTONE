package com.sportingevents.common.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class NumberUtil {
    public static boolean isValidNumber(String number) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }
}
