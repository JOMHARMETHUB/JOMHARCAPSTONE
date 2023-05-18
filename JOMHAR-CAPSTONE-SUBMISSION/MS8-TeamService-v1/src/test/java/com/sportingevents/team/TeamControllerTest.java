package com.sportingevents.team;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportingevents.common.constant.ApiEndpoint;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private TeamController teamController;

    @Mock
    private TeamServiceImpl teamService;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(teamController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void givenAuthenticatedUser_whenGetTeam_thenReturnTeam() throws Exception {
        when(teamService.getTeam(any(Integer.class))).thenReturn(getTeamEntity());
        mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TEAMS + ApiEndpoint.ID_PATH_VARIABLE, "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getTeamEntity())));
    }

    @Test
    public void givenAuthenticatedUserWithValidTeamIds_whenGetTeam_thenReturnTeams() throws Exception {
        when(teamService.getTeams(any(String.class))).thenReturn(getTeams());
        mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TEAMS + ApiEndpoint.MULTIPLE + "?teamIds=1,2"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getTeams())));
    }

    @Test
    public void givenAuthenticatedUser_whenGetAllTeams_returnTeams() throws Exception{
        when(teamService.getAllTeams(any(Pageable.class))).thenReturn(getTeams());
        mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TEAMS))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getTeams())));
    }

    @Test
    public void givenAuthenticatedUser_whenSaveTeam_returnMessage() throws Exception{
        when(teamService.saveTeam(any(TeamRequestModel.class))).thenReturn(TeamMessage.TEAM_SAVE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.post(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TEAMS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getTeamRequestBody())))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().string(TeamMessage.TEAM_SAVE_SUCCESS));
    }

    @Test
    public void givenAuthenticatedUser_whenUpdateTeam_returnMessage() throws Exception {
        when(teamService.updateTeam(anyInt(), any(TeamRequestModel.class))).thenReturn(TeamMessage.TEAM_UPDATE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.put(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TEAMS + ApiEndpoint.ID_PATH_VARIABLE, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getTeamRequestBody())))
                .andExpect(status().isOk())
                .andExpect(content().string(TeamMessage.TEAM_UPDATE_SUCCESS));
    }

    @Test
    public void givenAuthenticatedUser_whenDeleteTeam_returnMessage() throws Exception {
        when(teamService.deleteTeam(anyInt())).thenReturn(TeamMessage.TEAM_DELETE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.delete(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TEAMS + ApiEndpoint.ID_PATH_VARIABLE, "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(TeamMessage.TEAM_DELETE_SUCCESS));
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

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
