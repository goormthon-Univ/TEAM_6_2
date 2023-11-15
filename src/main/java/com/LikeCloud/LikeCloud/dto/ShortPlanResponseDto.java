package com.LikeCloud.LikeCloud.dto;

import com.LikeCloud.LikeCloud.domain.entity.ShortPlan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShortPlanResponseDto {
    private Long shortPlanId;
    private String shortPlan;
    private Integer period;
    private Integer miniCloud;
    private Integer steam;
    private boolean done;

    public Integer getMiniCloud() {
        // 만약 getMiniCloud()가 null을 반환하면 기본값 0을 반환하도록 수정
        return miniCloud != null ? miniCloud : 0;
    }

    public ShortPlanResponseDto(ShortPlan shortPlan) {
        this.shortPlanId = shortPlan.getId();
        this.shortPlan = shortPlan.getShortPlan();
        this.period = shortPlan.getPeriod();
        this.steam = shortPlan.getMiniCloud() * 4;
        this.done = shortPlan.getDone();
    }
}
