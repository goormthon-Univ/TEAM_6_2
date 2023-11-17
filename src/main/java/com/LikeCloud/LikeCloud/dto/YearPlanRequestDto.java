package com.LikeCloud.LikeCloud.dto;

import com.LikeCloud.LikeCloud.domain.entity.User;
import com.LikeCloud.LikeCloud.domain.entity.YearPlan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class YearPlanRequestDto {

    private Long userId;
    private Integer year;
    private String yearPlan;
    private String halfPlan;
    private List<MonthlyPlanRequestDto> monthlyPlan;
    private List<DailyPlanRequestDto> dailyPlan;

    public YearPlanRequestDto(Long userId, Integer year, String yearPlan, String halfPlan, List<MonthlyPlanRequestDto> monthlyPlan, List<DailyPlanRequestDto> dailyPlan) {
        this.userId = userId;
        this.year = year;
        this.yearPlan = yearPlan;
        this.halfPlan = halfPlan;
        this.monthlyPlan = monthlyPlan;
        this.dailyPlan = dailyPlan;
    }

    public YearPlan toEntity(User user, Integer year, String yearPlan, String halfPlan) {
        return YearPlan.builder()
                .user(user)
                .year(year)
                .yearPlan(yearPlan)
                .halfPlan(halfPlan)
                .waterDrop(0)
                .steam(0)
                .miniCloud(0)
                .bigCloud(0)
                .done(false)
                .dailyFix(true)
                .build();
    }
}
