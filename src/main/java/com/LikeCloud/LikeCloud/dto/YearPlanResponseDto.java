package com.LikeCloud.LikeCloud.dto;

import com.LikeCloud.LikeCloud.domain.entity.YearPlan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class YearPlanResponseDto {
    private Long yearPlanId;
    private String yearPlan;
    private Integer miniCloud;
    private Integer yearPlanSteam;
    private boolean yearPlanDone;

    public Integer getMiniCloud() {
        // 만약 getMiniCloud()가 null을 반환하면 기본값 0을 반환하도록 수정
        return miniCloud != null ? miniCloud : 0;
    }

    public YearPlanResponseDto(YearPlan yearPlan) {
        this.yearPlanId = yearPlan.getId();
        this.yearPlan = yearPlan.getYearPlan();
        this.yearPlanSteam = yearPlan.getMiniCloud() * 4;
        this.yearPlanDone = yearPlan.getDone();
    }
}
