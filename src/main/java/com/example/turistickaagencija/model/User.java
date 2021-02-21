package com.example.turistickaagencija.model;

import com.example.turistickaagencija.enumerations.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "korisnik")
public class User implements UserDetails {
    @Id
    private String username;
    private String password;
    private String ime;
    private String prezime;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Rezervacii> rezervacii;
    @Enumerated(EnumType.STRING)
    private Role role;
@Transient
    private boolean isAccountNonExpired = true;
    @Transient
    private boolean isAccountNonLocked = true;
    @Transient
    private boolean isCredentialsNonExpired = true;
    @Transient
    private boolean isEnabled = true;
    public User() {
    }

    public User(String username,String password, String ime, String prezime,  Role role) {
        this.username = username;
        this.ime = ime;
        this.prezime = prezime;
        this.password = password;
        this.role = role;
    }

    public List<Rezervacii> getRezervacii() {
        return rezervacii;
    }

    public void setRezervacii(List<Rezervacii> rezervacii) {
        this.rezervacii = rezervacii;
    }

    public User(String username, String password,String ime, String prezime) {
        this.username = username;
        this.ime = ime;
        this.prezime = prezime;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

