package com.sportingevents.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AppConstant {
    public static final String SERVICE_NAME = "match-service";

    public static final String LOGIN_SERVICE_NAME = "MS6-LOGINSERVICE-V1";

    public static final String LOGIN_SERVICE_LOCALHOST_API_GATEWAY_URL = "http://localhost:8088/user-gateway/api/v1/users";

    public static final String PLAYER_SERVICE_NAME = "MS7-PLAYERSERVICE-V1";

    public static final String PLAYER_SERVICE_LOCALHOST_API_GATEWAY_URL = "http://localhost:8088/player-gateway/api/v1/players";

    public static final String TEAM_SERVICE_NAME = "MS8-TEAMSERVICE-V1";

    public static final String TEAM_SERVICE_LOCALHOST_API_GATEWAY_URL = "http://localhost:8088/team-gateway/api/v1/teams";

    public static final String FIELD_SERVICE_NAME = "MS11-FIELDSERVICE-V1";

    public static final String FIELD_SERVICE_LOCALHOST_API_GATEWAY_URL = "http://localhost:8088/field-gateway/api/v1/fields";

    public static final String TOURNAMENT_SERVICE_NAME = "MS10-TOURNAMENTSERVICE-V1";

    public static final String TOURNAMENT_SERVICE_LOCALHOST_API_GATEWAY_URL = "http://localhost:8088/tournament-gateway/api/v1/tournaments";
}
