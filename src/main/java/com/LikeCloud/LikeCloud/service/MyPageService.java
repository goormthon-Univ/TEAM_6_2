package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.domain.type.Day;
import com.LikeCloud.LikeCloud.dto.*;

import java.util.List;

public interface MyPageService {

    DoingPlanResponseDto getPlans(Long userId);
    DonePlanResponseDto getDonePlans(Long userId);
    void deletePlan(Long planId);
    List<DailyPlanResponseDto> getDailyPlansByPlanId(Long userId, String planType, Long planId);
    void updateDailyPlansByPlanId(Long userId, Long planId, String planType, UpdateDailyPlanRequestDto updateDailyPlanRequestDto);
    Day findDay(Integer day);
}