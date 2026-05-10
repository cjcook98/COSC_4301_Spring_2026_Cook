package com.neonark.api.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "observations")
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "creature_id")
    private Creature creature;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String note;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected Observation() {}

    public Observation(Creature creature, String author, String note) {
        this.creature = creature;
        this.author = author;
        this.note = note;
    }

    public Long getId() { return id; }
    public Creature getCreature() { return creature; }
    public String getAuthor() { return author; }
    public String getNote() { return note; }
    public Instant getCreatedAt() { return createdAt; }
}
