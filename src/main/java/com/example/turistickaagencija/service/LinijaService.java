package com.example.turistickaagencija.service;

import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.model.Linija;
import com.example.turistickaagencija.model.Statistika;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LinijaService {
    Linija create(LocalDate vreme, float cena, Long pocetna, Long krajna, List<Long> kompanii);
    Linija update(Long id,LocalDate vreme,float cena, Long pocetna, Long krajna,List<Long> kompanii);
    List<Linija> listAllLinii(Long page);
    Linija findById(Long id);
    Linija delete(Long id);
    List<Linija> listLiniiByDestinacii(Long destinacijaId, Long page);
    List<Long> listCompanyStatisics ();
    List<String> listCompanies ();
}
