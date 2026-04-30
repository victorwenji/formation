package com.formation.films.service;

import org.springframework.stereotype.Component;

import com.formation.films.domain.dtos.FilmResponse;
import com.formation.films.domain.entities.Film;

@Component
public class FilmMapper {

    public FilmResponse toResponse(Film film) {
        return new FilmResponse(
            film.getId(),
            film.getTitle(),
            film.getRealisateur().getName(),
            film.getYear()
        );
    }
}