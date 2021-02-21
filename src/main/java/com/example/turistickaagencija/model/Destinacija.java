package com.example.turistickaagencija.model;

import javax.persistence.*;

@Entity
public class Destinacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_destinacija;
    private String ime_destinacija;
    private String drzhava;
    private Double latitude;
    private Double longitude;

    public Destinacija() {

    }

    public Destinacija(String ime_destinacija, String drzhava, Double latitude, Double longitude) {
        this.ime_destinacija = ime_destinacija;
        this.drzhava = drzhava;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Destinacija(String ime_destinacija, String drzhava) {
        this.ime_destinacija = ime_destinacija;
        this.drzhava = drzhava;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getId_destinacija() {
        return id_destinacija;
    }

    public void setId_destinacija(Long id_destinacija) {
        this.id_destinacija = id_destinacija;
    }

    public String getIme_destinacija() {
        return ime_destinacija;
    }

    public void setIme_destinacija(String ime_destinacija) {
        this.ime_destinacija = ime_destinacija;
    }

    public String getDrzhava() {
        return drzhava;
    }

    public void setDrzhava(String drzhava) {
        this.drzhava = drzhava;
    }
}
