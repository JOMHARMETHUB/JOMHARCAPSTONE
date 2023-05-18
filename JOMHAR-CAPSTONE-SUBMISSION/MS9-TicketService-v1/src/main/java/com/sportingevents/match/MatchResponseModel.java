package com.sportingevents.match;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class MatchResponseModel {

    @NonNull
    private Integer matchId;

    @NonNull
    private Integer fieldId;

    @NonNull
    private Integer tournamentId;


    @NonNull
    private String teamsId;

    @NonNull
    private String participantsId;

    @NonNull
    private String dateTime;

}
