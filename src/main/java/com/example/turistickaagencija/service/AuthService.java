package com.example.turistickaagencija.service;

import com.example.turistickaagencija.model.User;

public interface AuthService {
    User login(String username, String password);
}

