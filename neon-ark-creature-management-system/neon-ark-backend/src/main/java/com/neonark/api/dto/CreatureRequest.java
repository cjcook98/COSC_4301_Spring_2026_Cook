package com.neonark.api.dto;

import com.neonark.api.model.CreatureStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreatureRequest (
        // Ensures name is not blank and it's only letters or spaces
        @NotBlank
        @Pattern(regexp = "^[A-Za-z ]+$")
        String name,

        @NotNull CreatureStatus status,
        @NotNull Long habitatId
) {}
