package com.sportingevents.player;

import com.sportingevents.team.TeamResponseModel;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PlayerServiceTest {

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenFindAllPlayers_thenReturnAllPlayers() {
        Pageable pageable = PageRequest.of(0, 10);
        when(playerRepository.findByActiveTrue(any(Pageable.class))).thenReturn(getPagePlayers());
        List<PlayerResponseModel> result = playerService.findAllPlayers(pageable);
        Assert.assertNotNull(result);
    }

    @Test
    public void whenFindAllPlayersIsEmpty_thenReturnMessage() {
        exception.expect(PlayerException.class);
        exception.expectMessage(PlayerMessage.NO_PLAYERS_FOUND);
        Pageable pageable = PageRequest.of(0, 10);
        when(playerRepository.findByActiveTrue(any(Pageable.class))).thenReturn(Page.empty());
        playerService.findAllPlayers(pageable);
    }

    @Test
    public void givenValidRequestModel_whenSavePlayer_thenReturnMessage() {
        PlayerRequestModel playerRequestModel = getPlayerRequestModel(1, "Philippines", "test", "testFn");
        String message = playerService.savePlayer(playerRequestModel);
        Assert.assertEquals(PlayerMessage.PLAYER_SAVE_SUCCESS, message);
    }

    @Test
    public void givenInvalidFirstName_whenSavePlayer_thenReturnMessage() {
        exception.expect(PlayerException.class);
        exception.expectMessage(PlayerMessage.INVALID_NAME);
        PlayerRequestModel playerRequestModel = getPlayerRequestModel(1, "Philippines", "test1", "testFn");
        playerService.savePlayer(playerRequestModel);
    }

    @Test
    public void givenInvalidLastName_whenSavePlayer_thenReturnMessage() {
        exception.expect(PlayerException.class);
        exception.expectMessage(PlayerMessage.INVALID_NAME);
        PlayerRequestModel playerRequestModel = getPlayerRequestModel(1, "Philippines", "test", "testFn1");
        playerService.savePlayer(playerRequestModel);
    }

    @Test
    public void givenValidRequestBody_whenUpdatePlayer_thenReturnMessage() {
        PlayerRequestModel playerRequestModel = getPlayerRequestModel(1, "Philippines", "test", "testFn");
        Optional<PlayerEntity> playerEntityOptional = getOptionalPlayer();
        when(playerRepository.findByPlayerIdAndActiveTrue(any(Integer.class))).thenReturn(playerEntityOptional);
        String message = playerService.updatePlayer(1, playerRequestModel);
        Assert.assertEquals(PlayerMessage.PLAYER_UPDATE_SUCCESS, message);
    }

    @Test
    public void givenInvalidFirstName_whenUpdatePlayer_thenReturnMessage() {
        exception.expect(PlayerException.class);
        exception.expectMessage(PlayerMessage.INVALID_NAME);
        PlayerRequestModel playerRequestModel = getPlayerRequestModel(1, "Philippines", "test1", "testFn");
        Optional<PlayerEntity> playerEntityOptional = getOptionalPlayer();
        when(playerRepository.findByPlayerIdAndActiveTrue(any(Integer.class))).thenReturn(playerEntityOptional);
        playerService.updatePlayer(1, playerRequestModel);
    }

    @Test
    public void givenInvalidLastName_whenUpdatePlayer_thenReturnMessage() {
        exception.expect(PlayerException.class);
        exception.expectMessage(PlayerMessage.INVALID_NAME);
        PlayerRequestModel playerRequestModel = getPlayerRequestModel(1, "Philippines", "test", "testFn1");
        Optional<PlayerEntity> playerEntityOptional = getOptionalPlayer();
        when(playerRepository.findByPlayerIdAndActiveTrue(any(Integer.class))).thenReturn(playerEntityOptional);
        playerService.updatePlayer(1, playerRequestModel);
    }

    @Test
    public void givenNotExistingPlayer_whenUpdatePlayer_thenReturnMessage() {
        exception.expect(PlayerException.class);
        exception.expectMessage(PlayerMessage.PLAYER_NOT_FOUND);
        PlayerRequestModel playerRequestModel = getPlayerRequestModel(1, "Philippines", "test", "testFn");
        Optional<PlayerEntity> playerEntityOptional = Optional.empty();
        when(playerRepository.findByPlayerIdAndActiveTrue(any(Integer.class))).thenReturn(playerEntityOptional);
        playerService.updatePlayer(1, playerRequestModel);
    }

    @Test
    public void whenDeletePlayer_thenReturnMessage() {
        Optional<PlayerEntity> playerEntityOptional = getOptionalPlayer();
        when(playerRepository.findByPlayerIdAndActiveTrue(any(Integer.class))).thenReturn(playerEntityOptional);
        String message = playerService.deletePlayer(1);
        Assert.assertEquals(PlayerMessage.PLAYER_DELETE_SUCCESS, message);
    }

    @Test
    public void givenNotExistingPlayer_whenDeletePlayer_thenReturnMessage() {
        exception.expect(PlayerException.class);
        exception.expectMessage(PlayerMessage.PLAYER_NOT_FOUND);
        Optional<PlayerEntity> playerEntityOptional = Optional.empty();
        when(playerRepository.findByPlayerIdAndActiveTrue(any(Integer.class))).thenReturn(playerEntityOptional);
        playerService.deletePlayer(1);
    }

    @Test
    public void whenFindPlayer_thenReturnPlayer() {
        Optional<PlayerEntity> playerEntityOptional = getOptionalPlayer();
        when(playerRepository.findByPlayerIdAndActiveTrue(any(Integer.class))).thenReturn(playerEntityOptional);
        PlayerResponseModel result = playerService.findPlayer(1);
        Assert.assertNotNull(result);
    }

    @Test
    public void givenNotExistingPlayer_whenFindPlayer_thenReturnPlayer() {
        exception.expect(PlayerException.class);
        exception.expectMessage(PlayerMessage.PLAYER_NOT_FOUND);
        Optional<PlayerEntity> playerEntityOptional = Optional.empty();
        when(playerRepository.findByPlayerIdAndActiveTrue(any(Integer.class))).thenReturn(playerEntityOptional);
        playerService.findPlayer(1);
    }

    @Test
    public void givenValidPlayerIds_whenFindPlayers_thenReturnPlayers() {
        Optional<PlayerEntity> playerEntityOptional1 = getCustomOptionalPlayer(1, "ph", 1, "fname", "lname");
        Optional<PlayerEntity> playerEntityOptional2 = getCustomOptionalPlayer(2, "ph", 1, "fnamee", "lnamee");
        Optional<PlayerEntity> playerEntityOptional3 = getCustomOptionalPlayer(3, "ph", 1, "fnameee", "lnameee");
        Optional<PlayerEntity> playerEntityOptional4 = getCustomOptionalPlayer(4, "ph", 1, "fnameeee", "lnameeee");
        when(playerRepository.findById(any(Integer.class))).thenReturn(playerEntityOptional1, playerEntityOptional2, playerEntityOptional3, playerEntityOptional4);
        List<PlayerResponseModel> result = playerService.findPlayers("1,2,3,4");
        Assert.assertNotNull(result);
    }

    @Test
    public void givenPlayerIsNotExisting_whenFindPlayers_thenReturnMessage() {
        exception.expect(PlayerException.class);
        exception.expectMessage(PlayerMessage.PLAYER_NOT_FOUND);
        when(playerRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        playerService.findPlayers("1,2,3,4");
    }


    private PlayerRequestModel getPlayerRequestModel(Integer teamId, String country, String firstName, String lastName) {
        PlayerRequestModel playerRequestModel = new PlayerRequestModel();
        playerRequestModel.setTeamId(teamId);
        playerRequestModel.setCountry(country);
        playerRequestModel.setFirstName(firstName);
        playerRequestModel.setLastName(lastName);
        return playerRequestModel;
    }

    private Optional<PlayerEntity> getOptionalPlayer() {
        return Optional.of(getPlayer(1, "Philippines", 1, true, "player", "playerLn"));
    }

    private Optional<PlayerEntity> getCustomOptionalPlayer(Integer playerId, String country, Integer team, String firstName, String lastName) {
        return Optional.of(getPlayer(playerId, country, team, true, firstName, lastName));
    }

    private Page<PlayerEntity> getPagePlayers() {
        Pageable pageable = PageRequest.of(0, 10);
        List<PlayerEntity> players = getAllPlayers();
        int start = Math.min((int)pageable.getOffset(), players.size());
        int end = Math.min((start + pageable.getPageSize()), players.size());
        Page<PlayerEntity> playerEntityPage = new PageImpl<>(players.subList(start, end), pageable, players.size());
        return playerEntityPage;
    }

    private List<PlayerEntity> getAllPlayers() {
        List<PlayerEntity> players = new ArrayList<>();
        PlayerEntity playerEntity1 = getPlayer(1, "Philippines", 1, true, "playeronefn", "playeroneln");
        PlayerEntity playerEntity2 = getPlayer(2, "Philippines", 2, true, "playertwofn", "playertwoln");
        PlayerEntity playerEntity3 = getPlayer(3, "Philippines", 1, true, "playerthreefn", "playerthreeln");
        PlayerEntity playerEntity4 = getPlayer(4, "Philippines", 2, true, "playerfourfn", "playerfourln");
        players.add(playerEntity1);
        players.add(playerEntity2);
        players.add(playerEntity3);
        players.add(playerEntity4);
        return players;
    }

    private PlayerEntity getPlayer(Integer playerId, String country, Integer teamId, Boolean isActive,
                                   String firstName, String lastName) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setPlayerId(playerId);
        playerEntity.setCountry(country);
        playerEntity.setTeamId(teamId);
        playerEntity.setActive(isActive);
        playerEntity.setFirstName(firstName);
        playerEntity.setLastName(lastName);
        return playerEntity;
    }

    private TeamResponseModel getTeam(Integer teamId, String teamName) {
        TeamResponseModel team = new TeamResponseModel();
        team.setTeamName(teamName);
        team.setTeamId(teamId);
        return team;
    }
}
