package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.domain.type.Day;
import com.LikeCloud.LikeCloud.dto.*;

import java.util.List;

public interface MyPageService {

    DoingPlanResponseDto getPlans(Integer userId);
    DonePlanResponseDto getDonePlans(Integer userId);
    void deletePlan(Long planId);
    List<DailyPlanResponseDto> getDailyPlansByPlanId(String planType, Long planId);

    void updateDailyPlansByPlanId(Integer userId, Long planId, String planType, UpdateDailyPlanRequestDto updateDailyPlanRequestDto);

    Day findDay(Integer day);
}