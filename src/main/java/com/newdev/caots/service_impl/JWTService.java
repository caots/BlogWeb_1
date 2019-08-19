package com.newdev.caots.service_impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.newdev.caots.security.SecurityConstants.*;


@Service
public class JWTService {

    //Tạo token
    public String generateToken(String username) {

        return TOKEN_PREFIX + JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }


    //Giải mã token
    public String decode(String token) {

        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build().verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
    }
}