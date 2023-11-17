package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.dto.ShortPlanRequestDto;

public interface ShortPlanService {
    void save(Integer userId, ShortPlanRequestDto shortPlanRequestDto);
}
