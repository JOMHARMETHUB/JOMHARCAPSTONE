package com.sportingevents.team;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TeamResponseModel {
    @NonNull
    private Integer teamId;

    @NonNull
    private String teamName;
}
