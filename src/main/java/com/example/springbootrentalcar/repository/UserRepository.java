package com.example.springbootrentalcar.repository;


import com.example.springbootrentalcar.entity.User;
import com.example.springbootrentalcar.specifications.UserSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

  List<User> getUserByIsAdminFalse();
  User getUserByUsername(String username);


}
