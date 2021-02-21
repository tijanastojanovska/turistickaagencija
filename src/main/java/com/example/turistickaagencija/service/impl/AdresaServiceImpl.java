package com.example.turistickaagencija.service.impl;

import com.example.turistickaagencija.model.Adresa;
import com.example.turistickaagencija.repository.AdresaRepository;
import com.example.turistickaagencija.service.AdresaService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdresaServiceImpl implements AdresaService {
   private final AdresaRepository adresaRepository;

    public AdresaServiceImpl(AdresaRepository adresaRepository) {
        this.adresaRepository = adresaRepository;
    }

    @Override
    public List<Adresa> listAll() {
        return this.adresaRepository.findAll();
    }

    @Override
    public Adresa create(String name,Long latitude, Long longitude) {
       Adresa adresa=new Adresa(name,latitude,longitude);
        return this.adresaRepository.save(adresa);
    }
}
