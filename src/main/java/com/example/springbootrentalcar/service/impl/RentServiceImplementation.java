package com.example.springbootrentalcar.service.impl;

import com.example.springbootrentalcar.dto.RentDto;
import com.example.springbootrentalcar.entity.Rent;
import com.example.springbootrentalcar.mapper.RentMapper;
import com.example.springbootrentalcar.repository.RentRepository;
import com.example.springbootrentalcar.service.RentService;
import com.example.springbootrentalcar.specifications.DateInterval;
import com.example.springbootrentalcar.specifications.RentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RentServiceImplementation implements RentService {

    private final RentRepository rentRepository;
    private final RentMapper rentMapper;


    @Override
    public RentDto getRentById(int id) {
        return rentMapper.convertToDto(rentRepository.getReferenceById(id));
    }

    @Override
    public List<RentDto> getAllRents() {
        return rentMapper.convertToDtoList(rentRepository.findAll());
    }

    @Override
    public List<RentDto> rentsInRange(DateInterval dateInterval) {
        RentSpecification specForRents = new RentSpecification(dateInterval);
        return rentMapper.convertToDtoList(rentRepository.findAll(specForRents));
    }

    @Override
    public void saveOrUpdateRent(RentDto rentDto) {
        rentRepository.save(rentMapper.convertToRent(rentDto));
    }

    @Override
    public void deleteRent(int id) {
        rentRepository.deleteById(id);
    }

}
