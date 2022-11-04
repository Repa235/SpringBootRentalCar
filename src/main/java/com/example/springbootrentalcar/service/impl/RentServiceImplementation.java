package com.example.springbootrentalcar.service.impl;

import com.example.springbootrentalcar.dto.RentDto;
import com.example.springbootrentalcar.exception.RentNotFoundException;
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
        if (rentDto.getId() == null) {
            rentRepository.save(rentMapper.convertToRent(rentDto));
        } else {
            RentDto rentToModify = this.getRentById(rentDto.getId());
            if (rentToModify != null) {
                rentToModify=rentMapper.DtoToDto4Modify(rentDto);
                rentRepository.save(rentMapper.convertToRent(rentToModify));
            } else {
                throw new RentNotFoundException();
            }
        }
    }

    @Override
    public void deleteRent(int id) {
        RentDto vehicleToDelete = this.getRentById(id);
        if (vehicleToDelete != null) {
            rentRepository.deleteById(vehicleToDelete.getId());
        } else {
            throw new RentNotFoundException();
        }
    }

    @Override
    public List<RentDto> getRentsByUserId(int id) {
        return rentMapper.convertToDtoList(rentRepository.getRentsByUserId(id));
    }

}
