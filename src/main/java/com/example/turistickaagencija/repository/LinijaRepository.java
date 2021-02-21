package com.example.turistickaagencija.repository;

import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.model.Linija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinijaRepository extends JpaRepository<Linija,Long> {


   List<Linija> findAllByPocetna(Destinacija name);
   List<Linija> findAllByKrajna(Destinacija name);


}

