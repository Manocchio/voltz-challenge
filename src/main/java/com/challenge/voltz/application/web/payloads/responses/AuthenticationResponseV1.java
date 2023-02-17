package com.challenge.voltz.application.web.payloads.responses;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AuthenticationResponseV1 {
    private String token;
    private String forUser;

    private String type = "JWT";
    private Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

    public AuthenticationResponseV1(String token, String forUser) {
        this.token = token;
        this.forUser = forUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getForUser() {
        return forUser;
    }

    public void setForUser(String forUser) {
        this.forUser = forUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
