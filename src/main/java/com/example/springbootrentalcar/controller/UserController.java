package com.example.springbootrentalcar.controller;


import com.example.springbootrentalcar.dto.UserDto;
import com.example.springbootrentalcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getCustomers() {
        List<UserDto> customersList = userService.getCustomers();
        if(customersList == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(customersList, HttpStatus.OK);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") int id) {
        UserDto userDto = userService.getUserById(id);
        if(userDto == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(userDto, HttpStatus.OK);
        }
    }

    @GetMapping("/search/{filter}/{textToSearch}")
    public ResponseEntity<UserDto> getCustomerByParam(@PathVariable("filter") String filter,
                                                        @PathVariable("textToSearch") String textToSearch) {
        List<UserDto> userFound = userService.getCustomerByParam(filter,textToSearch);
        if(userFound == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(userFound, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/addOrUpdate", method = { RequestMethod.POST, RequestMethod.PUT })
    public void addOrUpdateUser(@Valid @RequestBody UserDto userDto) {
        userService.saveOrUpdateUser(userDto);
    }

    @DeleteMapping("/remove/{id}")
    public void removeUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }


}
