package com.example.turistickaagencija.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LinijaNotFoundException extends RuntimeException{

    public LinijaNotFoundException(Long id) {
        super(String.format("Linija with id: %d was not found", id));
    }
}
