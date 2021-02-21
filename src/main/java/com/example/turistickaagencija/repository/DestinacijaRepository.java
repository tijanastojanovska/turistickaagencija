package com.example.turistickaagencija.repository;

import com.example.turistickaagencija.model.Destinacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinacijaRepository extends JpaRepository<Destinacija,Long> {

    List<Destinacija> findAllByDrzhavaContaining(String name);

}
