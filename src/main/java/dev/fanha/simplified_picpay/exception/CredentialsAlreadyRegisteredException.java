package dev.fanha.simplified_picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class CredentialsAlreadyRegisteredException extends StandardException{

    @Override
    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Credentials already registered!");
        problemDetail.setDetail("Document or email already registered in our database");

        return problemDetail;
    }
}
