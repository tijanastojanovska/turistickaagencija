package com.example.turistickaagencija.service;

import com.example.turistickaagencija.enumerations.Role;
import com.example.turistickaagencija.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
    User register(String username, String password, String repeatPassword, String ime, String prezime, Role role);
}
