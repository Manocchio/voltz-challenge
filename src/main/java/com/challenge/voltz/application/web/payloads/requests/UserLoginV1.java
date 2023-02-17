package com.challenge.voltz.application.web.payloads.requests;

public class UserLoginV1 {
    private String name;
    private String password;

    public UserLoginV1(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
