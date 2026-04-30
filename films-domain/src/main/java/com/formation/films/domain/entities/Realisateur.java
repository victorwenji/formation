package com.formation.films.domain.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "realisateurs")
public class Realisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 200)
    private String name;

    protected Realisateur() {} // requis par JPA

    public Realisateur(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        return o instanceof Realisateur r && Objects.equals(id, r.id);
    }
    @Override public int hashCode() { return Objects.hashCode(id); }
}