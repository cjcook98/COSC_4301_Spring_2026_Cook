package com.neonark.api.service;

import com.neonark.api.dto.*;

import com.neonark.api.model.Creature;
import com.neonark.api.model.CreatureStatus;
import com.neonark.api.model.Habitat;
import com.neonark.api.repository.CreatureRepository;
import com.neonark.api.repository.HabitatRepository;
import com.neonark.api.repository.ObservationRepository;
import com.neonark.api.repository.FeedingScheduleRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
public class CreatureService {

    private final CreatureRepository creatureRepo;
    private final HabitatRepository habitatRepo;
    private final ObservationRepository observationRepo;
    private final FeedingScheduleRepository feedingRepo;

    public CreatureService(CreatureRepository creatureRepo, HabitatRepository habitatRepo, ObservationRepository observationRepo, FeedingScheduleRepository feedingRepo) {
        this.creatureRepo = creatureRepo;
        this.habitatRepo = habitatRepo;
        this.observationRepo = observationRepo;
        this.feedingRepo = feedingRepo;
    }

    public CreatureResponse createCreature(CreatureRequest req) {

        Habitat habitat = habitatRepo.findById(req.habitatId())
                .orElseThrow(() -> new RuntimeException("Habitat not found."));

        // Check duplicate rule: UNIQUE (habitat_id, name)
        boolean exists = creatureRepo.existsByHabitatIdAndName(req.habitatId(), req.name());
        if (exists) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A creature with this name already exists in the same habitat."
            );
        }

        Creature creature = new Creature(
                req.name(),
                req.status(),
                habitat
        );

        Creature saved = creatureRepo.save(creature);

        return new CreatureResponse(
                saved.getId(),
                saved.getName(),
                saved.getStatus(),
                saved.getHabitat().getId(),
                saved.getHabitat().getName()
        );
    }

    public List<CreatureResponse> getAllCreatures() {
        return creatureRepo.findAllByOrderByIdAsc().stream()
                .map(creature -> new CreatureResponse(
                        creature.getId(),
                        creature.getName(),
                        creature.getStatus(),
                        creature.getHabitat().getId(),
                        creature.getHabitat().getName()
                ))
                .toList();
    }

    public CreatureResponse getCreatureById(Long id) {
        Creature creature = creatureRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Creature not found."));

        return new CreatureResponse(
                creature.getId(),
                creature.getName(),
                creature.getStatus(),
                creature.getHabitat().getId(),
                creature.getHabitat().getName()
        );
    }

    public void deleteCreature(Long id) {
        if (!creatureRepo.existsById(id)) {
            throw new RuntimeException("Creature not found.");
        }
        creatureRepo.deleteById(id);
    }

    public CreatureResponse updateCreature(Long id, CreatureRequest req) {
        Creature creature = creatureRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Creature not found."));

        Habitat habitat = habitatRepo.findById(req.habitatId())
                .orElseThrow(() -> new RuntimeException("Habitat not found."));

        creature.setName(req.name());
        creature.setStatus(req.status());
        creature.setHabitat(habitat);

        Creature saved = creatureRepo.save(creature);

        return new CreatureResponse(
                saved.getId(),
                saved.getName(),
                saved.getStatus(),
                saved.getHabitat().getId(),
                saved.getHabitat().getName()
        );
    }

    public RenameResponse renameCreature(Long id, String newName) {

        Creature creature = creatureRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Creature not found."));

        if (newName == null || newName.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be blank.");
        }

        // Check duplicate rule: UNIQUE (habitat_id, name)
        boolean exists = creatureRepo.existsByHabitatIdAndName(creature.getHabitat().getId(), newName);
        if (exists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate creature name in this habitat.");
        }

        String oldName = creature.getName();
        creature.setName(newName);
        creatureRepo.save(creature);

        return new RenameResponse(
                creature.getId(),
                oldName,
                newName,
                creature.getHabitat().getName()
        );
    }

    public Map<String, Object> softDeleteCreature(Long id) {

        Creature creature = creatureRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Creature not found."));

        if (feedingRepo.existsByCreatureId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot remove creature with active feeding schedule.");
        }

        creature.setStatus(CreatureStatus.Removed);
        creatureRepo.save(creature);

        return Map.of(
                "id", creature.getId(),
                "message", "Creature removed.",
                "status", creature.getStatus()
        );
    }

    public CreatureWithObservationsResponse getCreatureWithObservations(Long id) {
        Creature creature = creatureRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Creature not found."));

        var observations = observationRepo.findByCreatureIdOrderByCreatedAtAsc(id).stream()
                .map(o -> new ObservationResponse(
                        o.getId(),
                        o.getAuthor(),
                        o.getNote(),
                        o.getCreatedAt()
                ))
                .toList();

        return new CreatureWithObservationsResponse(
                creature.getId(),
                creature.getName(),
                creature.getStatus(),
                creature.getHabitat().getName(),
                observations
        );
    }
}
