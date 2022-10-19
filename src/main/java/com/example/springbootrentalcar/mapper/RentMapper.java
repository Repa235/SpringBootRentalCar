package com.example.springbootrentalcar.mapper;


import com.example.springbootrentalcar.dto.RentDto;
import com.example.springbootrentalcar.entity.Rent;
import com.example.springbootrentalcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RentMapper {
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
private final VehicleMapper vehicleMapper;

    public Rent convertToRent(RentDto rentDto) {
        Rent rent = null;
        if (rentDto != null) {
            rent =  modelMapper.map(rentDto, Rent.class);
            rent.setUser(userMapper.convertToUser(rentDto.getUserDto()));
            rent.setVehicle(vehicleMapper.convertToVehicle(rentDto.getVehicleDto()));
        }
        return rent;
    }

    public RentDto convertToDto(Rent rent) {
        RentDto rentDto = null;
        if (rent != null) {
            rentDto =  modelMapper.map(rent, RentDto.class);
            rentDto.setUserDto(userMapper.convertToDto(rent.getUser()));
            rentDto.setVehicleDto(vehicleMapper.convertToDto(rent.getVehicle()));
        }
        return rentDto;
    }

    public List<RentDto> convertToDtoList(List<Rent> rentList) {
        List<RentDto> rentDtoList = new ArrayList<>();
        if (rentDtoList != null) {
            rentDtoList = rentList
                    .stream()
                    .map(source -> this.convertToDto(source))
                    .collect(Collectors.toList());
        }
        return rentDtoList;
    }
}
