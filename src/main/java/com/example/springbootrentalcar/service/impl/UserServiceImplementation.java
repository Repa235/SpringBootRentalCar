package com.example.springbootrentalcar.service.impl;

import com.example.springbootrentalcar.dto.RentDto;
import com.example.springbootrentalcar.dto.UserDto;
import com.example.springbootrentalcar.entity.User;
import com.example.springbootrentalcar.mapper.UserMapper;
import com.example.springbootrentalcar.repository.UserRepository;
import com.example.springbootrentalcar.service.UserService;
import com.example.springbootrentalcar.specifications.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        if (userDto.getId() == null) {
            userRepository.save(userMapper.convertToUser(userDto));
        } else {
            UserDto userToModify = this.getUserById(userDto.getId());
            if (userToModify != null) {
                userToModify.setName(userDto.getName());
                userToModify.setSurname(userDto.getSurname());
                userToModify.setBirthday(userDto.getBirthday());
                userToModify.setAdmin(userDto.isAdmin());
                userToModify.setUsername(userDto.getUsername());
                userToModify.setPassword(userDto.getPassword());
                userRepository.save(userMapper.convertToUser(userToModify));
            }
        }
    }

    @Override
    public void deleteUser(int id) {
        UserDto userToDelete = this.getUserById(id);
        if (userToDelete != null) {
            userRepository.deleteById(userToDelete.getId());
        }
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

    @Override
    public UserDto getUserByUsername(String username) {
        return userMapper.convertToDto(userRepository.getUserByUsername(username));
    }

}
