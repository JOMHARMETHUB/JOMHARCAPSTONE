package com.sportingevents.common.jwt;

import com.sportingevents.common.constant.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = AppConstant.LOGIN_SERVICE_NAME, url=AppConstant.LOGIN_SERVICE_LOCALHOST_API_GATEWAY_URL)
public interface JwtRestService {

    @PostMapping("/validateJwtToken")
    ResponseEntity<Boolean> validateJwtToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);

    @PostMapping("/getEmailFromJwt")
    ResponseEntity<String> getEmailFromJwt(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);
}
