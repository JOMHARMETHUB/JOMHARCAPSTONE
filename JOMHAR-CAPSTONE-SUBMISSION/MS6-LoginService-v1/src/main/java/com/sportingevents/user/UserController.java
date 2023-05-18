package com.sportingevents.user;

import com.sportingevents.common.constant.ApiEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.USERS)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody @Validated UserRequestModel userRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRequestModel));
    }

    @PostMapping(ApiEndpoint.VALIDATE_JWT_TOKEN)
    public ResponseEntity<Boolean> validateToken(HttpServletRequest request) {
        return ResponseEntity.ok(userService.validateToken(request));
    }

    @PostMapping(ApiEndpoint.GET_EMAIL_FROM_JWT)
    public ResponseEntity<String> getEmailFromJwt(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.ok(userService.getEmail(token));
    }
}
