package com.example.springbootrentalcar.controller;

import com.example.springbootrentalcar.dto.AuthDto;
import com.example.springbootrentalcar.exception.AuthException;
import com.example.springbootrentalcar.repository.UserRepository;
import com.example.springbootrentalcar.service.AuthService;
import com.example.springbootrentalcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public  Map<String, Object> auth(@RequestBody AuthDto authDto) {
        return authService.authUser(authDto.getUsername(), authDto.getPassword());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String authEx(AuthException authException) {
        return authException.getMessage();
    }
}
