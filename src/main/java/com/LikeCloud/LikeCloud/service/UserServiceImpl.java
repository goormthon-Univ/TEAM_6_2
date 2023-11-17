package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.domain.entity.User;
import com.LikeCloud.LikeCloud.dto.SignUpRequestDto;
import com.LikeCloud.LikeCloud.dto.UserLoginRequestDto;
import com.LikeCloud.LikeCloud.dto.UserLoginResponseDto;
//import com.LikeCloud.LikeCloud.dto.UserNickNameResponseDto;
import com.LikeCloud.LikeCloud.dto.UserNickNameResponseDto;
import com.LikeCloud.LikeCloud.repository.UserRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    public void saveUser(SignUpRequestDto signUpRequestDto) {
        try {
            User user = signUpRequestDto.toEntity(signUpRequestDto.getNickname(), signUpRequestDto.getPassword());
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("회원가입에 실패했습니다");
        }
    }

    public UserLoginResponseDto findUser(UserLoginRequestDto userLoginRequestDto) {
        try {
            User user = userRepository.findByNickName(userLoginRequestDto.getNickname())
                    .orElseThrow(() -> new RuntimeException("닉네임이 존재하지 않습니다."));

            return new UserLoginResponseDto(Long.valueOf(user.getId()).intValue());
        } catch (Exception e) {
            throw new RuntimeException("로그인에 실패했습니다");
        }
    }

    public UserNickNameResponseDto findByUserNickname(String Nickname) {
        try {
            Boolean exists;
            List<User> users = userRepository.findByUserNickName(Nickname);
            if (users.size() > 0) {
                exists = true;
            } else {
                exists = false;
            }
            return new UserNickNameResponseDto(exists);
        } catch (Exception e) {
            throw new RuntimeException("닉네임 사용이 불가능 합니다.");
        }
    }
}
