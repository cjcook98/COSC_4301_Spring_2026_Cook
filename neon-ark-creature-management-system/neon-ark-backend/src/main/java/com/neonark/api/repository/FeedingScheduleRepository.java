package com.neonark.api.repository;

import com.neonark.api.model.FeedingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface FeedingScheduleRepository extends JpaRepository<FeedingSchedule, Long> {
    List<FeedingSchedule> findByFeedTime(LocalTime feedTime);

    boolean existsByCreatureId(Long creatureId);
}
