package com.example.springbootrentalcar.service.impl;

import com.example.springbootrentalcar.entity.Rent;
import com.example.springbootrentalcar.repository.RentRepository;
import com.example.springbootrentalcar.service.RentService;
import com.example.springbootrentalcar.specifications.DateInterval;
import com.example.springbootrentalcar.specifications.RentSpecification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RentServiceImplementation implements RentService {

    private final RentRepository rentRepository;

    public RentServiceImplementation(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }


    @Override
    public Rent getRentById(int id) {
        return rentRepository.getReferenceById(id);
    }

    @Override
    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    @Override
    public List<Rent> rentsInRange(DateInterval dateInterval) {
        RentSpecification specForRents = new RentSpecification(dateInterval);
        return rentRepository.findAll(specForRents);
    }

    @Override
    public void saveOrUpdateRent(Rent r) {
        rentRepository.save(r);
    }

    @Override
    public void deleteRent(int id) {
        rentRepository.deleteById(id);
    }

    @Override
    public void approveRent(Rent r) {
        r.setApproved(true);
        rentRepository.save(r);
    }
}
