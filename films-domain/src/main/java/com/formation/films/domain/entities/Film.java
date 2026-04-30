package com.formation.films.domain.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "realisateur_id")
    private Realisateur realisateur;

    @Column(nullable = false, name = "publication_year")
    private Integer year;

    protected Film() {}

    public Film(String title, Realisateur realisateur, Integer year) {
        this.title = title;
        this.realisateur = realisateur;
        this.year = year;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Realisateur getRealisateur() { return realisateur; }
    public Integer getYear() { return year; }

    public void setTitle(String title) { this.title = title; }
    public void setRealisateur(Realisateur realisateur) { this.realisateur = realisateur; }
    public void setYear(Integer year) { this.year = year; }

    @Override
    public boolean equals(Object o) {
        return o instanceof Film f && Objects.equals(id, f.id);
    }
    @Override public int hashCode() { return Objects.hashCode(id); }
}