package com.formation.films.domain.dtos;

public record FilmResponse(
    Long id,
    String title,
    String realisateurName,
    Integer year
) {}