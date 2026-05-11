package com.neonark.api.repository;

import com.neonark.api.model.FeedingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface FeedingScheduleRepository extends JpaRepository<FeedingSchedule, Long> {
    // Finds all feeding schedule entries that match the given time
    List<FeedingSchedule> findByFeedTime(LocalTime feedTime);

    // Checkes whether a feeding schedule exists for a given creature
    boolean existsByCreatureId(Long creatureId);
}
