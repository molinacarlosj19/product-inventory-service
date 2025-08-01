package com.carlosmolina.productinventoryservice.controller;

import com.carlosmolina.productinventoryservice.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam String username) {
        // For demo, we just issue a token for any username — improve with real user validation
        return jwtUtil.generateToken(username);
    }
}
