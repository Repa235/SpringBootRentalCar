package com.example.springbootrentalcar.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class VehicleDto {

    private Integer id;
    @NotBlank(message = "Car brand is mandatory")
    private String carBrand;
    @NotBlank(message = "Model is mandatory")
    private String model;
    private int registrationYear;
    private String type;

}