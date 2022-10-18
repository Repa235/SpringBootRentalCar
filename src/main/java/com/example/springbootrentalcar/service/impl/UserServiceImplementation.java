package com.example.springbootrentalcar.service.impl;

import com.example.springbootrentalcar.dto.UserDto;
import com.example.springbootrentalcar.entity.User;
import com.example.springbootrentalcar.mapper.UserMapper;
import com.example.springbootrentalcar.repository.UserRepository;
import com.example.springbootrentalcar.service.UserService;
import com.example.springbootrentalcar.specifications.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    @Override
    public UserDto getUserById(int id) {
        return userMapper.convertToDto(userRepository.findById(id).get());
    }

    @Override
    public void saveOrUpdateUser(UserDto userDto) {
        userRepository.save(userMapper.convertToUser(userDto));
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getCustomers() {
        List<User> customers = userRepository.getUserByIsAdminFalse();
        return userMapper.convertToDtoList(customers);
    }


    @Override
    public List<UserDto> getCustomerByParam(String filter, String textToSearch) {
        if (!filter.isBlank() && !textToSearch.isBlank()) {
            UserSpecification userSpecification = new UserSpecification(filter, textToSearch);
            return userMapper.convertToDtoList(userRepository.findAll(userSpecification));
        }
        return null;
    }

}
