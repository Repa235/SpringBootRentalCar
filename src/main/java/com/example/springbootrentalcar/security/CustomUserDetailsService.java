package com.example.springbootrentalcar.security;


import com.example.springbootrentalcar.dto.UserDto;
import com.example.springbootrentalcar.service.UserService;
import lombok.RequiredArgsConstructor;

import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service("customUserDetailsService")
@RequiredArgsConstructor
@Log
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Search user with username: "+username);
        UserDto userDto = userService.getUserByUsername(username);
        if (userDto == null) {
            throw new UsernameNotFoundException("User not found");
        }
        User.UserBuilder builder = User.withUsername(userDto.getUsername());
        builder.password(userDto.getPassword());
        if (userDto.isAdmin()) {
            builder.roles("ADMIN");
        } else builder.roles("USER");
        return builder.build();
    }
}