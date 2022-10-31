package com.example.springbootrentalcar.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Data
public class UserDtoFE {

    private Integer id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Surname is mandatory")
    private String surname;
    private LocalDate birthday;
    @NotBlank(message = "Username is mandatory")
    private String username;

}




