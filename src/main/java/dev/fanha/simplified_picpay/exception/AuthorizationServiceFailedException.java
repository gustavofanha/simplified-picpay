package dev.fanha.simplified_picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AuthorizationServiceFailedException extends StandardException{

    @Override
    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.SERVICE_UNAVAILABLE);
        problemDetail.setTitle("Unauthorized");
        problemDetail.setTitle("Authorization failed, try again later");

        return problemDetail;
    }
}
