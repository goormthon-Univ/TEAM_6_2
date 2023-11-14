package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.domain.entity.User;
import com.LikeCloud.LikeCloud.dto.DoingPlanResponseDto;
import com.LikeCloud.LikeCloud.dto.DonePlanResponseDto;
import com.LikeCloud.LikeCloud.dto.ShortPlanResponseDto;
import com.LikeCloud.LikeCloud.dto.YearPlanResponseDto;
import com.LikeCloud.LikeCloud.repository.ShortPlanRepository;
import com.LikeCloud.LikeCloud.repository.UserRepository;
import com.LikeCloud.LikeCloud.repository.YearPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MyPageServiceImpl implements MyPageService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    YearPlanRepository yearPlanRepository;

    @Autowired
    ShortPlanRepository shortPlanRepository;

    @Override
    public DoingPlanResponseDto getPlans(Long userId) {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("User를 찾을 수 없습니다."));

        List<YearPlanResponseDto> yearPlans = yearPlanRepository.findByUserId(userId)
                .stream()
                .filter(yearPlan -> !yearPlan.getDone())
                .map(YearPlanResponseDto::new)
                .collect(Collectors.toList());

        List<ShortPlanResponseDto> shortPlans = shortPlanRepository.findByUserId(userId)
                .stream()
                .filter(shortPlan -> !shortPlan.getDone())
                .map(ShortPlanResponseDto::new)
                .collect(Collectors.toList());

        return new DoingPlanResponseDto(yearPlans, shortPlans);
    }

    @Override
    public DonePlanResponseDto getDonePlans(Long userId) {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("User를 찾을 수 없습니다."));

        List<YearPlanResponseDto> yearPlans = yearPlanRepository.findByUserId(userId)
                .stream()
                .filter(yearPlan -> yearPlan.getDone())
                .map(YearPlanResponseDto::new)
                .collect(Collectors.toList());

        List<ShortPlanResponseDto> shortPlans = shortPlanRepository.findByUserId(userId)
                .stream()
                .filter(shortPlan -> shortPlan.getDone())
                .map(ShortPlanResponseDto::new)
                .collect(Collectors.toList());

        return new DonePlanResponseDto(yearPlans, shortPlans);
    }
}
