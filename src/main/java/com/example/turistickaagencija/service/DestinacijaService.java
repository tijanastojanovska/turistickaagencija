package com.example.turistickaagencija.service;

import com.example.turistickaagencija.model.Destinacija;

import java.util.List;

public interface DestinacijaService {
    Destinacija create(String ime,String drzhava,Double latitude, Double longitude);
    Destinacija update(Long id,String ime,String drzhava,Double latitude, Double longitude);
    Destinacija delete(Long destinacijaId);
    Destinacija findById(Long destinacijaId);
    List<Destinacija> listAll();
    List<Destinacija> listAllByDrzhava(String name);
}
