package com.example.springbootrentalcar.service.impl;

import com.example.springbootrentalcar.entity.User;
import com.example.springbootrentalcar.repository.UserRepository;
import com.example.springbootrentalcar.service.UserService;
import com.example.springbootrentalcar.specifications.UserSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void saveOrUpdateUser(User c) {
        userRepository.save(c);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getCustomers() {
        return userRepository.getUserByIsAdminFalse();
    }


    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
   public List<User> getCustomerByParam(String filter, String textToSearch) {
        UserSpecification userSpecification = new UserSpecification(filter,textToSearch);
        return userRepository.findAll(userSpecification);
    }
}
