package com.example.springbootrentalcar.mapper;

import com.example.springbootrentalcar.dto.UserDto;
import com.example.springbootrentalcar.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Component
public class UserMapper {

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public User convertToUser(UserDto userDto) {
        User user = null;
        if (userDto != null) {
            user =  modelMapper.map(userDto, User.class);
            if(userDto.getPassword()!=null) {
                user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            }
        }
        return user;
    }


    public UserDto convertToDto(User user) {
        UserDto userDto = null;
        if (user != null) {
            userDto =  modelMapper.map(user, UserDto.class);
        }
        return userDto;
    }

    public UserDto dtoToDto4Modify(UserDto userDtoToModify) {
        UserDto userDto = null;
        if (userDtoToModify != null) {
            userDto =  modelMapper.map(userDtoToModify, UserDto.class);
        }
        return userDto;
    }



    public List<UserDto> convertToDtoList(List<User> usersList) {
        List<UserDto> usersDto = new ArrayList<>();
        if (usersList != null) {
            usersDto = usersList
                    .stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }
        return usersDto;
    }

}
