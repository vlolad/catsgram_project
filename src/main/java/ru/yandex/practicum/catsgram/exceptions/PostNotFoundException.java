package ru.yandex.practicum.catsgram.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PostNotFoundException extends ResponseStatusException {

    public PostNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
