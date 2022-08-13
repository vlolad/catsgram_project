package ru.yandex.practicum.catsgram.exceptions;

import java.io.IOException;

public class UserAlreadyExistException extends IOException {

    public UserAlreadyExistException (String message) {
        super(message);
    }
}
