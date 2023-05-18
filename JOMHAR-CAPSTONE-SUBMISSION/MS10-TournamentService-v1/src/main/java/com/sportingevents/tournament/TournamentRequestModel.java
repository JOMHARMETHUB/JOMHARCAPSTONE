package com.sportingevents.tournament;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TournamentRequestModel {

    @NonNull
    private String sportsCategory;

    @NonNull
    private String tournamentStyle;

    @NonNull
    private String tournamentName;
}
