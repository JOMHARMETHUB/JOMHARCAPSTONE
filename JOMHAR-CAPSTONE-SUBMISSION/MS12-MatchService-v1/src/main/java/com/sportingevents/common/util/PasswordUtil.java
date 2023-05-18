package com.sportingevents.common.util;


import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class PasswordUtil {
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*\\d)"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[$&+,:;=?@#|'<>.^*()%!-])"
                + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
