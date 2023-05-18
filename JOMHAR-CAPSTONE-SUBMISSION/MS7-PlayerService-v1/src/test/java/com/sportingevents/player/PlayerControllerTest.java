package com.sportingevents.player;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportingevents.common.constant.ApiEndpoint;
import com.sportingevents.team.TeamResponseModel;
import com.sportingevents.team.TeamRestService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PlayerControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PlayerController playerController;

    @Mock
    private PlayerServiceImpl playerService;

    @Mock
    private TeamRestService teamService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(playerController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void givenAuthenticatedUser_whenFindAllPlayers_thenReturnAllPlayers() throws Exception {
        when(playerService.findAllPlayers(any(Pageable.class))).thenReturn(getAllPlayers());
        mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.PLAYERS))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getAllPlayers())));
    }

    @Test
    public void givenAuthenticatedUser_whenFindPlayer_thenReturnPlayer() throws Exception {
        TeamResponseModel team1 = getTeam(1, "firstTeam");
        PlayerResponseModel playerResponseModel = getPlayerResponseModel(1, 1, true, "Philippines", "playeronefn", "playeroneln");
        when(playerService.findPlayer(any(Integer.class))).thenReturn(playerResponseModel);
        mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.PLAYERS + ApiEndpoint.ID_PATH_VARIABLE, "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(playerResponseModel)));
    }

    @Test
    public void givenAuthenticatedUser_whenFindPlayers_thenReturnPlayers() throws Exception {
        TeamResponseModel team1 = getTeam(1, "firstTeam");
        List<PlayerResponseModel> playerResponseModelList = new ArrayList<>();
        PlayerResponseModel playerResponseModel1 = getPlayerResponseModel(1, 1, true, "Philippines", "playeronefn", "playeroneln");
        PlayerResponseModel playerResponseModel2 = getPlayerResponseModel(2, 1, true, "Philippines", "playertwofn", "playertwoln");
        playerResponseModelList.add(playerResponseModel1);
        playerResponseModelList.add(playerResponseModel2);
        when(playerService.findPlayers(any(String.class))).thenReturn(playerResponseModelList);
        mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.PLAYERS + ApiEndpoint.MULTIPLE + "?playerIds=1,2"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(playerResponseModelList)));
    }

    @Test
    public void givenAuthenticatedUserWithValidRequestBody_whenSavePlayer_thenReturnMessage() throws Exception {
        PlayerRequestModel playerRequestModel = getPlayerRequestModel(1, "country", "newplayer", "newplayerfn");
        when(teamService.getTeam(anyInt(), anyString())).thenReturn(ResponseEntity.ok(getTeam(1, "team")));
        when(playerService.savePlayer(any(PlayerRequestModel.class))).thenReturn(PlayerMessage.PLAYER_SAVE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.post(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.PLAYERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(playerRequestModel))
                        .header(HttpHeaders.AUTHORIZATION, "test"))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().string(PlayerMessage.PLAYER_SAVE_SUCCESS));
    }

    @Test
    public void givenAuthenticatedUserWithValidRequestBody_whenUpdatePlayer_thenReturnMessage() throws Exception {
        PlayerRequestModel playerRequestModel = getPlayerRequestModel(1, "country", "newplayer", "newplayerfn");
        when(playerService.updatePlayer(any(Integer.class), any(PlayerRequestModel.class))).thenReturn(PlayerMessage.PLAYER_UPDATE_SUCCESS);
        when(teamService.getTeam(anyInt(), anyString())).thenReturn(ResponseEntity.ok(getTeam(1, "team")));
        mockMvc.perform(MockMvcRequestBuilders.put(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.PLAYERS + ApiEndpoint.ID_PATH_VARIABLE, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(playerRequestModel))
                        .header(HttpHeaders.AUTHORIZATION, "test"))
                .andExpect(status().isOk())
                .andExpect(content().string(PlayerMessage.PLAYER_UPDATE_SUCCESS));
    }

    @Test
    public void givenAuthenticatedUser_whenDeletePlayer_thenReturnMessage() throws Exception {
        when(playerService.deletePlayer(any(Integer.class))).thenReturn(PlayerMessage.PLAYER_DELETE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.delete(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.PLAYERS + ApiEndpoint.ID_PATH_VARIABLE, "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(PlayerMessage.PLAYER_DELETE_SUCCESS));
    }

    private PlayerRequestModel getPlayerRequestModel(Integer teamId, String country, String firstName, String lastName) {
        PlayerRequestModel playerRequestModel = new PlayerRequestModel();
        playerRequestModel.setTeamId(teamId);
        playerRequestModel.setCountry(country);
        playerRequestModel.setFirstName(firstName);
        playerRequestModel.setLastName(lastName);
        return playerRequestModel;
    }

    private List<PlayerResponseModel> getAllPlayers() {
        List<PlayerResponseModel> players = new ArrayList<>();
        TeamResponseModel team1 = getTeam(1, "firstTeam");
        TeamResponseModel team2 = getTeam(2, "secondTeam");
        PlayerResponseModel playerEntity1 = getPlayer(1, "Philippines", 1, true, "playeronefn", "playeroneln");
        PlayerResponseModel playerEntity2 = getPlayer(2, "Philippines", 2, true, "playertwofn", "playertwoln");
        PlayerResponseModel playerEntity3 = getPlayer(3, "Philippines", 1, true, "playerthreefn", "playerthreeln");
        PlayerResponseModel playerEntity4 = getPlayer(4, "Philippines", 2, true, "playerfourfn", "playerfourln");
        players.add(playerEntity1);
        players.add(playerEntity2);
        players.add(playerEntity3);
        players.add(playerEntity4);
        return players;
    }

    private PlayerResponseModel getPlayer(Integer playerId, String country, Integer teamId, Boolean isActive,
                                    String firstName, String lastName) {
        PlayerResponseModel player = new PlayerResponseModel();
        player.setPlayerId(playerId);
        player.setCountry(country);
        player.setTeamId(teamId);
        player.setActive(isActive);
        player.setFirstName(firstName);
        player.setLastName(lastName);
        return player;
    }

    private PlayerResponseModel getPlayerResponseModel(Integer playerId, Integer teamId, Boolean isActive, String country,
                                                       String firstName, String lastName) {
        PlayerResponseModel playerResponseModel = new PlayerResponseModel();
        playerResponseModel.setPlayerId(playerId);
        playerResponseModel.setTeamId(teamId);
        playerResponseModel.setActive(isActive);
        playerResponseModel.setCountry(country);
        playerResponseModel.setFirstName(firstName);
        playerResponseModel.setLastName(lastName);
        return playerResponseModel;
    }

    private TeamResponseModel getTeam(Integer teamId, String teamName) {
        TeamResponseModel team = new TeamResponseModel();
        team.setTeamName(teamName);
        team.setTeamId(teamId);
        return team;
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
