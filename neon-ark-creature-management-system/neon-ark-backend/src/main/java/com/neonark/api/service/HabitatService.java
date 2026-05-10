package com.neonark.api.service;

import com.neonark.api.model.Habitat;
import com.neonark.api.repository.HabitatRepository;
import com.neonark.api.dto.HabitatRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitatService {

    private final HabitatRepository habitatRepo;

    public HabitatService(HabitatRepository habitatRepo) {
        this.habitatRepo = habitatRepo;
    }

    public Habitat createHabitat(HabitatRequest req) {
        Habitat habitat = new Habitat(req.name());
        return habitatRepo.save(habitat);
    }

    public List<Habitat> getAllHabitats() {
        return habitatRepo.findAll();
    }
}
