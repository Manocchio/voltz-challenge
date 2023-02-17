package com.challenge.voltz.application.web;

import com.challenge.voltz.application.web.payloads.requests.UserLoginV1;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public void login(@RequestBody UserLoginV1 loginrequest){

    }
}
