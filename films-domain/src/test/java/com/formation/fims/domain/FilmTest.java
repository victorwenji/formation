package com.formation.films.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import com.formation.films.domain.entities.Film;
import com.formation.films.domain.entities.Realisateur;

class FilmTest {

    @Test
    void creation_renseigne_les_champs() {
        Realisateur realisateur = new Realisateur("Joshua Bloch");
        Film film = new Film("john le retour", realisateur, 2018);

        assertThat(film.getTitle()).isEqualTo("john le retour");
        assertThat(film.getRealisateur().getName()).isEqualTo("Joshua Bloch");
        assertThat(film.getYear()).isEqualTo(2018);
    }
}