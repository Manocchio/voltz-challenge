package com.challenge.voltz.application.web.operations;

import com.challenge.voltz.application.web.payloads.requests.UserLoginV1;
import com.challenge.voltz.application.web.payloads.responses.AuthenticationResponseV1;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationOperations  {
    @PostMapping
    public ResponseEntity<AuthenticationResponseV1> login(@RequestBody UserLoginV1 loginrequest);
}
