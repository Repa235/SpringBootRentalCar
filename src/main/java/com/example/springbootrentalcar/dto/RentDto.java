package com.example.springbootrentalcar.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Data
public class RentDto {

    private int id;
    @NotBlank(message = "Start date is mandatory")
    private LocalDate startDate;
    @NotBlank(message = "End date is mandatory")
    private LocalDate endDate;
    private boolean isApproved;
    private UserDto userDto;
    private VehicleDto vehicleDto;

}
