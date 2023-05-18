package com.sportingevents.tournament;

import com.sportingevents.common.constant.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = AppConstant.TOURNAMENT_SERVICE_NAME, url = AppConstant.TOURNAMENT_SERVICE_LOCALHOST_API_GATEWAY_URL)
public interface TournamentRestService {

    @GetMapping("/{id}")
    ResponseEntity<TournamentResponseModel> getTournament(@PathVariable("id") Integer tournamentId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token);
}
