package dev.fanha.simplified_picpay.controller.dto;

import dev.fanha.simplified_picpay.entity.UserType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String fullName,
        String email,
        BigDecimal balance,
        UserType userType,
        LocalDateTime creationTimestamp) {
}
