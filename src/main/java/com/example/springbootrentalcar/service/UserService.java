package com.example.springbootrentalcar.service;

import com.example.springbootrentalcar.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserById(int id);
    void saveOrUpdateUser(UserDto userDto);
    void deleteUser(int id);
    public List<UserDto> getCustomers();
    List<UserDto> getCustomerByParam(String filter, String textToSearch);
}
