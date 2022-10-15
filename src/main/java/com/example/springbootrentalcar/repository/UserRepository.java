package com.example.springbootrentalcar.repository;


import com.example.springbootrentalcar.entity.User;
import com.example.springbootrentalcar.specifications.UserSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

  public List<User> getUserByIsAdminFalse();

    public User getUserByUsername(String username);



}
