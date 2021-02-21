package com.example.turistickaagencija.model;

import javax.persistence.*;
import java.util.List;
@Entity
public class Kompanija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_kompanija;
    private  String ime_kompanija;


    public Kompanija() {
    }

    public Kompanija(String ime_kompanija) {
        this.ime_kompanija = ime_kompanija;

    }

    public Long getId_kompanija() {
        return id_kompanija;
    }

    public void setId_kompanija(Long id_kompanija) {
        this.id_kompanija = id_kompanija;
    }

    public String getIme_kompanija() {
        return ime_kompanija;
    }

    public void setIme_kompanija(String ime_kompanija) {
        this.ime_kompanija = ime_kompanija;
    }

}
