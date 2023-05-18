package com.sportingevents.team;

import com.sportingevents.common.constant.ApiEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.TEAMS)
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamEntity>> getAllTeams(Pageable pageable) {
        return ResponseEntity.ok(teamService.getAllTeams(pageable));
    }

    @GetMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<TeamEntity> getTeam(@PathVariable("id") Integer teamId) {
        return ResponseEntity.ok(teamService.getTeam(teamId));
    }

    @GetMapping(ApiEndpoint.MULTIPLE)
    public ResponseEntity<List<TeamEntity>> getTeams(@Param("teamIds") String teamIds) {
        return ResponseEntity.ok(teamService.getTeams(teamIds));
    }

    @PostMapping
    public ResponseEntity<String> saveTeam(@Validated @RequestBody TeamRequestModel teamRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.saveTeam(teamRequestModel));
    }

    @PutMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<String> updateTeam(@PathVariable("id") Integer teamId, @Validated @RequestBody TeamRequestModel teamRequestModel) {
        return ResponseEntity.ok(teamService.updateTeam(teamId, teamRequestModel));
    }

    @DeleteMapping(ApiEndpoint.ID_PATH_VARIABLE)
    public ResponseEntity<String> deleteTeam(@PathVariable("id") Integer teamId) {
        return ResponseEntity.ok(teamService.deleteTeam(teamId));
    }
}
