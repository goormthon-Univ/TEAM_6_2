package com.LikeCloud.LikeCloud.dto;

import com.LikeCloud.LikeCloud.domain.entity.MonthlyPlan;
import com.LikeCloud.LikeCloud.domain.entity.User;
import com.LikeCloud.LikeCloud.domain.entity.YearPlan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class MonthlyPlanRequestDto {
    private Integer year;
    private Integer month;
    private String monthlyPlan;

    public MonthlyPlan toEntity(User user, YearPlan yearPlan, Integer year, Integer month, String plan) {
        return MonthlyPlan.builder()
                .user(user)
                .yearPlan(yearPlan)
                .year(year)
                .month(month)
                .plan(plan)
                .build();
    }
}
