package com.example.springbootrentalcar.security;

import com.example.springbootrentalcar.security.filters.JWTAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static String REALM = "SpringBootRentalCar";
    private static final String[] USER_MATCHER = {
            "/api/vehicle/free",

    };
    private static final String[] ADMIN_MATCHER = {
            //Rent
            "/api/rent",
            "/api/rent/approve/**",
            //User
            "/api/user",
            "/api/user/search/**/**",
            "/api/user/remove/**",
            //Vehicle
            "/api/vehicle/get/**",
            "/api/vehicle/addOrUpdate",
            "/api/vehicle/remove/{id}",


    };

    private static final String[] USER_AND_ADMIN_MATCHER = {
            //Rent
            "/api/rent/get/**",
            "/api/rent/rentsOf/**",
            "/api/rent/addOrUpdate",
            "/api/rent/remove/**",
            //User
            "/api/user",
            "/api/user/get/**",
            "/api/user/addOrUpdate",
            //Vehicle
            "/api/vehicle/get/**"

    };

    private static final String[] PERMITALL_MATCHER = {
            "/api/vehicle", "/api/auth", "/resources/**"
    };


    private final UserDetailsService customUserDetailsService;

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers(PERMITALL_MATCHER).permitAll()
                //If an unauthorized user need to access a path see row 26 of JWTAuthorizationFilter.java
                .and().authorizeRequests()
                .antMatchers(USER_AND_ADMIN_MATCHER).hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .antMatchers(USER_MATCHER).hasAnyAuthority("ROLE_USER")
                .antMatchers(ADMIN_MATCHER).hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
                .and()
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.cors().and().csrf().disable()
        ;
    }

    @Bean
    public AuthEntryPoint getBasicAuthEntryPoint() {
        return new AuthEntryPoint();
    }


}