package com.challenge.voltz.application.web.exceptions;

public class ErrorV1 {
    private String message;

    private String origin;
    private Integer status;

    public ErrorV1(String message, String origin, Integer status) {
        this.message = message;
        this.origin = origin;
        this.status = status;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
