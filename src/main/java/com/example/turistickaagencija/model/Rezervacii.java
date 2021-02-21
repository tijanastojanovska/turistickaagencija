package com.example.turistickaagencija.model;

import com.example.turistickaagencija.enumerations.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Rezervacii {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Linija> rezervirani_linii;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Rezervacii() {
    }

    public Rezervacii(User user) {
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.rezervirani_linii = new ArrayList<>();
        this.status = Status.CREATED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Linija> getRezervirani_linii() {
        return rezervirani_linii;
    }

    public void setRezervirani_linii(List<Linija> rezervirani_linii) {
        this.rezervirani_linii = rezervirani_linii;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
