package com.example.springbootrentalcar.repository;

import com.example.springbootrentalcar.entity.Rent;
import com.example.springbootrentalcar.entity.Vehicle;
import com.example.springbootrentalcar.specifications.DateInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    public List<Vehicle> getFreeVehicleInRange(DateInterval dateInterval);
}
