package com.example.springbootrentalcar.dto;

import lombok.Data;
import java.time.LocalDate;


@Data
public class UserDto {

    private int id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private boolean isAdmin;
    private String username;
    private String password;

}




