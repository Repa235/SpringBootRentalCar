package com.example.springbootrentalcar.mapper;

import com.example.springbootrentalcar.dto.UserDto;
import com.example.springbootrentalcar.dto.VehicleDto;
import com.example.springbootrentalcar.entity.Vehicle;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class VehicleMapper {

    private final ModelMapper modelMapper;


    public Vehicle convertToVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = null;
        if (vehicleDto != null) {
            vehicle =  modelMapper.map(vehicleDto, Vehicle.class);
        }
        return vehicle;
    }

    public VehicleDto convertToDto(Vehicle vehicle) {
        VehicleDto vehicleDto = null;
        if (vehicle != null) {
            vehicleDto =  modelMapper.map(vehicle, VehicleDto.class);
        }
        return vehicleDto;
    }

    public VehicleDto dtoToDto4Modify(VehicleDto vehicleToModify) {
        VehicleDto vehicleDto = null;
        if (vehicleToModify != null) {
            vehicleDto =  modelMapper.map(vehicleToModify, VehicleDto.class);
        }
        return vehicleDto;
    }

    public List<VehicleDto> convertToDtoList(List<Vehicle> vehicleList) {
        List<VehicleDto> vehicleDtoList = new ArrayList<>();
        if (vehicleList != null) {
            vehicleDtoList = vehicleList
                    .stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }
        return vehicleDtoList;
    }

}
