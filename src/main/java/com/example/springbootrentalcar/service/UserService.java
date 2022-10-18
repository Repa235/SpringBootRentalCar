package com.example.springbootrentalcar.service;

import com.example.springbootrentalcar.dto.UserDto;
import com.example.springbootrentalcar.entity.User;

import java.util.List;

public interface UserService {
    UserDto getUserById(int id);
    void saveOrUpdateUser(UserDto c);
    void deleteUser(int id);
    public List<UserDto> getCustomers();
    List<UserDto> getCustomerByParam(String filter, String textToSearch);
}
