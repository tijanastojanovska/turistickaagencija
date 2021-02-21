package com.example.turistickaagencija.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Adresa {
    @Id
    private Long id;
  private String address;
   private String area;
   private String name;
    private Long latitude;
    private Long longitude;

    public Adresa(Long id, String address, String area, String name, Long latitude, Long longitude) {
        this.id = id;
        this.address = address;
        this.area = area;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Adresa(String name,Long latitude, Long longitude) {
        this.name=name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Adresa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }
}
