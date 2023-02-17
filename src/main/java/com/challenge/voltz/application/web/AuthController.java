package com.challenge.voltz.application.web;

import com.challenge.voltz.application.web.operations.AuthenticationOperations;
import com.challenge.voltz.application.web.payloads.requests.UserLoginV1;
import com.challenge.voltz.application.web.payloads.responses.AuthenticationResponseV1;
import com.challenge.voltz.resources.utils.JWTBuilderTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthenticationOperations {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTBuilderTools tokenService;

    @Override
    public ResponseEntity<AuthenticationResponseV1> login(@RequestBody UserLoginV1 loginrequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginrequest.getName(), loginrequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String token = tokenService.generateToken(authentication);

        return ResponseEntity.ok(new AuthenticationResponseV1(token, loginrequest.getName()));
    }
}
