package com.formation.films.domain.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FilmCreateRequest(
    @NotBlank @Size(max = 300) String titre,
    @NotBlank @Size(max = 200) String realisateurNom,
    @NotNull @Min(0) @Max(2100) Integer annee
) {}