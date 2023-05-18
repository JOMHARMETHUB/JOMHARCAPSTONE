package com.sportingevents.common.exception;

import com.sportingevents.common.constant.Message;
import com.sportingevents.user.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.REQUEST_BODY_INVALID);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<String> handleDisabledException(DisabledException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.USER_DISABLED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.INVALID_CREDENTIALS);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserException(UserException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
