package com.sportingevents.field;

import com.sportingevents.common.constant.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = AppConstant.FIELD_SERVICE_NAME, url = AppConstant.FIELD_SERVICE_LOCALHOST_API_GATEWAY_URL)
public interface FieldRestService {

    @GetMapping("/{id}")
    ResponseEntity<FieldResponseModel> getField(@PathVariable("id") Integer fieldId, @RequestHeader(HttpHeaders.AUTHORIZATION) String token);
}
