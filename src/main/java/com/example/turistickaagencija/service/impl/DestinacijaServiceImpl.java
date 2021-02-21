package com.example.turistickaagencija.service.impl;

import com.example.turistickaagencija.exceptions.InvalidDestinacijaException;
import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.repository.DestinacijaRepository;
import com.example.turistickaagencija.service.DestinacijaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinacijaServiceImpl implements DestinacijaService {
    private final DestinacijaRepository destinacijaRepository;

    public DestinacijaServiceImpl(DestinacijaRepository destinacijaRepository) {
        this.destinacijaRepository = destinacijaRepository;
    }

    @Override
    public Destinacija create(String ime, String drzhava,Double latitude, Double longitude) {
        Destinacija destinacija=new Destinacija(ime,drzhava,latitude,longitude);
        return this.destinacijaRepository.save(destinacija);
    }

    @Override
    public Destinacija update(Long id, String ime, String drzhava,Double latitude, Double longitude) {
        Destinacija destinacija=this.findById(id);
        destinacija.setIme_destinacija(ime);
        destinacija.setDrzhava(drzhava);
        destinacija.setLatitude(latitude);
        destinacija.setLongitude(longitude);
        return this.destinacijaRepository.save(destinacija);
    }


    @Override
    public Destinacija delete(Long destinacijaId) {
        Destinacija destinacija=this.findById(destinacijaId);
        this.destinacijaRepository.delete(destinacija);
        return destinacija;
    }

    @Override
    public Destinacija findById(Long destinacijaId) {
        return this.destinacijaRepository.findById(destinacijaId).orElseThrow(InvalidDestinacijaException::new);
    }

    @Override
    public List<Destinacija> listAll() {
        return  this.destinacijaRepository.findAll();
    }

    @Override
    public List<Destinacija> listAllByDrzhava(String name) {
        return this.destinacijaRepository.findAllByDrzhavaContaining(name);
    }
}
