package com.sportingevents.player;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class PlayerRequestModel {

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String country;

    @NonNull
    private Integer teamId;
}
