package com.example.springbootrentalcar.service;

import com.example.springbootrentalcar.entity.Vehicle;
import com.example.springbootrentalcar.specifications.DateInterval;

import java.time.LocalDate;
import java.util.List;

public interface VehicleService {
    Vehicle getVehicleById(int id);
    List<Vehicle> getAllVehicles();
    void saveOrUpdateVehicle(Vehicle v);
    void deleteVehicle(Vehicle v);
    List<Vehicle> getFreeVehicleInRange(DateInterval dateInterval);


}
