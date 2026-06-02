package com.toy.auth.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toy.auth.user.dto.UserMeResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public UserMeResponse user(Authentication auth) {
        String role = auth.getAuthorities()
                .iterator()
                .next()
                .getAuthority();
        return new UserMeResponse(auth.getName(), role);
    }


}