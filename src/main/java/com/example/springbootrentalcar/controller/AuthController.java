package com.example.springbootrentalcar.controller;

import com.example.springbootrentalcar.dto.AuthDto;
import com.example.springbootrentalcar.exception.AuthException;
import com.example.springbootrentalcar.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public  Map<String, String> auth(@RequestBody AuthDto authDto) {
        return authService.authUser(authDto.getUsername(), authDto.getPassword());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String authEx(AuthException authException) {
        return authException.getMessage();
    }
}
