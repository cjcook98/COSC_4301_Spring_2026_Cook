package com.neonark.api.repository;

import com.neonark.api.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, Long> {
    List<Observation> findByCreatureIdOrderByCreatedAtAsc(Long creatureId);
}
