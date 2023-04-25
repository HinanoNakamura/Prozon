package com.example.backend.service;

import java.util.Optional;

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
        public boolean register(Users user) {
            System.out.println("サービスusersの中身" + user.toString());
            Optional<Users> existingUser = userRepository.findById(user.getId());

            if (existingUser.isPresent()) {
                System.out.println("たかお定食: " + existingUser.get());
            }else{
                System.out.println("炙り中トロ");
            }

            if (existingUser.isPresent()) {
                return false;
            } else {
                userRepository.save(user);
                return true;
            }
        }

        public Boolean loginCheck(Users request){

            Optional<Users> optionalUserLogin = userRepository.findById(request.getId());
        
            boolean result = false;
        
            if (optionalUserLogin.isPresent() && request.getPassword().equals(optionalUserLogin.get().getPassword())) {
                result = true;
            }
            return result;
            }
}