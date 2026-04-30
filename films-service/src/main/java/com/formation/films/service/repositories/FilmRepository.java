package com.formation.films.service.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formation.films.domain.entities.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    Optional<Film> findByTitle(String title);

    boolean existsByTitle(String title);

    Page<Film> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}