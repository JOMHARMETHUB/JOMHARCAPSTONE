package com.sportingevents.tournament;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TournamentResponseModel {


    @NonNull
    private Integer tournamentId;

    @NonNull
    private String sportsCategory;


    @NonNull
    private String tournamentStyle;

    @NonNull
    private String tournamentName;

    @NonNull
    private Boolean active;
}
