package com.example.springbootrentalcar.service.impl;

import com.example.springbootrentalcar.dto.UserDto;
import com.example.springbootrentalcar.entity.User;
import com.example.springbootrentalcar.mapper.UserMapper;
import com.example.springbootrentalcar.repository.UserRepository;
import com.example.springbootrentalcar.service.UserService;
import com.example.springbootrentalcar.specifications.UserSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImplementation(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDto getUserById(int id) {
        return this.convertToDto(userRepository.findById(id).get());
    }

    @Override
    public void saveOrUpdateUser(UserDto c) {
        userRepository.save(this.convertToUser(c));
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDto> getCustomers() {
        List<User> customers = userRepository.getUserByIsAdminFalse();
        return this.convertToDtoList(customers);
    }


    @Override
   public List<UserDto> getCustomerByParam(String filter, String textToSearch) {
        UserSpecification userSpecification = new UserSpecification(filter,textToSearch);
        return this.convertToDtoList(userRepository.findAll(userSpecification));
    }


    private User convertToUser(UserDto userDto) {
        User user = null;
        if (userDto != null) {
            user =  modelMapper.map(userDto, User.class);
        }
        return user;
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = null;
        if (user != null) {
            userDto =  modelMapper.map(user, UserDto.class);
        }
        return userDto;
    }

    private List<UserDto> convertToDtoList(List<User> usersList) {
        List<UserDto> usersDto = new ArrayList<>();
        if (usersList != null) {
            usersDto = usersList
                    .stream()
                    .map(source -> modelMapper.map(source, UserDto.class))
                    .collect(Collectors.toList());
        }
        return usersDto;
    }

}
