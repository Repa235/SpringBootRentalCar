package com.example.springbootrentalcar.service.impl;


import com.example.springbootrentalcar.dto.RentDto;
import com.example.springbootrentalcar.dto.VehicleDto;

import com.example.springbootrentalcar.exception.VehicleNotFoundException;
import com.example.springbootrentalcar.mapper.VehicleMapper;
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
        if (vehicleDto.getId() == null) {
            vehicleRepository.save(vehicleMapper.convertToVehicle(vehicleDto));
        } else {
            VehicleDto vehicleToModify = this.getVehicleById(vehicleDto.getId());
            if (vehicleToModify != null) {
                vehicleToModify=vehicleMapper.dtoToDto4Modify(vehicleDto);
                vehicleRepository.save(vehicleMapper.convertToVehicle(vehicleToModify));
            } else {
                throw new VehicleNotFoundException();
            }

        }
    }

    @Override
    public void deleteVehicle(int id) {
        VehicleDto vehicleToDelete = this.getVehicleById(id);
        if (vehicleToDelete != null) {
            vehicleRepository.deleteById(vehicleToDelete.getId());
        } else {
            throw new VehicleNotFoundException();
        }
    }

    @Override
    public List<VehicleDto> getFreeVehicleInRange(DateInterval dateInterval) {
        List<RentDto> rentsInRange = rentService.rentsInRange(dateInterval);
        List<VehicleDto> allVehicles = getAllVehicles();
        for (VehicleDto v : allVehicles) {
            for (RentDto r : rentsInRange) {
                if (v.getId() == r.getVehicleDto().getId()) {
                    allVehicles.remove(v);
                }
            }
        }
        return allVehicles;
    }


}
