package com.LikeCloud.LikeCloud.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserNickNameResponseDto {
    private Boolean exists;

    public UserNickNameResponseDto(Boolean exists) {
        this.exists = exists;
    }
}
