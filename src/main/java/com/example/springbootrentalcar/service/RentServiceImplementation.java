package com.example.springbootrentalcar.service;

import com.example.springbootrentalcar.entity.Rent;
import com.example.springbootrentalcar.repository.RentRepository;

import java.util.List;

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
        List<Rent> all = rentRepository.findAll();
        return all;
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
