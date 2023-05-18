package com.sportingevents.common.constant;


import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiEndpoint {
    public static final String API = "/api";

    public static final String VERSION = "/v1";

    public static final String TICKETS = "/tickets";

    public static final String MATCHES = "/matches";

    public static final String ID = "id";

    public static final String ID_PATH_VARIABLE = "/{" + ID + "}";

    public static final String FIELDS = "/fields";

    public static final String TOURNAMENTS = "/tournaments";

}
