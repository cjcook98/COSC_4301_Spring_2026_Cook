package com.neonark.api.dto;

import com.neonark.api.model.CreatureStatus;

public record FeedingCreatureResponse (
        Long id,
        String name,
        CreatureStatus status,
        String habitatName,
        String feedTime
) {}
