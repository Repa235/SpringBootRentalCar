package com.example.springbootrentalcar.dto;


import com.example.springbootrentalcar.entity.User;
import com.example.springbootrentalcar.entity.Vehicle;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Data
public class Rent{

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isApproved;
    private User user;
    private Vehicle vehicle;

}
