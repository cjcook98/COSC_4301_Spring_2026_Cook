package com.neonark.api.dto;

import jakarta.validation.constraints.NotBlank;

public record HabitatRequest(
        @NotBlank String name
) {}
