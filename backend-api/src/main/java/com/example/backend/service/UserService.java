package com.example.backend.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.domain.Change;
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

        public ResponseEntity<?> changes(Change change){
        Optional<Users> user = userRepository.findById(change.getUserid());
        Users baseuser = user.get();

        if (!user.isPresent() || !change.getPassword().equals(baseuser.getPassword())) {
        // ユーザーが存在しない場合はエラーを返す
        System.out.print(change.getPassword());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    // パスワードを変更する処理を実装する
        baseuser.setPassword(change.getNewpassword());
        userRepository.save(baseuser);

    return new ResponseEntity<>(HttpStatus.OK);
    }

    public Users gethome(String user){
        return userRepository.findById(user).get();
    }

    public Users changemail(String user){
        return userRepository.findById(user).get();
    }

    public Boolean deleteUsers(String id) {
        Optional<Users> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return true; // 削除成功
        } else {
            return false; // 削除失敗
        }
    }

    public List<Users> getUsers(String id) {
        Optional<Users> optionalUsers  = userRepository.findById(id);
        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            List<Users> userList = Arrays.asList(users);
            return userList;

        } else {
            return null;
        }
        
    }

}