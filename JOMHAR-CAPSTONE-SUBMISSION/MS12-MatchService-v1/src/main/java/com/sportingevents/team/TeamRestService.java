package com.sportingevents.team;

import com.sportingevents.common.constant.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name=AppConstant.TEAM_SERVICE_NAME, url=AppConstant.TEAM_SERVICE_LOCALHOST_API_GATEWAY_URL)
public interface TeamRestService {
    @GetMapping("/multiple?teamIds={teamIds}")
    ResponseEntity<List<TeamResponseModel>> getTeams(@PathVariable("teamIds") String teamIds, @RequestHeader(HttpHeaders.AUTHORIZATION) String token);

}
