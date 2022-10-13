package com.example.springbootrentalcar.service;

import com.example.springbootrentalcar.entity.User;

import java.util.List;

public interface UserService {
    public User getUserById(int id);
    public void saveOrUpdateUser(User c);
    public void deleteUser(int id);
    public List<User> getCustomers();
    public User getUserByUsername(String username);
    public List<User> getCustomerByParam(String filter, String textToSearch);
}
