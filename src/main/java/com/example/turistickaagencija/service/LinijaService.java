package com.example.turistickaagencija.service;

import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.model.Linija;
import com.example.turistickaagencija.model.Statistika;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LinijaService {
    Linija create(Date vreme, float cena, Long pocetna, Long krajna, List<Long> kompanii);
    Linija update(Long id,Date vreme,float cena, Long pocetna, Long krajna,List<Long> kompanii);
    List<Linija> listAllLinii();
    Linija findById(Long id);
    Linija delete(Long id);
    List<Linija> listLiniiByDestinacii(Long destinacijaId);
    List<Long> listCompanyStatisics ();
    List<String> listCompanies ();
}
