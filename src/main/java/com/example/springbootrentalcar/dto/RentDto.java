package com.example.springbootrentalcar.dto;


import com.example.springbootrentalcar.entity.User;
import com.example.springbootrentalcar.entity.Vehicle;
import lombok.Data;

import java.time.LocalDate;


@Data
public class RentDto {

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isApproved;
    private User user;
    private Vehicle vehicle;

}
