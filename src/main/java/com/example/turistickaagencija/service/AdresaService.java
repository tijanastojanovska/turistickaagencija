package com.example.turistickaagencija.service;

import com.example.turistickaagencija.model.Adresa;

import java.util.List;

public interface AdresaService {
    List<Adresa> listAll();
    Adresa create(String name,Long latitude,Long longitude);
}
