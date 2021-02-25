package com.example.turistickaagencija.service.impl;

import com.example.turistickaagencija.exceptions.InvalidLinijaIdException;
import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.model.Kompanija;
import com.example.turistickaagencija.model.Linija;
import com.example.turistickaagencija.repository.DestinacijaRepository;
import com.example.turistickaagencija.repository.KompanijaRepository;
import com.example.turistickaagencija.repository.LinijaRepository;
import com.example.turistickaagencija.service.LinijaService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LinijaServiceImpl implements LinijaService {
    private final LinijaRepository linijaRepository;
    private final DestinacijaRepository destinacijaRepository;
    private final KompanijaRepository kompanijaRepository;


    public LinijaServiceImpl(LinijaRepository linijaRepository, DestinacijaRepository destinacijaRepository, KompanijaRepository kompanijaRepository) {
        this.linijaRepository = linijaRepository;
        this.destinacijaRepository = destinacijaRepository;
        this.kompanijaRepository = kompanijaRepository;
    }

    @Override
    public Linija create(LocalDate vreme, float cena, Long pocetna, Long krajna, List<Long> kompanii) {
       Destinacija p=this.destinacijaRepository.findById(pocetna).get();
       Destinacija k=this.destinacijaRepository.findById(krajna).get();
        List<Kompanija> komp = this.kompanijaRepository.findAllById(kompanii);
        Linija linija=new Linija(vreme,cena,p,k,komp);
        return this.linijaRepository.save(linija);
    }

    @Override
    public Linija update(Long id,LocalDate vreme,float cena,Long pocetna, Long krajna, List<Long> kompanii) {
        Linija linija = this.findById(id);
        linija.setCena(cena);
        Destinacija p=this.destinacijaRepository.findById(pocetna).get();
        Destinacija k=this.destinacijaRepository.findById(krajna).get();
        linija.setPocetna(p);
        linija.setKrajna(k);
        linija.setVreme(vreme);
        List<Kompanija> komp = this.kompanijaRepository.findAllById(kompanii);
        linija.setKompanii(komp);

        return this.linijaRepository.save(linija);
    }

    @Override
    public List<Linija> listAllLinii(Long page) {
        if(page == 0) {
            return this.linijaRepository.findAll();
        }
        else{
            return this.linijaRepository.findAll()
                    .stream().skip((page-1)*5)
                    .limit(5)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Linija findById(Long id) {
        return this.linijaRepository.findById(id).orElseThrow(InvalidLinijaIdException::new);


    }



    @Override
    public Linija delete(Long id) {
        Linija linija = this.findById(id);
        this.linijaRepository.delete(linija);
        return linija;
    }

    @Override
    public List<Linija> listLiniiByDestinacii(Long destinacijaId, Long page) {
        Destinacija destinacija = destinacijaId != null ? this.destinacijaRepository.findById(destinacijaId).orElse((Destinacija) null) : null;
        List<Linija> linii = new ArrayList<>();
        if (!this.linijaRepository.findAllByKrajna(destinacija).isEmpty())
            linii.addAll(this.linijaRepository.findAllByKrajna(destinacija));
        if (!this.linijaRepository.findAllByPocetna(destinacija).isEmpty())
            linii.addAll(this.linijaRepository.findAllByPocetna(destinacija));
        if (page == 0) {
            return linii;
        } else {
            return linii.stream()
                    .skip((page - 1) * 5)
                    .limit(5)
                    .collect(Collectors.toList());
        }
    }
    @Override
    public List<Long> listCompanyStatisics() {
        List<Linija> linii = this.linijaRepository.findAll();
        List<String> kompanii = new ArrayList<>();
        for (Linija linija: linii
        ) {
            kompanii.add(linija.getKompanii().stream().findFirst().get().getIme_kompanija());
        };
        Map<String, List<String>> result = kompanii.stream().collect(Collectors.groupingBy(i -> i , LinkedHashMap::new, Collectors.toList()));
        List<Long> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : result.entrySet()) {
           res.add((long) entry.getValue().size());
        }
        return res;
    }

    @Override
    public List<String> listCompanies() {
        List<Linija> linii = this.linijaRepository.findAll();
        List<String> kompanii = new ArrayList<>();
        for (Linija linija: linii
        ) {
            kompanii.add(linija.getKompanii().stream().findFirst().get().getIme_kompanija());
        };
        Map<String, List<String>> result = kompanii.stream().collect(Collectors.groupingBy(i -> i , LinkedHashMap::new, Collectors.toList()));
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : result.entrySet()) {
            res.add(entry.getKey());
        }
        return res;
    }
}

