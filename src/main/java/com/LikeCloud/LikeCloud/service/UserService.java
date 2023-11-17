package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.dto.SignUpRequestDto;
import com.LikeCloud.LikeCloud.dto.UserLoginRequestDto;
import com.LikeCloud.LikeCloud.dto.UserLoginResponseDto;

public interface UserService {
    void saveUser(SignUpRequestDto signUpRequestDto);
    UserLoginResponseDto findUser(UserLoginRequestDto userLoginRequestDto);
}
