package com.sportingevents.common.exception;

import com.sportingevents.common.constant.Message;
import com.sportingevents.player.PlayerException;
import com.sportingevents.player.PlayerMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Message.REQUEST_BODY_INVALID);
    }

    @ExceptionHandler(PlayerException.class)
    public ResponseEntity<String> handlePlayerException(PlayerException ex) {
        switch (ex.getMessage()) {
            case PlayerMessage.PLAYER_NOT_FOUND:
            case PlayerMessage.NO_PLAYERS_FOUND:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<String> handleServletException(ServletException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
