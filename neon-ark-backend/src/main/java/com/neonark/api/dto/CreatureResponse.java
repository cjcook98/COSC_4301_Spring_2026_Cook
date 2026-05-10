package com.neonark.api.dto;

public record CreatureReponse (
        Long id,
        String name,
        String status,
        Long habitatId,
        String habitatName
) {}
