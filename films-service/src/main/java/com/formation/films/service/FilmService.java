package com.formation.films.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formation.films.domain.dtos.FilmCreateRequest;
import com.formation.films.domain.dtos.FilmResponse;
import com.formation.films.domain.dtos.FilmUpdateRequest;
import com.formation.films.domain.entities.Film;
import com.formation.films.domain.entities.Realisateur;
import com.formation.films.service.exceptions.FilmNotFoundException;
import com.formation.films.service.exceptions.TitreDejaExistantException;
import com.formation.films.service.repositories.RealisateurRepository;
import com.formation.films.service.repositories.FilmRepository;


@Service
@Transactional
public class FilmService {

    private final FilmRepository filmRepo;
    private final RealisateurRepository realisateurRepo;
    private final FilmMapper mapper;

    public FilmService(FilmRepository filmRepo, RealisateurRepository realisateurRepo, FilmMapper mapper) {
        this.filmRepo = filmRepo;
        this.realisateurRepo = realisateurRepo;
        this.mapper = mapper;
    }

    public FilmResponse create(FilmCreateRequest req) {
        if (filmRepo.existsByTitle(req.title())) {
            throw new TitreDejaExistantException(req.title());
        }
        Realisateur realisateur = realisateurRepo.findByName(req.realisateurName())
            .orElseGet(() -> realisateurRepo.save(new Realisateur(req.realisateurName())));

        Film film = new Film(req.title(), realisateur, req.year());
        Film saved = filmRepo.save(film);
        return mapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public FilmResponse findById(Long id) {
        Film film = filmRepo.findById(id)
            .orElseThrow(() -> new FilmNotFoundException(id));
        return mapper.toResponse(film);
    }

    @Transactional(readOnly = true)
    public Page<FilmResponse> findAll(Pageable pageable) {
        return filmRepo.findAll(pageable).map(mapper::toResponse);
    }

    public FilmResponse update(Long id, FilmUpdateRequest req) {
        Film film = filmRepo.findById(id)
            .orElseThrow(() -> new FilmNotFoundException(id));

        Realisateur realisateur = realisateurRepo.findByName(req.realisateurName())
            .orElseGet(() -> realisateurRepo.save(new Realisateur(req.realisateurName())));

        film.setTitle(req.title());
        film.setRealisateur(realisateur);
        film.setYear(req.year());
        // pas besoin de filmRepo.save() : entité managée → autoflush
        return mapper.toResponse(film);
    }

    public void delete(Long id) {
        if (!filmRepo.existsById(id)) {
            throw new FilmNotFoundException(id);
        }
        filmRepo.deleteById(id);
    }
}