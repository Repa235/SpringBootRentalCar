package com.example.springbootrentalcar.service;

import com.example.springbootrentalcar.entity.Rent;
import com.example.springbootrentalcar.specifications.DateInterval;

import java.util.List;

public interface RentService {

    Rent getRentById(int id);
    List<Rent> getAllRents();
    List<Rent> rentsInRange(DateInterval dateInterval);
    void saveOrUpdateRent(Rent r);
    void deleteRent(int id);
    void approveRent(Rent r);



}
