package com.LikeCloud.LikeCloud.controller;

import com.LikeCloud.LikeCloud.dto.SignUpRequestDto;
import com.LikeCloud.LikeCloud.dto.UserLoginRequestDto;
import com.LikeCloud.LikeCloud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/auth/signup")
    public ResponseEntity<?> saveUser(@RequestBody SignUpRequestDto signUpRequestDto) {
        userService.saveUser(signUpRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequestDto userLoginRequestDto) {;
        return ResponseEntity.ok(userService.findUser(userLoginRequestDto));
    }
}
