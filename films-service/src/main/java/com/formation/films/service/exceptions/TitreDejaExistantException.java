package com.formation.films.service.exceptions;

public class TitreDejaExistantException extends RuntimeException {
    public TitreDejaExistantException(String title) {
        super("Un film avec le titre '" + title + "' existe déjà.");
    }
}