package com.sportingevents.team;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeamService {

    List<TeamEntity> getAllTeams(Pageable pageable);
    TeamEntity getTeam(Integer teamId);

    List<TeamEntity> getTeams(String teamIds);

    String saveTeam(TeamRequestModel teamRequestModel);

    String updateTeam(Integer teamId, TeamRequestModel teamRequestModel);

    String deleteTeam(Integer teamId);


}
