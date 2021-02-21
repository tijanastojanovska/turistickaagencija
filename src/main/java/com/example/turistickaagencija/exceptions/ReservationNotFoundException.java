package com.example.turistickaagencija.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReservationNotFoundException extends RuntimeException{

    public ReservationNotFoundException(Long id) {
        super(String.format("Reservation with id: %d was not found", id));
    }
}