package com.fiap.zalert.api.dto;

import com.fiap.zalert.api.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String senha,
        UserRole role
) {}

