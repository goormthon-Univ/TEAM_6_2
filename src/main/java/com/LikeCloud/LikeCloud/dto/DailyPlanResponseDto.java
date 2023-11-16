package com.LikeCloud.LikeCloud.dto;

import com.LikeCloud.LikeCloud.domain.type.Day;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DailyPlanResponseDto {
    private Day day;
    private String plan;

    public DailyPlanResponseDto(Day day, String plan) {
        this.day = day;
        this.plan = plan;
    }
}
