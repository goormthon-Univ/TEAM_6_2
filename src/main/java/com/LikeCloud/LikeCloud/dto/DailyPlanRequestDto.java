package com.LikeCloud.LikeCloud.dto;

import com.LikeCloud.LikeCloud.domain.entity.DailyPlan;
import com.LikeCloud.LikeCloud.domain.entity.ShortPlan;
import com.LikeCloud.LikeCloud.domain.entity.User;
import com.LikeCloud.LikeCloud.domain.entity.YearPlan;
import com.LikeCloud.LikeCloud.domain.type.Day;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DailyPlanRequestDto {
    private Integer day;
    private String plan;

    public DailyPlan toEntity(User user, YearPlan yearPlan, ShortPlan shortPlan, Day day, String plan) {
        return DailyPlan.builder()
                .user(user)
                .yearPlan(yearPlan)
                .shortPlan(shortPlan)
                .day(day)
                .plan(plan)
                .done(false)
                .exception(false)
                .build();
    }
}
