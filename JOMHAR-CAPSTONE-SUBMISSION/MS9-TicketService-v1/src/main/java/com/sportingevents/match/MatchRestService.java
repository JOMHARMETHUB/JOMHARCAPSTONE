package com.sportingevents.match;

import com.sportingevents.common.constant.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = AppConstant.MATCH_SERVICE_NAME, url = AppConstant.MATCH_SERVICE_LOCALHOST_API_GATEWAY_URL)
public interface MatchRestService {

    @GetMapping("/{id}")
    ResponseEntity<MatchResponseModel> getMatch(@PathVariable("id") Integer matchId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token);
}
