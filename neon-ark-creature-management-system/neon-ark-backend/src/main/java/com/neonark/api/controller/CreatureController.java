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

    @GetMapping
    public List<CreatureResponse> getAllCreatures() {
        return service.getAllCreatures();
    }

    @GetMapping("/{id}")
    public CreatureResponse getById(@PathVariable Long id) {
        return service.getCreatureById(id);
    }

    @GetMapping("/{id}/observations")
    public CreatureWithObservationsResponse getWithObservations(@PathVariable Long id) {
        return service.getCreatureWithObservations(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatureResponse create(@Valid @RequestBody CreatureRequest req) {
        return service.createCreature(req);
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> softDelete(@PathVariable Long id) {
        return service.softDeleteCreature(id);
    }

    @PutMapping("/{id}")
    public CreatureResponse update(@PathVariable Long id, @Valid @RequestBody CreatureRequest req) {
        return service.updateCreature(id, req);
    }

    @PutMapping("/{id}/rename")
    public RenameResponse rename(
            @PathVariable Long id,
            @Valid @RequestBody RenameRequest req) {
        return service.renameCreature(id, req.newName());
    }
}
