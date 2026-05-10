package com.neonark.api.controller;

import com.neonark.api.dto.FeedingCreatureResponse;
import com.neonark.api.service.FeedingService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedings")
public class FeedingController {

    private final FeedingService service;

    public FeedingController(FeedingService service) {
        this.service = service;
    }

    @GetMapping
    public List<FeedingCreatureResponse> findByTime(@RequestParam String time) {
        return service.findCreaturesToFeedAt(time);
    }
}
