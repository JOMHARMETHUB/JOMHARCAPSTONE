package com.sportingevents.common.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class NameUtil {
    public static boolean isValidName(String name) {
        String regex = "^[a-zA-Z\\\\]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
