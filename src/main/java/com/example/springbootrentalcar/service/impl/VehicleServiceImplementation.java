package com.example.springbootrentalcar.service.impl;

import com.example.springbootrentalcar.entity.Rent;
import com.example.springbootrentalcar.entity.Vehicle;
import com.example.springbootrentalcar.repository.RentRepository;
import com.example.springbootrentalcar.repository.VehicleRepository;
import com.example.springbootrentalcar.service.RentService;
import com.example.springbootrentalcar.service.VehicleService;
import com.example.springbootrentalcar.specifications.DateInterval;
import lombok.RequiredArgsConstructor;


import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class VehicleServiceImplementation implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final RentService rentService;




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
    public List<Vehicle> getFreeVehicleInRange(DateInterval dateInterval) {
        List<Rent> rentsInRange = rentService.rentsInRange(dateInterval);
        List<Vehicle> occupiedVehicles = new ArrayList<>();
        List<Vehicle> allVehicles = getAllVehicles();
        for (Rent r: rentsInRange) {occupiedVehicles.add(r.getVehicle());}
        allVehicles.removeAll(occupiedVehicles);
        return allVehicles;
    }
}
