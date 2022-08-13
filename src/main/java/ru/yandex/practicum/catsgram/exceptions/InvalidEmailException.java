package ru.yandex.practicum.catsgram.exceptions;

import java.io.IOException;

public class InvalidEmailException extends IOException {

    public InvalidEmailException(String message){
        super(message);
    }
}
