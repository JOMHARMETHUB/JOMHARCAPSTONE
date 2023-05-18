package com.sportingevents.player;

import com.sportingevents.common.constant.ApiEndpoint;
import com.sportingevents.team.TeamRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.PLAYERS)
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamRestService teamRestService;

    @GetMapping
    public ResponseEntity<List<PlayerResponseModel>> findAllPlayers(Pageable pageable) {
        return ResponseEntity.ok(playerService.findAllPlayers(pageable));
    }

    @GetMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<PlayerResponseModel> findPlayer(@PathVariable("id") Integer playerId) {
        return ResponseEntity.ok(playerService.findPlayer(playerId));
    }

    @GetMapping(ApiEndpoint.MULTIPLE)
    public ResponseEntity<List<PlayerResponseModel>> findPlayers(@Param("playerIds") String playerIds) {
        return ResponseEntity.ok(playerService.findPlayers(playerIds));
    }

    @PostMapping
    public ResponseEntity<String> savePlayer(@RequestBody @Validated PlayerRequestModel playerRequestModel, @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) {
        teamRestService.getTeam(playerRequestModel.getTeamId(), jwtToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.savePlayer(playerRequestModel));
    }

    @PutMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<String> updatePlayer(@PathVariable("id") Integer id,
                                               @RequestBody @Validated PlayerRequestModel playerRequestModel, @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) {
        teamRestService.getTeam(playerRequestModel.getTeamId(), jwtToken);
        return ResponseEntity.ok(playerService.updatePlayer(id, playerRequestModel));
    }

    @DeleteMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<String> deletePlayer(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(playerService.deletePlayer(id));
    }
}
