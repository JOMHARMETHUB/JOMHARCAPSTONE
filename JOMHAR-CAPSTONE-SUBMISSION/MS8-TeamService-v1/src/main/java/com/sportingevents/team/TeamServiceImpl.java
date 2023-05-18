package com.sportingevents.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<TeamEntity> getAllTeams(Pageable pageable) {
        List<TeamEntity> teams = teamRepository.findByActiveTrue(pageable).getContent();
        if(teams.isEmpty())
            throw new TeamException(TeamMessage.TEAM_NOT_FOUND);
        return teams;
    }

    @Override
    public TeamEntity getTeam(Integer teamId) {
        Optional<TeamEntity> teamEntityOptional = teamRepository.findByTeamIdAndActiveTrue(teamId);
        if(!teamEntityOptional.isPresent())
            throw new TeamException(TeamMessage.TEAM_NOT_FOUND);
        return teamEntityOptional.get();
    }


    @Override
    public List<TeamEntity> getTeams(String teamIds) {
        String[] teamId = teamIds.split(",");
        List<TeamEntity> teamEntities = new ArrayList<>();
        Arrays.stream(teamId).forEach(id -> {
            Optional<TeamEntity> teamEntityOptional = teamRepository.findByTeamIdAndActiveTrue(Integer.parseInt(id));
            if(!teamEntityOptional.isPresent())
                throw new TeamException(TeamMessage.TEAM_NOT_FOUND);
            teamEntities.add(teamEntityOptional.get());
        });
        return teamEntities;
    }

    @Override
    public String saveTeam(TeamRequestModel teamRequestModel) {
        Optional<TeamEntity> teamEntity = teamRepository.findByTeamName(teamRequestModel.getTeamName());
        if(teamEntity.isPresent())
            throw new TeamException(TeamMessage.TEAM_EXISTING);
        TeamEntity team = new TeamEntity();
        team.setTeamName(teamRequestModel.getTeamName());
        teamRepository.saveAndFlush(team);
        return TeamMessage.TEAM_SAVE_SUCCESS;
    }

    @Override
    public String updateTeam(Integer teamId, TeamRequestModel teamRequestModel) {
        Optional<TeamEntity> teamById = teamRepository.findByTeamIdAndActiveTrue(teamId);
        if(!teamById.isPresent())
            throw new TeamException(TeamMessage.TEAM_NOT_FOUND);
        Optional<TeamEntity> teamByName = teamRepository.findByTeamName(teamRequestModel.getTeamName());
        if(teamByName.isPresent())
            throw new TeamException(TeamMessage.TEAM_EXISTING);
        TeamEntity team = new TeamEntity();
        team.setTeamName(teamRequestModel.getTeamName());
        teamRepository.saveAndFlush(team);
        return TeamMessage.TEAM_UPDATE_SUCCESS;
    }

    @Override
    public String deleteTeam(Integer teamId) {
        Optional<TeamEntity> teamById = teamRepository.findByTeamIdAndActiveTrue(teamId);
        if(!teamById.isPresent())
            throw new TeamException(TeamMessage.TEAM_NOT_FOUND);
        TeamEntity team = teamById.get();
        team.setActive(false);
        teamRepository.saveAndFlush(team);
        return TeamMessage.TEAM_DELETE_SUCCESS;
    }
}
