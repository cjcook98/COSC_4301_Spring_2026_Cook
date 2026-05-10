package com.neonark.api.dto;

import java.time.Instant;

public record ObservationResponse (
        Long id,
        String author,
        String note,
        Instant createdAt
) {}
