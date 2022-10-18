package com.example.springbootrentalcar.service;

import com.example.springbootrentalcar.dto.VehicleDto;
import com.example.springbootrentalcar.entity.Vehicle;
import com.example.springbootrentalcar.specifications.DateInterval;

import java.time.LocalDate;
import java.util.List;

public interface VehicleService {
    VehicleDto getVehicleById(int id);
    List<VehicleDto> getAllVehicles();
    void saveOrUpdateVehicle(VehicleDto vehicleDto);
    void deleteVehicle(int id);
    List<VehicleDto> getFreeVehicleInRange(DateInterval dateInterval);


}
