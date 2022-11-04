package com.example.springbootrentalcar.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){ super("User not found"); }

}
