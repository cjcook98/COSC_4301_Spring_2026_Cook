package com.neonark.api.dto;

import com.neonark.api.model.CreatureStatus;

import java.util.List;

public record CreatureWithObservationsResponse (
        Long id,
        String name,
        CreatureStatus status,
        String habitatName,
        List<ObservationResponse> observations
) {}
