package com.example.springbootrentalcar.exception;

public class VehicleNotFoundException extends RuntimeException{

    public VehicleNotFoundException(){ super("Vehicle not found"); }

}
