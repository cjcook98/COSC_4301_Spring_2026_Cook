package com.neonark.api.repository;

import com.neonark.api.model.Creature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreatureRepository extends JpaRepository<Creature, Long> {
    boolean existsByHabitatIdAndName(Long habitatId, String name);

    List<Creature> findAllByOrderByIdAsc();
}