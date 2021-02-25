package com.example.turistickaagencija.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Entity
public class Linija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_linija;
    //@DateTimeFormat (pattern="MM/dd/yyyy")
    private LocalDate vreme;
    private  float cena;
    @ManyToOne
    Destinacija pocetna;
    @ManyToOne
    Destinacija krajna;
    @ManyToMany
    List<Kompanija> kompanii;
    @Transient
    List<Destinacija> destinacii;

    public Linija() {
    }

    public LocalDate getVreme() {
        return vreme;
    }

    public void setVreme(LocalDate vreme) {
        this.vreme = vreme;
    }

    public Linija(LocalDate vreme, float cena, Destinacija pocetna, Destinacija krajna, List<Kompanija> kompanii) {
        this.vreme = vreme;
        this.cena = cena;
        this.pocetna = pocetna;
        this.krajna = krajna;
        this.kompanii = kompanii;
    }




    public List<Destinacija> getDestinacii() {
        return destinacii;
    }

    public void setDestinacii(List<Destinacija> destinacii) {
        this.destinacii = destinacii;
    }

    public Destinacija getPocetna() {
        return pocetna;
    }

    public void setPocetna(Destinacija pocetna) {
        this.pocetna = pocetna;
    }

    public Destinacija getKrajna() {
        return krajna;
    }

    public void setKrajna(Destinacija krajna) {
        this.krajna = krajna;
    }

    public List<Kompanija> getKompanii() {
        return kompanii;
    }

    public void setKompanii(List<Kompanija> kompanii) {
        this.kompanii = kompanii;
    }

    public Long getId_linija() {
        return id_linija;
    }

    public void setId_linija(Long id_linija) {
        this.id_linija = id_linija;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public Linija(Long id_linija, float cena, Destinacija pocetna, Destinacija krajna, List<Kompanija> kompanii) {
        this.id_linija = id_linija;

        this.cena = cena;
        this.pocetna = pocetna;
        this.krajna = krajna;
        this.kompanii = kompanii;
    }

    public Linija(float cena, Destinacija pocetna, Destinacija krajna, List<Kompanija> kompanii) {
        this.cena = cena;
        this.pocetna = pocetna;
        this.krajna = krajna;
        this.kompanii = kompanii;
    }
}
