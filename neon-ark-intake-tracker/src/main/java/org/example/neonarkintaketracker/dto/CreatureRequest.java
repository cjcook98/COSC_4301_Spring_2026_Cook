package org.example.neonarkintaketracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
/**
 * DTO used for CREATE and UPDATE requests coming from the Java CLI client.
 *
 * NOTE:
 *  - We intentionally EXCLUDE id and createdAt because those are DB-managed.
 *  - This is the "allowed" shape of incoming data.
 */
public record CreatureRequest(
        @NotBlank String name,
        @NotBlank String species,
        @NotBlank String dangerLevel,
        @NotBlank String condition,
        String notes,
        @NotNull Long habitatId
) {}