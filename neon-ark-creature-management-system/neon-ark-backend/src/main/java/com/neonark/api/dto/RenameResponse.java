package com.neonark.api.dto;

public record RenameResponse(
        Long id,
        String oldName,
        String newName,
        String habitatName
) {}
