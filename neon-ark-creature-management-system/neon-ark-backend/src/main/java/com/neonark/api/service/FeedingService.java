package com.neonark.api.service;

import com.neonark.api.dto.FeedingCreatureResponse;
import com.neonark.api.model.FeedingSchedule;
import com.neonark.api.repository.FeedingScheduleRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class FeedingService {

    private final FeedingScheduleRepository feedingRepo;

    public FeedingService(FeedingScheduleRepository feedingRepo) {
        this.feedingRepo = feedingRepo;
    }

    public List<FeedingCreatureResponse> findCreaturesToFeedAt(String timeStr) {
        if (timeStr == null || timeStr.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Time query parameter is required (HH:MM).");
        }

        LocalTime time;
        try {
            time = LocalTime.parse(timeStr);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid time format, expected HH:MM.");
        }

        List<FeedingSchedule> schedules = feedingRepo.findByFeedTime(time);

        if (schedules.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No creatures found for this feeding time.");
        }

        return schedules.stream()
                .map(fs -> new FeedingCreatureResponse(
                        fs.getCreature().getId(),
                        fs.getCreature().getName(),
                        fs.getCreature().getStatus(),
                        fs.getCreature().getHabitat().getName(),
                        fs.getFeedTime().toString()
                ))
                .toList();
    }
}
