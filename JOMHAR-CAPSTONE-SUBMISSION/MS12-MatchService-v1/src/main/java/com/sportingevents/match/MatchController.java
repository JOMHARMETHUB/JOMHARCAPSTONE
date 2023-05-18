package com.sportingevents.match;

import com.sportingevents.common.constant.ApiEndpoint;
import com.sportingevents.field.FieldRestService;
import com.sportingevents.player.PlayerRestService;
import com.sportingevents.team.TeamRestService;
import com.sportingevents.tournament.TournamentRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.MATCHES)
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamRestService teamRestService;

    @Autowired
    private PlayerRestService playerRestService;

    @Autowired
    private FieldRestService fieldRestService;

    @Autowired
    private TournamentRestService tournamentRestService;

    @GetMapping
    public ResponseEntity<List<MatchResponseModel>> findAllMatches(Pageable pageable) {
        return ResponseEntity.ok(matchService.findAllMatches(pageable));
    }

    @GetMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<MatchResponseModel> getMatch(@PathVariable("id") Integer matchId) {
        return ResponseEntity.ok(matchService.findMatch(matchId));
    }

    @PostMapping
    public ResponseEntity<String> saveMatch(@Validated @RequestBody MatchRequestModel matchRequestModel, @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) {
        fieldRestService.getField(matchRequestModel.getFieldId(), jwtToken);
        tournamentRestService.getTournament(matchRequestModel.getTournamentId(), jwtToken);
        teamRestService.getTeams(matchRequestModel.getTeamsId(), jwtToken);
        playerRestService.getPlayers(matchRequestModel.getParticipantsId(), jwtToken);
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.saveMatch(matchRequestModel));
    }

    @PutMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<String> updateMatch(@PathVariable("id") Integer matchId, @Validated @RequestBody MatchRequestModel matchRequestModel, @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) {
        fieldRestService.getField(matchRequestModel.getFieldId(), jwtToken);
        tournamentRestService.getTournament(matchRequestModel.getTournamentId(), jwtToken);
        teamRestService.getTeams(matchRequestModel.getTeamsId(), jwtToken);
        playerRestService.getPlayers(matchRequestModel.getParticipantsId(), jwtToken);
        return ResponseEntity.ok(matchService.updateMatch(matchId, matchRequestModel));
    }

    @DeleteMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<String> deleteMatch(@PathVariable("id") Integer matchId) {
        return ResponseEntity.ok(matchService.deleteMatch(matchId));
    }
}
