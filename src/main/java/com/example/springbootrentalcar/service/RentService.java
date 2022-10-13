package com.example.springbootrentalcar.service;

import com.example.springbootrentalcar.entity.Rent;

import java.util.List;

public interface RentService {

    public Rent getRentById(int id);
    public List<Rent> getAllRents();
    public void saveOrUpdateRent(Rent r);
    public void deleteRent(int id);
    public void approveRent(Rent r);

}
