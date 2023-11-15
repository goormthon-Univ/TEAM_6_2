package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.dto.DailyPlanResponseDto;
import com.LikeCloud.LikeCloud.dto.DoingPlanResponseDto;
import com.LikeCloud.LikeCloud.dto.DonePlanResponseDto;

import java.util.List;

public interface MyPageService {

    DoingPlanResponseDto getPlans(Long userId);
    DonePlanResponseDto getDonePlans(Long userId);
    void deletePlan(Long planId);
    List<DailyPlanResponseDto> getDailyPlansByPlanId(Long userId, Long planId);
}