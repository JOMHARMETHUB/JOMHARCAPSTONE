package com.sportingevents.match;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class MatchResponseModel {

    @NotNull
    private Integer matchId;

    @NotNull
    private Integer fieldId;

    @NotNull
    private Integer tournamentId;

    @NotBlank
    @NotNull
    private String teamsId;

    @NotBlank
    @NotNull
    private String participantsId;

    @NotBlank
    @NotNull
    private String dateTime;


}
