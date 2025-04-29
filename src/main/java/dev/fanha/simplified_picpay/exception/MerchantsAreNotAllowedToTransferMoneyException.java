package dev.fanha.simplified_picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class MerchantsAreNotAllowedToTransferMoneyException extends StandardException{

    @Override
    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Unauthorized");
        problemDetail.setDetail("Merchants are not allowed to transfer money");

        return problemDetail;
    }
}
