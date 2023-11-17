package com.LikeCloud.LikeCloud.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserLoginResponseDto {
    private Integer userId;

    public UserLoginResponseDto(Integer userId) {
        this.userId = userId;
    }
}
