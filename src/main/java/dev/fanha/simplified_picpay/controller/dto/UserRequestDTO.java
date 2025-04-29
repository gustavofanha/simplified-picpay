package dev.fanha.simplified_picpay.controller.dto;

import dev.fanha.simplified_picpay.entity.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserRequestDTO(
        @NotBlank String fullName,
        @NotBlank String document,
        @Email String email,
        @NotBlank String password,
        @NotNull BigDecimal balance,
        @NotNull UserType userType
) {
}
