package dev.fanha.simplified_picpay.service;

import dev.fanha.simplified_picpay.client.AuthorizationClient;
import dev.fanha.simplified_picpay.entity.Transaction;
import dev.fanha.simplified_picpay.exception.AuthorizationServiceFailedException;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(Transaction transaction) {

        try {
            var res = authorizationClient.isAuthorized();
            return res.getBody().authorized();
        } catch (FeignException e) {
            throw new AuthorizationServiceFailedException();
        }
    }
}
