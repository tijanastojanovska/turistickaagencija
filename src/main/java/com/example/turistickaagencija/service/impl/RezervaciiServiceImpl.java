package com.example.turistickaagencija.service.impl;

import com.example.turistickaagencija.enumerations.Status;
import com.example.turistickaagencija.exceptions.LinijaAlreadyReserved;
import com.example.turistickaagencija.exceptions.LinijaNotFoundException;
import com.example.turistickaagencija.exceptions.ReservationNotFoundException;
import com.example.turistickaagencija.exceptions.UserNotFoundException;
import com.example.turistickaagencija.model.Linija;
import com.example.turistickaagencija.model.Rezervacii;
import com.example.turistickaagencija.model.User;
import com.example.turistickaagencija.repository.LinijaRepository;
import com.example.turistickaagencija.repository.RezervaciiRepository;
import com.example.turistickaagencija.repository.UserRepository;
import com.example.turistickaagencija.service.RezervaciiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RezervaciiServiceImpl implements RezervaciiService {
    private final RezervaciiRepository rezervaciiRepository;
    private final UserRepository userRepository;
    private final LinijaRepository linijaRepository;

    public RezervaciiServiceImpl(RezervaciiRepository rezervaciiRepository, UserRepository userRepository, LinijaRepository linijaRepository) {
        this.rezervaciiRepository = rezervaciiRepository;
        this.userRepository = userRepository;
        this.linijaRepository = linijaRepository;
    }

    @Override
    public List<Linija> listAllReservations(Long id) {
        if(!this.rezervaciiRepository.findById(id).isPresent())
            throw new ReservationNotFoundException(id);
        return this.rezervaciiRepository.findById(id).get().getRezervirani_linii();
    }

    @Override
    public Rezervacii getActiveRezervacii(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.rezervaciiRepository
                .findByUserAndStatus(user, Status.CREATED)
                .orElseGet(() -> {
                    Rezervacii cart = new Rezervacii(user);
                    return this.rezervaciiRepository.save(cart);
                });
    }

    @Override
    public Rezervacii createRezervacija(String username, Long linijaId) {
        Rezervacii rezervacii = this.getActiveRezervacii(username);
        Linija linija = this.linijaRepository.findById(linijaId)
                .orElseThrow(() -> new LinijaNotFoundException(linijaId));
        if(rezervacii.getRezervirani_linii()
                .stream().filter(i -> i.getId_linija().equals(linijaId))
                .collect(Collectors.toList()).size() > 0)
            throw new LinijaAlreadyReserved(linijaId, username);
        rezervacii.getRezervirani_linii().add(linija);
        return this.rezervaciiRepository.save(rezervacii);
    }

    @Override
    public Rezervacii deleteRezervacija(Long id) {
        Rezervacii rezervacija = this.rezervaciiRepository.findById(id).get();
        this.rezervaciiRepository.delete(rezervacija);
        return rezervacija;
    }
}
