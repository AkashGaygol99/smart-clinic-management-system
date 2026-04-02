package com.smartclinic.security;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String generateToken(String email) {
        return "mock-jwt-token";
    }
}
