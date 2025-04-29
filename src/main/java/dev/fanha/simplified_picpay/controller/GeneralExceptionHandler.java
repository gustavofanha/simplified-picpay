package dev.fanha.simplified_picpay.controller;

import dev.fanha.simplified_picpay.exception.StandardException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler
    public ProblemDetail handleStandardException(StandardException e) {
        return e.toProblemDetail();
    }
}
