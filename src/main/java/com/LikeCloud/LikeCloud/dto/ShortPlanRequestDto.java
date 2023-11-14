package com.LikeCloud.LikeCloud.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShortPlanRequestDto {
    private Long userId;
    private Integer year;
    private String shortPlan;
    private Integer period;
    private List<DailyPlanRequestDto> dailyPlan;

    public ShortPlanRequestDto(Long userId, Integer year, String shortPlan, Integer period, List<DailyPlanRequestDto> dailyPlan) {
        this.userId = userId;
        this.year = year;
        this.shortPlan = shortPlan;
        this.period = period;
        this.dailyPlan = dailyPlan;
    }
}
