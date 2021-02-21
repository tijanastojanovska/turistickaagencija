package com.example.turistickaagencija.repository;

import com.example.turistickaagencija.enumerations.Status;
import com.example.turistickaagencija.model.Rezervacii;
import com.example.turistickaagencija.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RezervaciiRepository extends JpaRepository<Rezervacii,Long> {
    Optional<Rezervacii> findByUserAndStatus(User user, Status status);
}
