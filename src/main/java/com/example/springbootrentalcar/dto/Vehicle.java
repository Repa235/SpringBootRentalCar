package com.example.springbootrentalcar.dto;


import lombok.Data;


@Data
public class Vehicle{

    private int id;
    private String carBrand;
    private String model;
    private int registrationYear;
    private String type;

}