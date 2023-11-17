package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.domain.entity.*;
import com.LikeCloud.LikeCloud.domain.type.Day;
import com.LikeCloud.LikeCloud.dto.DailyPlanRequestDto;
import com.LikeCloud.LikeCloud.dto.ShortPlanRequestDto;
import com.LikeCloud.LikeCloud.repository.DailyPlanRepository;
import com.LikeCloud.LikeCloud.repository.ShortPlanRepository;
import com.LikeCloud.LikeCloud.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ShortPlanServiceImpl implements ShortPlanService {

    @Autowired
    ShortPlanRepository shortPlanRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DailyPlanRepository dailyPlanReposioty;

    public Day findDay(Integer day) {
        Day day1 = Arrays.stream(Day.values())
                .filter(c -> c.getNum() == day)
                .findAny().get();
        return day1;
    }
    @Override
    public void save(Integer userId, ShortPlanRequestDto sp) {
        try {
            // UserRepository를 이용하여 User 정보를 불러옴
            User user = userRepository.findById(Long.valueOf(userId))
                    .orElseThrow(() -> new RuntimeException("Not Found User"));

            ShortPlan shortPlan = sp.toEntity(user, sp.getYear(), sp.getShortPlan(), sp.getPeriod());
            shortPlanRepository.save(shortPlan);

            // Save daily plans
            List<DailyPlan> dailyPlans = sp.getDailyPlan().stream()
                    .map(dp -> dp.toEntity(user, null, shortPlan, findDay(dp.getDay()), dp.getPlan()))
                    .collect(Collectors.toList());

            dailyPlanReposioty.saveAll(dailyPlans);

        } catch (Exception e) {
            log.error("ShortPlan 저장 중 오류 발생: {}", e.getMessage());
            // 예외 처리 필요한 경우 처리
        }
    }


}
