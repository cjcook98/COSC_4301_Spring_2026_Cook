package com.neonark.api.dto;

import com.neonark.api.model.CreatureStatus;

public record CreatureResponse(
        Long id,
        String name,
        CreatureStatus status,
        Long habitatId,
        String habitatName
) {}
