package com.example.springbootrentalcar.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Data
public class RentDto {

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isApproved;
    private UserDto userDto;
    private VehicleDto vehicleDto;

}
