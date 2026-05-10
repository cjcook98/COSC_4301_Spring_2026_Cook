package com.neonark.api.dto;

import jakarta.validation.constraints.NotBlank;

public record RenameRequest(
        @NotBlank String newName
) {}
