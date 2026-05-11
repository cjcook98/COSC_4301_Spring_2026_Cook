package com.neonark.api.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalTime;

@Entity
@Table(name = "feeding_schedules")
public class FeedingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creature_id")
    private Creature creature;

    @Column(name = "feed_time", nullable = false)
    private LocalTime feedTime;

    protected FeedingSchedule() {}

    // Creates a new feeding schedule entry
    public FeedingSchedule(Creature creature, LocalTime feedTime) {
        this.creature = creature;
        this.feedTime = feedTime;
    }

    public Long getId() { return id; }
    public Creature getCreature() { return creature; }
    public LocalTime getFeedTime() { return feedTime; }
}
