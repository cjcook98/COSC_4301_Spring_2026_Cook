package com.neonark.api.controller;

import com.neonark.api.model.Habitat;
import com.neonark.api.service.HabitatService;
import com.neonark.api.dto.HabitatRequest;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/habitats")
public class HabitatController {

    private final HabitatService service;

    public HabitatController(HabitatService service) {
        this.service = service;
    }

    // Gets a list of all habitats
    @GetMapping
    public List<Habitat> getAll() {
        return service.getAllHabitats();
    }

    // Creates a new habitat
    @PostMapping
    public Habitat create(@Valid @RequestBody HabitatRequest req) {
        return service.createHabitat(req);
    }
}
