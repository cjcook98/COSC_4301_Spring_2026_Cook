// CreatureService.java

package org.example.neonarkintaketracker.service;

import org.example.neonarkintaketracker.entity.Creature;
import org.example.neonarkintaketracker.repository.CreatureRepository;
import org.springframework.stereotype.Service;

import org.example.neonarkintaketracker.repository.HabitatRepository;
import org.example.neonarkintaketracker.dto.CreatureRequest;
import org.example.neonarkintaketracker.dto.CreatureResponse;
import org.example.neonarkintaketracker.entity.Habitat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/*
 * Thin service for now.
 * Keeps the controller clean and gives us a place to add
 * validation, DTO mapping, and business rules later.
 */
@Service
public class CreatureService {

    private final CreatureRepository repository;
    private final HabitatRepository habitatRepository;

    public CreatureService(CreatureRepository repository, HabitatRepository habitatRepository) {
        this.repository = repository;
        this.habitatRepository = habitatRepository;
    }

    /*
     * Return every creature currently in the database.
     * This is the "Read" operation for GET /api/creatures
     */
    public List<Creature> getAllCreatures() {
        return repository.findAll();
    }

    // NEW: Return one creature by id (Optional = may not exist)
    public Optional<Creature> getCreatureById(Long id) {
        return repository.findById(id);
    }

    public CreatureResponse createCreature(CreatureRequest req) {
        // 1. Map request -> entity
        Creature creature = new Creature();
        creature.setName(req.name());
        creature.setSpecies(req.species());
        creature.setDangerLevel(req.dangerLevel());
        creature.setCondition(req.condition());
        creature.setNotes(req.notes());
        creature.setCreatedAt(LocalDateTime.now());

        // 2. Validate habitat exists
        Habitat habitat = habitatRepository.findById(req.habitatId())
                .orElseThrow(() -> new RuntimeException("Habitat not found"));

        creature.setHabitat(habitat);

        // 3. Save entity
        Creature saved = repository.save(creature);

        // 4. Map entity -> response DTO
        return new CreatureResponse(
                saved.getId(),
                saved.getName(),
                saved.getSpecies(),
                saved.getDangerLevel(),
                saved.getCondition(),
                saved.getNotes(),
                saved.getCreatedAt(),
                saved.getHabitat().getId()
        );
    }
}