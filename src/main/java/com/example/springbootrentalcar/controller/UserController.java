package com.example.springbootrentalcar.controller;


import com.example.springbootrentalcar.entity.User;
import com.example.springbootrentalcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getCustomers() {
        List<User> customersList = userService.getCustomers();
        if(customersList == null) {
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<User>>(customersList, HttpStatus.OK);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if(user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);
        if(user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
    }

    @GetMapping("/search/{filter}/{textToSearch}")
    public ResponseEntity<List<User>> getCustomerByParam(@PathVariable("filter") String filter,
                                                        @PathVariable("textToSearch") String textToSearch) {
        List<User> userFounded = userService.getCustomerByParam(filter,textToSearch);
        if(userFounded == null) {
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<User>>(userFounded, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/addOrUpdate", method = { RequestMethod.POST, RequestMethod.PUT })
    public void addOrUpdateUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
    }

    @DeleteMapping("/remove/{id}")
    public void removeUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }


}
