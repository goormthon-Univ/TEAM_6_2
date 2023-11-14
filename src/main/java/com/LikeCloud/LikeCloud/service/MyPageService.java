package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.dto.DoingPlanResponseDto;

import java.util.List;

public interface MyPageService {

    DoingPlanResponseDto getPlans(Long userId);
}



