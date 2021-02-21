package com.example.turistickaagencija.service.impl;


import com.example.turistickaagencija.exceptions.InvalidArgumentsException;
import com.example.turistickaagencija.exceptions.InvalidUserCredentialsException;
import com.example.turistickaagencija.model.User;
import com.example.turistickaagencija.repository.UserRepository;
import com.example.turistickaagencija.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
