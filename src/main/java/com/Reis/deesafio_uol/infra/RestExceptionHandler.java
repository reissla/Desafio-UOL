package com.Reis.deesafio_uol.infra;

import com.Reis.deesafio_uol.exceptions.CredentialsAlreadyUsedException;
import com.Reis.deesafio_uol.exceptions.EmptyGroupException;
import com.Reis.deesafio_uol.exceptions.FullGroupException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FullGroupException.class)
    public ResponseEntity<String> FullGroupException(FullGroupException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O grupo está cheio!");
    }

    @ExceptionHandler(EmptyGroupException.class)
    public ResponseEntity<String> EmptyGroupException(EmptyGroupException exception){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(CredentialsAlreadyUsedException.class)
    public ResponseEntity<String> CredentialsAlreadyUsedException(CredentialsAlreadyUsedException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome ou Email ja foram usados!");
    }
}
