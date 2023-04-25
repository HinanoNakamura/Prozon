package com.example.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    UserService userService;
    
    @PostMapping(path="/api/users")
    public ResponseEntity<?> registerUser(@RequestBody Users users) {
        logger.debug("usersの中身ですな: {}", users);
        try {
            boolean isSuccess = userService.register(users);
            if (isSuccess) {
                logger.info("User registered successfully");
                return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
            } else {
                logger.error("このIDはもう登録されてるですな: {}", users);
                return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Error registering user: {}", "エラーですな"+e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Object>  login(@RequestBody Users request) {

        System.out.println("ログインしようとしてるですな"+request);
        Boolean result = userService.loginCheck(request);
        System.out.println("ですなですな"+result);
        if (result) {
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ログインに失敗しました。");
        }
    }

}