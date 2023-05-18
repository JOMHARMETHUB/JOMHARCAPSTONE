package com.sportingevents.user;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMessage {
    public static final String USER_SAVE_SUCCESS = "User saved successfully.";

    public static final String USER_EMAIL_EXIST = "Email already exists please use another.";

    public static final String USER_PHONE_EXIST = "Phone already exists please use another.";

    public static final String USER_PASSWORD_INVALID = "Password should be a combination of at least an uppercase alphabet, lowercase\n" +
            "alphabets, a digit, and a special character. And it should be at least 8 characters";

    public static final String USER_INVALID_NAME = "Invalid name.";

    public static final String USER_INVALID_PHONE_NUMBER = "Invalid phone number.";
}
