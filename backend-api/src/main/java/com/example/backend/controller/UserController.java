package com.example.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.domain.Users;
import com.example.backend.service.UserService;

@RestController
@CrossOrigin
class UserController {

    @Autowired
    UserService userService;
    
    @PostMapping(path="/api/users")
    public ResponseEntity<?> registerUser(@RequestBody Users users) {
       
        System.out.println(users);
        try {
            userService.register(users);
        
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        } catch (Exception e) {
           
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}