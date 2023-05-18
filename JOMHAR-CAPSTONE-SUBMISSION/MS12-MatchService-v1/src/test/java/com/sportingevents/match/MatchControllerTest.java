package com.sportingevents.match;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportingevents.common.constant.ApiEndpoint;
import com.sportingevents.field.FieldResponseModel;
import com.sportingevents.field.FieldRestService;
import com.sportingevents.player.PlayerResponseModel;
import com.sportingevents.player.PlayerRestService;
import com.sportingevents.team.TeamResponseModel;
import com.sportingevents.team.TeamRestService;
import com.sportingevents.tournament.TournamentResponseModel;
import com.sportingevents.tournament.TournamentRestService;
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
public class MatchControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private MatchController matchController;

    @Mock
    private MatchServiceImpl matchService;

    @Mock
    private TeamRestService teamRestService;

    @Mock
    private PlayerRestService playerRestService;

    @Mock
    private FieldRestService fieldRestService;

    @Mock
    private TournamentRestService tournamentRestService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(matchController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void givenAuthenticatedUser_whenFindAllMatches_returnMatches() throws Exception {
        List<MatchResponseModel> matches = getMatchResponseModels();
        when(matchService.findAllMatches(any(Pageable.class))).thenReturn(matches);
        mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.MATCHES))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(matches)));
    }

    @Test
    public void givenAuthenticatedUser_whenFindMatch_returnMatch() throws Exception {
        MatchResponseModel match = getMatchResponseModel();
        when(matchService.findMatch(any(Integer.class))).thenReturn(match);
        mockMvc.perform(MockMvcRequestBuilders.get(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.MATCHES + ApiEndpoint.ID_PATH_VARIABLE, "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(match)));
    }

    @Test
    public void givenAuthenticatedUser_whenSaveMatch_returnMessage() throws Exception {
        MatchRequestModel match = getMatchRequestModel();
        when(matchService.saveMatch(any(MatchRequestModel.class))).thenReturn(MatchMessage.MATCH_SAVE_SUCCESS);
        when(playerRestService.getPlayers(anyString(), anyString())).thenReturn(ResponseEntity.ok(getPlayers()));
        when(teamRestService.getTeams(anyString(), anyString())).thenReturn(ResponseEntity.ok(getTeams()));
        when(fieldRestService.getField(anyInt(), anyString())).thenReturn(ResponseEntity.ok(getField()));
        when(tournamentRestService.getTournament(anyInt(), anyString())).thenReturn(ResponseEntity.ok(getTournament()));
        mockMvc.perform(MockMvcRequestBuilders.post(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.MATCHES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(match))
                        .header(HttpHeaders.AUTHORIZATION, "test"))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().string(MatchMessage.MATCH_SAVE_SUCCESS));
    }

    @Test
    public void givenAuthenticatedUser_whenUpdateMatch_returnMessage() throws Exception {
        MatchRequestModel match = getMatchRequestModel();
        when(matchService.updateMatch(any(Integer.class), any(MatchRequestModel.class))).thenReturn(MatchMessage.MATCH_UPDATE_SUCCESS);
        when(playerRestService.getPlayers(anyString(), anyString())).thenReturn(ResponseEntity.ok(getPlayers()));
        when(teamRestService.getTeams(anyString(), anyString())).thenReturn(ResponseEntity.ok(getTeams()));
        when(fieldRestService.getField(anyInt(), anyString())).thenReturn(ResponseEntity.ok(getField()));
        when(tournamentRestService.getTournament(anyInt(), anyString())).thenReturn(ResponseEntity.ok(getTournament()));
        mockMvc.perform(MockMvcRequestBuilders.put(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.MATCHES + ApiEndpoint.ID_PATH_VARIABLE, "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(match))
                        .header(HttpHeaders.AUTHORIZATION, "test"))
                .andExpect(status().isOk())
                .andExpect(content().string(MatchMessage.MATCH_UPDATE_SUCCESS));
    }

    @Test
    public void givenAuthenticatedUser_whenDeleteMatch_returnMessage() throws Exception {
        when(matchService.deleteMatch(any(Integer.class))).thenReturn(MatchMessage.MATCH_DELETE_SUCCESS);
        mockMvc.perform(MockMvcRequestBuilders.delete(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.MATCHES + ApiEndpoint.ID_PATH_VARIABLE, "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(MatchMessage.MATCH_DELETE_SUCCESS));
    }

    private MatchRequestModel getMatchRequestModel() {
        MatchRequestModel matchRequestModel = new MatchRequestModel();
        matchRequestModel.setDateTime("2099-05-03 11:43:15");
        matchRequestModel.setFieldId(1);
        matchRequestModel.setTournamentId(1);
        matchRequestModel.setFieldId(1);
        matchRequestModel.setParticipantsId("1,2");
        matchRequestModel.setTeamsId("1,2");
        return matchRequestModel;
    }


    private List<MatchResponseModel> getMatchResponseModels() {
        List<MatchResponseModel> matches = new ArrayList<>();
        for(int x=0;x<2;x++) {
            MatchResponseModel match = getMatchResponseModel();
            match.setMatchId(x+1);
            matches.add(match);
        }
        return matches;
    }

    private MatchResponseModel getMatchResponseModel() {
        MatchResponseModel matchResponseModel = new MatchResponseModel();
        matchResponseModel.setDateTime("2099-05-03 11:43:15");
        matchResponseModel.setMatchId(1);
        matchResponseModel.setTournamentId(1);
        matchResponseModel.setFieldId(1);
        return matchResponseModel;
    }

    private FieldResponseModel getField() {
        FieldResponseModel fieldEntity = new FieldResponseModel();
        fieldEntity.setFieldAddress("test");
        fieldEntity.setActive(true);
        fieldEntity.setFieldId(1);
        fieldEntity.setCapacity(10);
        fieldEntity.setFieldName("test");
        return fieldEntity;
    }

    private TournamentResponseModel getTournament() {
        TournamentResponseModel tournament = new TournamentResponseModel();
        tournament.setTournamentStyle("test");
        tournament.setTournamentName("test");
        tournament.setTournamentId(1);
        tournament.setActive(true);
        tournament.setSportsCategory("test");
        return tournament;
    }

    private List<PlayerResponseModel> getPlayers() {
        List<PlayerResponseModel> players = new ArrayList<>();
        for(int x=0;x<2;x++) {
            PlayerResponseModel player = new PlayerResponseModel();
            player.setPlayerId(1);
            player.setTeamId(1);
            player.setCountry("test");
            player.setActive(true);
            player.setFirstName("test");
            player.setLastName("test");
            players.add(player);
        }
        return players;
    }

    private List<TeamResponseModel> getTeams() {
        List<TeamResponseModel> teams = new ArrayList<>();
        for(int x=0;x<2;x++) {
            TeamResponseModel team = new TeamResponseModel();
            team.setTeamId(1);
            team.setTeamName("test");
            teams.add(team);
        }
        return teams;
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
