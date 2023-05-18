package com.sportingevents.player;

import com.sportingevents.common.constant.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = AppConstant.PLAYER_SERVICE_NAME, url = AppConstant.PLAYER_SERVICE_LOCALHOST_API_GATEWAY_URL)
public interface PlayerRestService {

    @GetMapping("/multiple?playerIds={playerIds}")
    ResponseEntity<List<PlayerResponseModel>> getPlayers(@PathVariable("playerIds") String playerIds, @RequestHeader(HttpHeaders.AUTHORIZATION) String token);
}
