package com.example.turistickaagencija.service;

import com.example.turistickaagencija.model.Linija;
import com.example.turistickaagencija.model.Rezervacii;

import java.util.List;

public interface RezervaciiService {
    List<Linija> listAllReservations(Long id);
    Rezervacii getActiveRezervacii(String username);
    Rezervacii createRezervacija(String username, Long linijaId);
    Rezervacii deleteRezervacija(Long id);
}
