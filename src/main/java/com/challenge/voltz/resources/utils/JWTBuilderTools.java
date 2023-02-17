package com.challenge.voltz.resources.utils;

import com.challenge.voltz.domain.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


import java.io.Serializable;
import java.util.Date;

@Component
public class JWTBuilderTools implements Serializable {
    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    public JWTBuilderTools() {
    }

    public String generateToken(Authentication authentication) {

        User usuario = (User) authentication.getPrincipal();

        Date now = new Date(System.currentTimeMillis());
        Date exp = new Date(System.currentTimeMillis() + 280000000L);

        return Jwts.builder().setIssuer("voltz")
                .setSubject(String.valueOf(usuario.getId()))
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Integer getTokenId(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Integer.valueOf(body.getSubject());
    }
}
