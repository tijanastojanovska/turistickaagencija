package com.example.turistickaagencija.service.impl;

import com.example.turistickaagencija.exceptions.InvalidKompanijaException;
import com.example.turistickaagencija.model.Kompanija;
import com.example.turistickaagencija.model.Linija;
import com.example.turistickaagencija.repository.KompanijaRepository;
import com.example.turistickaagencija.repository.LinijaRepository;
import com.example.turistickaagencija.service.KompanijaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KompanijaServiceImpl implements KompanijaService {
    private final KompanijaRepository kompanijaRepository;

    public KompanijaServiceImpl(KompanijaRepository kompanijaRepository) {
        this.kompanijaRepository = kompanijaRepository;
    }

    @Override
    public Kompanija findById(Long kompanijaId) {
        return this.kompanijaRepository.findById(kompanijaId).orElseThrow(InvalidKompanijaException::new);
    }

    @Override
    public Kompanija create(String ime) {

        Kompanija kompanija=new Kompanija(ime);
        return this.kompanijaRepository.save(kompanija);
    }

    @Override
    public Kompanija update(Long id, String ime) {
        Kompanija kompanija=this.findById(id);
        kompanija.setIme_kompanija(ime);
        return this.kompanijaRepository.save(kompanija);
    }

    @Override
    public Kompanija delete(Long id) {

            Kompanija kompanija = this.findById(id);
            this.kompanijaRepository.delete(kompanija);
            return kompanija;
        }


    @Override
    public List<Kompanija> listAll(Long page) {

        if(page==0)
            return this.kompanijaRepository.findAll();
        else{
            return this.kompanijaRepository.findAll()
                    .stream().skip((page - 1) * 5)
                    .limit(5)
                    .collect(Collectors.toList());
        }
    }
}
