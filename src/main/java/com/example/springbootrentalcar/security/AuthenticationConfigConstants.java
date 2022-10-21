package com.example.springbootrentalcar.security;

public class AuthenticationConfigConstants {
    public static final String SECRET = "Rental_Car_Secret";
    public static final long EXPIRATION_TIME = 86400000; // 1 day
    public static final String HEADER_STRING = "token";
}
