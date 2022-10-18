package com.example.springbootrentalcar.repository;

import com.example.springbootrentalcar.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer>, JpaSpecificationExecutor<Rent> {
}
