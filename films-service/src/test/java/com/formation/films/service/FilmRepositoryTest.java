package com.formation.films.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.formation.films.domain.entities.Realisateur;
import com.formation.films.domain.entities.Film;
import com.formation.films.service.repositories.FilmRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
class FilmRepositoryTest {

    @Autowired FilmRepository filmRepo;
    @Autowired EntityManager em;

    @Test
    void findByTitle_existant_renvoieLeFilm() {
        Realisateur bloch = new Realisateur("Joshua Bloch");
        em.persist(bloch);
        em.persist(new Film("john le retour", bloch, 2018));
        em.flush();

        assertThat(filmRepo.findByTitle("john le retour")).isPresent();
    }

    @Test
    void findByTitle_inconnu_renvoieEmpty() {
        assertThat(filmRepo.findByTitle("inconnu")).isEmpty();
    }

    @Test
    void existsByTitle() {
        Realisateur a = new Realisateur("A");
        em.persist(a);
        em.persist(new Film("X", a, 2020));
        em.flush();

        assertThat(filmRepo.existsByTitle("X")).isTrue();
        assertThat(filmRepo.existsByTitle("Y")).isFalse();
    }
}