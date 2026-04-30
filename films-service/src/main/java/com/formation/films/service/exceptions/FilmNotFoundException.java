package com.formation.films.service.exceptions;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(Long id) {
        super("Film introuvable : id=" + id);
    }
    public FilmNotFoundException(String title) {
        super("Film introuvable : title=" + title);
    }
}