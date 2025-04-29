package dev.fanha.simplified_picpay.client;

import dev.fanha.simplified_picpay.client.dto.AuthorizationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "authorization", url = "${transfer.authorization-service.url}")
public interface AuthorizationClient {

    @GetMapping
    ResponseEntity<AuthorizationResponseDTO> isAuthorized();
}
