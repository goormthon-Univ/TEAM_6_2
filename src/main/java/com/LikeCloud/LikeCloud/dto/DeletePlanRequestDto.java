package com.LikeCloud.LikeCloud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletePlanRequestDto {
    private Long yearPlanId;
    private Long shortPlanId;
}
