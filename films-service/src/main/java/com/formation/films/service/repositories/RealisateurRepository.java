package com.formation.films.service.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.films.domain.entities.Realisateur;

public interface RealisateurRepository extends JpaRepository<Realisateur, Long> {
    Optional<Realisateur> findByName(String name);
}