package com.elotech.cadastroCerto.exceptions;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class AllExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardErrorResponse> NotFoundException
            (NotFoundException exception, HttpServletRequest request) {

        StandardErrorResponse error = new StandardErrorResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CpfAlreadyExistException.class)
    public ResponseEntity<StandardErrorResponse> CpfAlreadyExist
            (CpfAlreadyExistException exception, HttpServletRequest request) {

        StandardErrorResponse error = new StandardErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                exception.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AttributeMissingException.class)
    public ResponseEntity<StandardErrorResponse> PersonAttributeMissingException
            (AttributeMissingException exception, HttpServletRequest request) {

        StandardErrorResponse error = new StandardErrorResponse(Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Errors:",
                exception.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
