package com.example.springbootrentalcar.service;

import com.example.springbootrentalcar.entity.Vehicle;
import com.example.springbootrentalcar.repository.VehicleRepository;

import java.time.LocalDate;
import java.util.List;

public class VehicleServiceImplementation implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImplementation(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    public Vehicle getVehicleById(int id) {
        return vehicleRepository.getReferenceById(id);
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public void saveOrUpdateVehicle(Vehicle v) {
        vehicleRepository.save(v);
    }

    @Override
    public void deleteVehicle(Vehicle v) {
        vehicleRepository.delete(v);
    }

    @Override
    public List<Vehicle> getFreeVehicleInRange(LocalDate startDate, LocalDate endDate) {
        return vehicleRepository.getFreeVehicleInRange(startDate, endDate);
    }
}
