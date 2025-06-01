package com.fiap.zalert.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
        @NotBlank @Email String email,
        @NotBlank String senha
) {
}
