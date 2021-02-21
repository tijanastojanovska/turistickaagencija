package com.example.turistickaagencija.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class LinijaAlreadyReserved extends RuntimeException{

    public LinijaAlreadyReserved(Long id, String username) {
        super(String.format("Linija with id: %d is already reserved for user with username %s", id, username));
    }
}