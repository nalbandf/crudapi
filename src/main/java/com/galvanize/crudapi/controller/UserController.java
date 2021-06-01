package com.galvanize.crudapi.controller;

import com.galvanize.crudapi.domain.Authentication;
import com.galvanize.crudapi.domain.Count;
import com.galvanize.crudapi.domain.User;
import com.galvanize.crudapi.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/users/authenticate")
    public ResponseEntity<Authentication> authenticateUser(@RequestBody User user){

       User userByEmail = userRepository.findByEmail(user.getEmail());
       if(userByEmail.getPassword().equals(user.getPassword())){
           Authentication auth = new Authentication();
           auth.setAuthenticated(true);
           auth.setUser(userByEmail);
           return new ResponseEntity(auth, HttpStatus.OK);
       }
       else {
           Authentication auth1 = new Authentication();
           auth1.setAuthenticated(false);
           return new ResponseEntity(auth1, HttpStatus.OK);
       }

    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserByID(@PathVariable ("id") Long id){

        Optional<User> userByID = userRepository.findById(id);

        return userByID;

    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUserByID(@RequestBody User user, @PathVariable ("id") Long id){

        Optional<User> userByID = userRepository.findById(id);
        if(userByID.isPresent()){

            userByID.get().setEmail(user.getEmail());
           if(user.getPassword()!=null){
                userByID.get().setPassword(user.getPassword());
            }
           userRepository.save(userByID.get());
           return new ResponseEntity(userByID, HttpStatus.OK);

        }
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Count> deleteUser(@PathVariable ("id") Long id){

        Optional<User> userByID = userRepository.findById(id);
        if(userByID.isPresent()){
            userRepository.delete(userByID.get());
            Count cnt = new Count();
            cnt.setCount(userRepository.count());
            return new ResponseEntity(cnt, HttpStatus.OK);
        }
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
