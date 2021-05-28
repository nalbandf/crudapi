package com.galvanize.crudapi.controller;

import com.galvanize.crudapi.domain.User;
import com.galvanize.crudapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> getUsers(){

       return userRepository.findAll();

    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){

        User createdUser = userRepository.save(user);

        return createdUser;


    }

}
