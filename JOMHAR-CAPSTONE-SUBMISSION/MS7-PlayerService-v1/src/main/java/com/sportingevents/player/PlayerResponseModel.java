package com.sportingevents.player;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class PlayerResponseModel {
    @NonNull
    private Integer playerId;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String country;

    @NonNull
    private Boolean active;

    @NonNull
    private Integer teamId;
}
