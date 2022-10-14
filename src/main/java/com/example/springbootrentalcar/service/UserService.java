package com.example.springbootrentalcar.service;

import com.example.springbootrentalcar.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);
    void saveOrUpdateUser(User c);
    void deleteUser(int id);
    List<User> getCustomers();
    User getUserByUsername(String username);
  //  List<User> getCustomerByParam(String filter, String textToSearch);
}
