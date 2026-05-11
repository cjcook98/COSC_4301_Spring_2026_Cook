package com.neonark.api.repository;

import com.neonark.api.model.Creature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreatureRepository extends JpaRepository<Creature, Long> {
    // Checks whether a creature with a given name already exists within
    // the specific habitat
    boolean existsByHabitatIdAndName(Long habitatId, String name);

    // Retrieves all creatures sorted by ID in ascending order
    List<Creature> findAllByOrderByIdAsc();
}