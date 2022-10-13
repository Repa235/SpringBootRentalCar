package com.example.springbootrentalcar.service;

import com.example.springbootrentalcar.entity.User;
import com.example.springbootrentalcar.repository.UserRepository;

import java.util.List;

public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getUserById(int id) {
        return userRepository.getReferenceById(id);
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
        return userRepository.getCustomers();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public List<User> getCustomerByParam(String filter, String textToSearch) {
        return userRepository.getCustomerByParam(filter,textToSearch);
    }
}
