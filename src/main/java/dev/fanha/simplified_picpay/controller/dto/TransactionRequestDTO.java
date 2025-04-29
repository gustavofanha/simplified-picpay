package dev.fanha.simplified_picpay.controller.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionRequestDTO(
        @NotNull BigDecimal amount,
        @NotNull Long sender,
        @NotNull Long receiver) {
}
