package com.example.jwttest.controller;

import com.example.jwttest.config.JwtProvider;
import com.example.jwttest.dto.ResponseToken;
import com.example.jwttest.dto.UserDto;
import com.example.jwttest.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/api")
public class AuthenticationController {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    JwtProvider jwtProvider;


    @Autowired
    AuthenticationManager authenticationManager;



    @PostMapping(value = "/auth/login")
    public HttpEntity<?> login(@RequestBody UserDto userDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
            return ResponseEntity.ok(new ResponseToken(jwtProvider.generate(new HashMap<>(), userDto.getUsername())));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.toString());
        }
    }




}
