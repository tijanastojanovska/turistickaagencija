package com.example.turistickaagencija.model;

public class Statistika {
    public Long broj;

    public Statistika(Long broj, String imeKompanija) {
        this.broj = broj;
        this.imeKompanija = imeKompanija;
    }
    public Statistika(){}
    public String imeKompanija;

    public Long getBroj() {
        return broj;
    }

    public void setBroj(Long broj) {
        this.broj = broj;
    }

    public String getImeKompanija() {
        return imeKompanija;
    }

    public void setImeKompanija(String imeKompanija) {
        this.imeKompanija = imeKompanija;
    }
}
