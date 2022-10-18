package com.example.springbootrentalcar.mapper;


import com.example.springbootrentalcar.dto.RentDto;
import com.example.springbootrentalcar.entity.Rent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RentMapper {
    private final ModelMapper modelMapper;


    public Rent convertToRent(RentDto rentDto) {
        Rent rent = null;
        if (rentDto != null) {
            rent =  modelMapper.map(rentDto, Rent.class);
        }
        return rent;
    }

    public RentDto convertToDto(Rent rent) {
        RentDto rentDto = null;
        if (rent != null) {
            rentDto =  modelMapper.map(rent, RentDto.class);
        }
        return rentDto;
    }

    public List<RentDto> convertToDtoList(List<Rent> rentList) {
        List<RentDto> rentDtoList = new ArrayList<>();
        if (rentDtoList != null) {
            rentDtoList = rentList
                    .stream()
                    .map(source -> modelMapper.map(source, RentDto.class))
                    .collect(Collectors.toList());
        }
        return rentDtoList;
    }
}
