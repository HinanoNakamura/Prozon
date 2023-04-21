package com.example.backend.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.domain.Users;
import com.example.backend.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void register(Users user) {
        System.out.println("サービスusersの中身"+user.toString());
        userRepository.save(user);
    }

}

