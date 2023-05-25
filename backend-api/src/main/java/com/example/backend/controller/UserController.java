package com.example.backend.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.domain.Change;

import com.example.backend.domain.Users;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;

@RestController
@CrossOrigin
class UserController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;


    @PostMapping(path="/api/users")//
    public ResponseEntity<?> registerUser(@RequestPart(name = "icon", required = false)MultipartFile file,@RequestParam("id") String id,@RequestParam("password") String password,
    @RequestParam("mail") String mail,@RequestParam("homenumber") String homenumber,@RequestParam("homeaddress") String homeaddress) {
        Users users = new Users();
        users.setId(id);
        users.setPassword(password);
        users.setMail(mail);
        users.setHomenumber(homenumber);
        users.setHomeaddress(homeaddress);

        if (file == null || file.isEmpty()) {
            users.setIcon("/assets/profileimg/w.jpg");
        } else {
            // 通常の処理でアイコンを保存する
            LocalDateTime nowDate = LocalDateTime.now();
            DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String formatNowDate = dtf3.format(nowDate);
            String name = formatNowDate + ".png";
            users.setIcon("/assets/profileimg/" + name);
    
            // アイコンを保存する処理
            try {
                Path path = Paths.get("/Users/hinano_nakamura/Desktop/perfect/frontend/public/assets/profileimg/" + name);
                Files.write(path, file.getBytes());
            } catch (IOException e) {
                // アイコン保存エラーの場合の処理
                logger.error("Error saving icon: {}", e);
                return new ResponseEntity<>("Error saving icon", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    
        try {
            boolean isSuccess = userService.register(users);
            if (isSuccess) {
                logger.info("User registered successfully");
                return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
            } else {
                logger.error("This ID is already registered: {}", users);
                return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("Error registering user: {}", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/api/updatepassword")
    public ResponseEntity<?> registerUser(@RequestBody Change change) {
        logger.error("このIDはもう");
        return userService.changes(change);

    }

    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(@RequestBody Users request) {

        System.out.println("ログインしようとしてるですな" + request);
        Boolean result = userService.loginCheck(request);
        System.out.println("ですなですな" + result);
        if (result) {
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("ログインに失敗しました。");
        }
    }

    @PostMapping("/api/updatehomeadd")
    public ResponseEntity<String> updateHomeAddress(@RequestBody Users user) {
        System.out.println(user.getHomeaddress()+user.getHomenumber());
        Optional<Users> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()) {
            Users existingUser = userOptional.get();

            existingUser.setHomeaddress(user.getHomeaddress());
            existingUser.setHomenumber(user.getHomenumber());
            userRepository.save(existingUser);
            return ResponseEntity.ok("yahoo");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/homeaddress/{userId}")
    public Users getHomeAddress(@PathVariable ("userId") String id) {
        System.out.println("ああああ");
        return userService.gethome(id);
    }

    @GetMapping("/api/mailaddress/{userId}")
    public Users changemailAddress(@PathVariable ("userId") String id) {
        System.out.println("いいいいい");
        return userService.changemail(id);
    }

    @PostMapping("/api/updatemailadd")
    public ResponseEntity<Users> updatemailAddress(@RequestBody Users user) {
        Optional<Users> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()) {
            Users existingUser = userOptional.get();
            existingUser.setMail(user.getMail());
            Users updatedUser = userRepository.save(existingUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/changeicon")
    public ResponseEntity<Users> changeicon(
            @RequestParam("id") String id,
            @RequestParam("icon") MultipartFile profileImageFile
    ) throws IOException {
        System.out.println("ぼえa"+profileImageFile+"きた");
        Optional<Users> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            Users existingUser = userOptional.get();
            // 以下、画像の保存処理
            String fileName = profileImageFile.getOriginalFilename();

            LocalDateTime nowDate = LocalDateTime.now();
            DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String formatNowDate = dtf3.format(nowDate);
            Path dest = Paths.get("/Users/hinano_nakamura/Desktop/perfect/frontend/public/assets/profileimg/" + formatNowDate  + fileName);
            Files.write(dest, profileImageFile.getBytes(), StandardOpenOption.CREATE);
            
            String imagePath = "/assets/profileimg/" + formatNowDate + fileName;
            existingUser.setIcon(imagePath);
            Users updatedUser = userRepository.save(existingUser);
    
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }
    


    @GetMapping(path = "/deleteuser/{userId}")
    public boolean delete(@PathVariable("userId") String userId) {
        boolean boo = userService.deleteUsers(userId);
        return boo;
    }

    @GetMapping(path = "/getuser/{userId}")
    public List<Users>getimage(@PathVariable("userId") String id){
        return userService.getUsers(id);
    }
      
}