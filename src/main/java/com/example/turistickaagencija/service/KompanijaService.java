package com.example.turistickaagencija.service;

import com.example.turistickaagencija.model.Kompanija;

import java.util.List;

public interface KompanijaService {
    Kompanija findById(Long kompanijaId);
    Kompanija create(String ime);
    Kompanija update(Long id,String ime);
    Kompanija delete(Long id);
    List<Kompanija> listAll();
}
