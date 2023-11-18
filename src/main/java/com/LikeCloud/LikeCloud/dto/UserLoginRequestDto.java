package com.LikeCloud.LikeCloud.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginRequestDto {

    private String nickname;
    private String password;
}
