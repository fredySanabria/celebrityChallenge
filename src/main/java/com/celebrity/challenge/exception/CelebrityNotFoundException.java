package com.celebrity.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CelebrityNotFoundException extends RuntimeException {
    public CelebrityNotFoundException(String s) {
        super(s);
    }
}
