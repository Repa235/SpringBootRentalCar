package com.example.springbootrentalcar.exception;

public class RentNotFoundException extends RuntimeException{

    public RentNotFoundException(){ super("Rent not found"); }

}
