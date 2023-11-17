package com.LikeCloud.LikeCloud.dto;

import com.LikeCloud.LikeCloud.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SignUpRequestDto {
    private String nickname;
    private String password;

    public User toEntity(String nickname, String password) {
        return User.builder()
                .nickName(nickname)
                .password(password)
                .profileImage(null)
                .status(1)
                .build();
    }
}
