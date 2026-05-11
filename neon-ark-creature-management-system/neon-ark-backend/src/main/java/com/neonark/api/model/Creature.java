package com.neonark.api.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;

import jakarta.persistence.*;

@Entity
@Table(name = "creatures")
public class Creature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CreatureStatus status; // Healthy, Injured, Critical, Removed

    @ManyToOne
    @JoinColumn(name = "habitat_id", nullable = false)
    private Habitat habitat;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    public Creature() {}

    public Creature(String name, CreatureStatus status, Habitat habitat) {
        this.name = name;
        this.status = status;
        this.habitat = habitat;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public CreatureStatus getStatus() { return status; }
    public Habitat getHabitat() { return habitat; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setStatus(CreatureStatus status) { this.status = status; }
    public void setHabitat(Habitat habitat) { this.habitat = habitat; }
}