package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.dto.DoingPlanResponseDto;
import com.LikeCloud.LikeCloud.dto.DonePlanResponseDto;

import java.util.List;

public interface MyPageService {

    DoingPlanResponseDto getPlans(Long userId);
    DonePlanResponseDto getDonePlans(Long userId);
}



