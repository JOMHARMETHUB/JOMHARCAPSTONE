package com.sportingevents.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiEndpoint {
    public static final String API = "/api";

    public static final String VERSION = "/v1";

    public static final String LOGIN = "/login";

    public static final String USERS = "/users";

    public static final String VALIDATE_JWT_TOKEN = "/validateJwtToken";

    public static final String GET_EMAIL_FROM_JWT = "/getEmailFromJwt";
}
