package com.sportingevents.team;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamRequestModel {
    @NonNull
    private String teamName;
}
