package com.example.springbootrentalcar.specifications;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DateInterval {
    private LocalDate startDate;
    private LocalDate endDate;
}
