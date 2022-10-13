package com.example.springbootrentalcar.service;

import com.example.springbootrentalcar.entity.Vehicle;

import java.time.LocalDate;
import java.util.List;

public interface VehicleService {
    public Vehicle getVehicleById(int id);
    public List<Vehicle> getAllVehicles();
    public void saveOrUpdateVehicle(Vehicle v);
    public void deleteVehicle(Vehicle v);
    public List<Vehicle> getFreeVehicleInRange(LocalDate startDate, LocalDate endDate);
}
