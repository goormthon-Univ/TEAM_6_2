package com.LikeCloud.LikeCloud.dto;

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
}
