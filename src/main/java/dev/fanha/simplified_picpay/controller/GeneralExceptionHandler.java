package dev.fanha.simplified_picpay.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import dev.fanha.simplified_picpay.exception.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(StandardException.class)
    public ProblemDetail handleStandardException(StandardException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleInvalidArgumentsException(MethodArgumentNotValidException e) {
        var fieldErrors = e.getFieldErrors().stream().map(i -> new InvalidParams(i.getField(), i.getDefaultMessage())).toList();
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problemDetail.setTitle("Some fields are not valid");
        problemDetail.setProperty("invalid params", fieldErrors);

        return problemDetail;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleInvalidUserType(HttpMessageNotReadableException e) {
        var cause = e.getCause();
        if (cause instanceof InvalidFormatException ife) {
            if (ife.getTargetType().isEnum()) {
                var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
                problemDetail.setTitle("Invalid user type");
                problemDetail.setDetail("User type must be COMMON or MERCHANT");
                return problemDetail;
            }
        }

        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Malformed JSON");
        problemDetail.setDetail("The request body is invalid or improperly formatted.");
        return problemDetail;
    }

    private record InvalidParams(String field, String cause){}
}
