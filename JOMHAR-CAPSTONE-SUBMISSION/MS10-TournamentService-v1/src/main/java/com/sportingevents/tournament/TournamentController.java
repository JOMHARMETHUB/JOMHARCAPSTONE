package com.sportingevents.tournament;

import com.sportingevents.common.constant.ApiEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TOURNAMENTS)
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public ResponseEntity<List<TournamentEntity>> getAllTournaments(Pageable pageable) {
        return ResponseEntity.ok(tournamentService.getAllTournaments(pageable));
    }

    @GetMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<TournamentEntity> getTournament(@PathVariable("id") Integer tournamentId) {
        return ResponseEntity.ok(tournamentService.getTournament(tournamentId));
    }

    @PostMapping
    public ResponseEntity<String> saveTournament(@Validated @RequestBody TournamentRequestModel tournamentRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tournamentService.saveTournament(tournamentRequestModel));
    }

    @PutMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<String> updateTournament(@PathVariable("id") Integer tournamentId, @Validated @RequestBody TournamentRequestModel tournamentRequestModel) {
        return ResponseEntity.ok(tournamentService.updateTournament(tournamentId, tournamentRequestModel));
    }

    @DeleteMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<String> deleteTournament(@PathVariable("id") Integer tournamentId) {
        return ResponseEntity.ok(tournamentService.deleteTournament(tournamentId));
    }
}
