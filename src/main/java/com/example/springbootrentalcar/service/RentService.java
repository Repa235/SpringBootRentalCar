package com.example.springbootrentalcar.service;

import com.example.springbootrentalcar.dto.RentDto;
import com.example.springbootrentalcar.specifications.DateInterval;

import java.util.List;

public interface RentService {

    RentDto getRentById(int id);
    List<RentDto> getAllRents();
    List<RentDto> rentsInRange(DateInterval dateInterval);
    void saveOrUpdateRent(RentDto r);
    void deleteRent(int id);
    List<RentDto> getRentsByUserId(int id);




}
