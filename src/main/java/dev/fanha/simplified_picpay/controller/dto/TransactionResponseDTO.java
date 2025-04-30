package dev.fanha.simplified_picpay.controller.dto;

import java.math.BigDecimal;

public record TransactionResponseDTO(
        BigDecimal amount,
        UserResponseDTO sender,
        UserResponseDTO receiver) {
}
