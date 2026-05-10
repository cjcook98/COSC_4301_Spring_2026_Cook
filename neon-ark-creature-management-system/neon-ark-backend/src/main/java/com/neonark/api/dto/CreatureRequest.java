package com.neonark.api.dto;

import com.neonark.api.model.CreatureStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatureRequest (
        @NotBlank String name,
        @NotNull CreatureStatus status,
        @NotNull Long habitatId
) {}
