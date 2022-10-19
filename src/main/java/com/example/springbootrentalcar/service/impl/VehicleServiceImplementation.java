package com.example.springbootrentalcar.service.impl;


import com.example.springbootrentalcar.dto.RentDto;
import com.example.springbootrentalcar.dto.VehicleDto;
import com.example.springbootrentalcar.entity.Rent;
import com.example.springbootrentalcar.entity.User;
import com.example.springbootrentalcar.entity.Vehicle;
import com.example.springbootrentalcar.mapper.VehicleMapper;
import com.example.springbootrentalcar.repository.RentRepository;
import com.example.springbootrentalcar.repository.VehicleRepository;
import com.example.springbootrentalcar.service.RentService;
import com.example.springbootrentalcar.service.VehicleService;
import com.example.springbootrentalcar.specifications.DateInterval;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VehicleServiceImplementation implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final RentService rentService;

    private final VehicleMapper vehicleMapper;


    @Override
    public VehicleDto getVehicleById(int id) {
        return vehicleMapper.convertToDto(vehicleRepository.getReferenceById(id));
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        return vehicleMapper.convertToDtoList(vehicleRepository.findAll());
    }

    @Override
    public void saveOrUpdateVehicle(VehicleDto vehicleDto) {
        vehicleRepository.save(vehicleMapper.convertToVehicle(vehicleDto));
    }

    @Override
    public void deleteVehicle(int id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<VehicleDto> getFreeVehicleInRange(DateInterval dateInterval) {
        List<RentDto> rentsInRange = rentService.rentsInRange(dateInterval);
        List<VehicleDto> allVehicles = getAllVehicles();
        for (VehicleDto v : allVehicles) {
            for (RentDto r : rentsInRange) {
               if (v.getId()==r.getVehicleDto().getId()){
                   allVehicles.remove(v);
               }
            }
            break;
        }
        return allVehicles;
    }


}
