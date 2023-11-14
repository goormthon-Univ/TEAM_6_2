package com.LikeCloud.LikeCloud.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CloudType {
    MINICLOUD(1,"MINICLOUD"),
    BIGCLOUD(2,"BIGCLOUD");

    private final Integer num;
    private final String CloudType;
}
