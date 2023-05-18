package com.sportingevents.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstant {
    public static final String SERVICE_NAME = "player-service";

    public static final String LOGIN_SERVICE_NAME = "MS6-LOGINSERVICE-V1";

    public static final String LOGIN_SERVICE_LOCALHOST_API_GATEWAY_URL = "http://localhost:8088/user-gateway/api/v1/users";

    public static final String TEAM_SERVICE_NAME = "MS8-TEAMSERVICE-V1";

    public static final String TEAM_SERVICE_LOCALHOST_API_GATEWAY_URL = "http://localhost:8088/team-gateway/api/v1/teams";
}
