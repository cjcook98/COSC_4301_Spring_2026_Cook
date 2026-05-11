package com.neonark.api.controller;

import com.neonark.api.dto.RenameRequest;
import com.neonark.api.model.Creature;
import com.neonark.api.dto.CreatureRequest;
import com.neonark.api.dto.CreatureResponse;
import com.neonark.api.dto.RenameResponse;
import com.neonark.api.dto.CreatureWithObservationsResponse;
import com.neonark.api.service.CreatureService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/creatures")
public class CreatureController {

    private final CreatureService service;

    public CreatureController(CreatureService service) { this.service = service; }

    // Gets a list of all creatures in the system
    @GetMapping
    public List<CreatureResponse> getAllCreatures() {
        return service.getAllCreatures();
    }

    // Gets a creature with a specific creature id
    @GetMapping("/{id}")
    public CreatureResponse getById(@PathVariable Long id) {
        return service.getCreatureById(id);
    }

    // Gets a creature along with its associated observations
    @GetMapping("/{id}/observations")
    public CreatureWithObservationsResponse getWithObservations(@PathVariable Long id) {
        return service.getCreatureWithObservations(id);
    }

    // Creates a new creature
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatureResponse create(@Valid @RequestBody CreatureRequest req) {
        return service.createCreature(req);
    }

    // Soft deletes a creature by marking it as inactive ("Removed")
    @DeleteMapping("/{id}")
    public Map<String, Object> softDelete(@PathVariable Long id) {
        return service.softDeleteCreature(id);
    }

    // Updates an existing creature's details
    @PutMapping("/{id}")
    public CreatureResponse update(@PathVariable Long id, @Valid @RequestBody CreatureRequest req) {
        return service.updateCreature(id, req);
    }

    // Renames a creature with a specific creature id
    @PutMapping("/{id}/rename")
    public RenameResponse rename(
            @PathVariable Long id,
            @Valid @RequestBody RenameRequest req) {
        return service.renameCreature(id, req.newName());
    }
}
