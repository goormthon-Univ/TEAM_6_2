package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.dto.SignUpRequestDto;
import com.LikeCloud.LikeCloud.dto.UserLoginRequestDto;
import com.LikeCloud.LikeCloud.dto.UserLoginResponseDto;
//import com.LikeCloud.LikeCloud.dto.UserNickNameResponseDto;

public interface UserService {
    void saveUser(SignUpRequestDto signUpRequestDto);
    UserLoginResponseDto findUser(UserLoginRequestDto userLoginRequestDto);
//    UserNickNameResponseDto findByUserNickname(String Nickname);
}
