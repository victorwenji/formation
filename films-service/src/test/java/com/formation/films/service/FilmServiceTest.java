package com.formation.films.service;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.formation.films.domain.dtos.FilmCreateRequest;
import com.formation.films.service.exceptions.FilmNotFoundException;
import com.formation.films.service.exceptions.TitreDejaExistantException;
import com.formation.films.service.repositories.RealisateurRepository;
import com.formation.films.service.repositories.FilmRepository;

@ExtendWith(MockitoExtension.class)
class FilmServiceTest {

    @Mock FilmRepository filmRepo;
    @Mock RealisateurRepository realisateurRepo;
    @Mock FilmMapper mapper;

    @InjectMocks FilmService service;

    @Test
    void create_titreDejaExistant_leveException() {
        when(filmRepo.existsByTitle("X")).thenReturn(true);

        assertThatThrownBy(() -> service.create(
                new FilmCreateRequest("X", "A", 2020)))
            .isInstanceOf(TitreDejaExistantException.class);

        verify(filmRepo, never()).save(any());
    }

    @Test
    void findById_inconnu_leveException() {
        when(filmRepo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.findById(99L))
            .isInstanceOf(FilmNotFoundException.class);
    }
}