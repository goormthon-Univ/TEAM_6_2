package com.LikeCloud.LikeCloud.dto;

import com.LikeCloud.LikeCloud.domain.entity.ShortPlan;
import com.LikeCloud.LikeCloud.domain.entity.User;
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

    public ShortPlan toEntity(User user, Integer year, String shortPlan, Integer period) {
        return ShortPlan.builder()
                .user(user)
                .year(year)
                .shortPlan(shortPlan)
                .waterDrop(0)
                .steam(0)
                .miniCloud(0)
                .period(period)
                .done(false)
                .dailyFix(true)
                .build();
    }
}
