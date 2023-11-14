package com.LikeCloud.LikeCloud.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Day {
    MONDAY(1, "MONDAY"),
    TUSEDAY(2, "TUSEDAY"),
    WEDNESDAY(3, "WEDNESDAY"),
    THURSDAY(4, "THURSDAY"),
    FRIDAY(5, "FRIDAY"),
    SATURDAY(6, "SATURDAY"),
    SUNDAY(7, "SUNDAY");

    private final Integer num;
    private final String day;
}
