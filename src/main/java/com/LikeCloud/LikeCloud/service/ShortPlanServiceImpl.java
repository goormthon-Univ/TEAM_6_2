package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.domain.entity.*;
import com.LikeCloud.LikeCloud.domain.type.Day;
import com.LikeCloud.LikeCloud.dto.DailyPlanRequestDto;
import com.LikeCloud.LikeCloud.dto.ShortPlanRequestDto;
import com.LikeCloud.LikeCloud.repository.DailyPlanReposioty;
import com.LikeCloud.LikeCloud.repository.ShortPlanRepository;
import com.LikeCloud.LikeCloud.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ShortPlanServiceImpl implements ShortPlanService {

    @Autowired
    ShortPlanRepository shortPlanRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DailyPlanReposioty dailyPlanReposioty;

    public Day findDay(int day) {
        Day day1 = Arrays.stream(Day.values())
                .filter(c -> c.getNum() == day)
                .findAny().get();
        return day1;
    }

    @Override
    public void save(ShortPlanRequestDto shortPlanRequestDto) {
        try {
            // UserRepository를 이용하여 User 정보를 불러옴
            User user = userRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("User를 찾을 수 없습니다."));

            System.out.println(shortPlanRequestDto.getDailyPlan());
            // YearPlan 객체 생성 및 값 설정
            ShortPlan shortPlan = ShortPlan.builder()
                    .user(user)
                    .shortPlan(shortPlanRequestDto.getShortPlan())
                    .period(shortPlanRequestDto.getPeriod())
                    .dailyPlans(new ArrayList<>())
                    .waterDrop(0)
                    .steam(0)
                    .miniCloud(0)
                    .year(shortPlanRequestDto.getYear())
                    .dailyFix(true)
                    .done(false)
                    .dailyPlans(new ArrayList<>())
                    .build();

            if (shortPlan.getId() == null) {
                ShortPlan savedShortPlan = shortPlanRepository.save(shortPlan);
                savedShortPlan.setUser(user);

                // Save daily plans
                List<DailyPlanRequestDto> dailyPlanRequestDto = shortPlanRequestDto.getDailyPlan();
                if (dailyPlanRequestDto != null) {
                    for (DailyPlanRequestDto dailyPlanDto : dailyPlanRequestDto) {
                        DailyPlan dailyPlan = DailyPlan.builder()
                                .user(user)
                                .shortPlan(savedShortPlan)
                                .day(findDay(dailyPlanDto.getDay()))  // 수정된 부분
                                .plan(dailyPlanDto.getPlan())
                                .done(false)
                                .exception(false)
                                .build();
                        savedShortPlan.getDailyPlans().add(dailyPlan);
                    }
                }
            }
        } catch (Exception e) {
            log.error("ShortPlan 저장 중 오류 발생: {}", e.getMessage());
            // 예외 처리 필요한 경우 처리
        }
    }
}
