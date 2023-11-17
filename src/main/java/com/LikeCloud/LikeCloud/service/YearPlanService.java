package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.dto.YearPlanRequestDto;

public interface YearPlanService {
    void save(Integer userId, YearPlanRequestDto yearPlanRequestDto);
}
