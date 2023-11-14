package com.LikeCloud.LikeCloud.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DonePlanResponseDto {
    private List<YearPlanResponseDto> yearPlans = new ArrayList<>();
    private List<ShortPlanResponseDto> shortPlans = new ArrayList<>();

    public DonePlanResponseDto(List<YearPlanResponseDto> yearPlans, List<ShortPlanResponseDto> shortPlans) {
        this.yearPlans = yearPlans;
        this.shortPlans = shortPlans;
    }
}
