package org.pl.serwis_logowania.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<HttpStatus> exception() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
