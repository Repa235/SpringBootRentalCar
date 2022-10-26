package com.example.springbootrentalcar.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springbootrentalcar.dto.UserDto;
import com.example.springbootrentalcar.exception.AuthException;
import com.example.springbootrentalcar.mapper.UserMapper;
import com.example.springbootrentalcar.security.AuthenticationConfigConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public Map<String, Object> authUser(String username, String password) {
        UserDto uDto = userService.getUserByUsername(username);
        if (uDto!=null && passwordEncoder.matches(password, uDto.getPassword())) {
            String role = (uDto.isAdmin()) ? "ROLE_ADMIN" : "ROLE_USER";
            String token =  JWT.create()
                    .withSubject(uDto.getUsername())
                    .withClaim("role", role)
                    .withExpiresAt(new Date(System.currentTimeMillis() + AuthenticationConfigConstants.EXPIRATION_TIME))
                    .sign(Algorithm.HMAC256(AuthenticationConfigConstants.SECRET.getBytes()));
            Map<String, Object> claimMap = new HashMap<>(0);
            claimMap.put("token",token);
            claimMap.put("role",role);
            return claimMap;
        }
        throw new AuthException();
    }


}
