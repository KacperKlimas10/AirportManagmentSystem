package org.pl.serwis_panel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.security.sasl.AuthenticationException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<HttpStatus> AuthenticateException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    public ResponseEntity<HttpStatus> NullException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<HttpStatus> AuthorizartionException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HttpStatus> RuntimeException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
