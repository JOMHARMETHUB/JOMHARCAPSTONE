package com.sportingevents.team;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TeamServiceTest {

    @InjectMocks
    private TeamServiceImpl teamService;

    @Mock
    private TeamRepository teamRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGetAllTeams_returnTeams() {
        when(teamRepository.findByActiveTrue(any(Pageable.class))).thenReturn(getPageTeams());
        List<TeamEntity> result = teamService.getAllTeams(mock(Pageable.class));
        Assert.assertNotNull(result);
    }

    @Test
    public void givenEmptyResult_whenGetAllTeams_returnTeams() {
        exception.expect(TeamException.class);
        exception.expectMessage(TeamMessage.TEAM_NOT_FOUND);
        when(teamRepository.findByActiveTrue(any(Pageable.class))).thenReturn(Page.empty());
        teamService.getAllTeams(mock(Pageable.class));
    }

    @Test
    public void givenValidTeamId_whenGetTeam_returnTeam() {
        when(teamRepository.findByTeamIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getTeamEntity()));
        TeamEntity result = teamService.getTeam(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void givenNotExistingTeam_whenGetTeam_returnMessage() {
        exception.expect(TeamException.class);
        exception.expectMessage(TeamMessage.TEAM_NOT_FOUND);
        when(teamRepository.findByTeamIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.empty());
        teamService.getTeam(1);
    }

    @Test
    public void givenValidTeamIds_whenGetTeams_returnTeams() {
        when(teamRepository.findByTeamIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.of(getTeamEntity()), Optional.of(getTeamEntity()));
        List<TeamEntity> result = teamService.getTeams("1,2");
        Assert.assertNotNull(result);
    }

    @Test
    public void givenNotExistingTeamId_whenGetTeams_returnMessage() {
        exception.expect(TeamException.class);
        exception.expectMessage(TeamMessage.TEAM_NOT_FOUND);
        when(teamRepository.findByTeamIdAndActiveTrue(any(Integer.class))).thenReturn(Optional.empty());
        teamService.getTeams("1");
    }

    @Test
    public void whenSaveTeam_returnMessage() {
        when(teamRepository.findByTeamName(anyString())).thenReturn(Optional.empty());
        String result = teamService.saveTeam(getTeamRequestBody());
        Assert.assertEquals(TeamMessage.TEAM_SAVE_SUCCESS, result);
    }

    @Test
    public void givenExistingTeam_returnMessage() {
        exception.expect(TeamException.class);
        exception.expectMessage(TeamMessage.TEAM_EXISTING);
        when(teamRepository.findByTeamName(anyString())).thenReturn(Optional.of(getTeamEntity()));
        teamService.saveTeam(getTeamRequestBody());
    }

    @Test
    public void whenUpdateTeam_returnMessage() {
        when(teamRepository.findByTeamIdAndActiveTrue(anyInt())).thenReturn(Optional.of(getTeamEntity()));
        when(teamRepository.findByTeamName(anyString())).thenReturn(Optional.empty());
        String result = teamService.updateTeam(1, getTeamRequestBody());
        Assert.assertEquals(TeamMessage.TEAM_UPDATE_SUCCESS, result);
    }

    @Test
    public void givenTeamNotExisting_whenUpdateTeam_returnMessage() {
        exception.expect(TeamException.class);
        exception.expectMessage(TeamMessage.TEAM_NOT_FOUND);
        when(teamRepository.findByTeamIdAndActiveTrue(anyInt())).thenReturn(Optional.empty());
        when(teamRepository.findByTeamName(anyString())).thenReturn(Optional.empty());
        teamService.updateTeam(1, getTeamRequestBody());
    }

    @Test
    public void givenTeamNameExisting_whenUpdateTeam_returnMessage() {
        exception.expect(TeamException.class);
        exception.expectMessage(TeamMessage.TEAM_EXISTING);
        when(teamRepository.findByTeamIdAndActiveTrue(anyInt())).thenReturn(Optional.of(getTeamEntity()));
        when(teamRepository.findByTeamName(anyString())).thenReturn(Optional.of(getTeamEntity()));
        teamService.updateTeam(1, getTeamRequestBody());
    }

    @Test
    public void whenDeleteTeam_returnMessage() {
        when(teamRepository.findByTeamIdAndActiveTrue(anyInt())).thenReturn(Optional.of(getTeamEntity()));
        String result = teamService.deleteTeam(1);
        Assert.assertEquals(TeamMessage.TEAM_DELETE_SUCCESS, result);
    }

    @Test
    public void givenTeamIsNotExisting_whenDeleteTeam_returnMessage() {
        exception.expect(TeamException.class);
        exception.expectMessage(TeamMessage.TEAM_NOT_FOUND);
        when(teamRepository.findByTeamIdAndActiveTrue(anyInt())).thenReturn(Optional.empty());
        teamService.deleteTeam(1);
    }

    private Page<TeamEntity> getPageTeams() {
        Pageable pageable = PageRequest.of(0, 10);
        List<TeamEntity> teams = getTeams();
        int start = Math.min((int)pageable.getOffset(), teams.size());
        int end = Math.min((start + pageable.getPageSize()), teams.size());
        Page<TeamEntity> teamEntityPage = new PageImpl<>(teams.subList(start, end), pageable, teams.size());
        return teamEntityPage;
    }

    private TeamRequestModel getTeamRequestBody() {
        TeamRequestModel team = new TeamRequestModel();
        team.setTeamName("test");
        return team;
    }

    private List<TeamEntity> getTeams() {
        List<TeamEntity> teams = new ArrayList<>();
        for(int x=0;x<2;x++) {
            TeamEntity team = new TeamEntity();
            team.setTeamName("test");
            team.setTeamId(1);
            teams.add(team);
        }
        return teams;
    }

    private TeamEntity getTeamEntity() {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setTeamName("test");
        teamEntity.setTeamId(1);
        return teamEntity;
    }
}
