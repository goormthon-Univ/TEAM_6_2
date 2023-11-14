package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.domain.entity.DailyPlan;
import com.LikeCloud.LikeCloud.domain.entity.MonthlyPlan;
import com.LikeCloud.LikeCloud.domain.entity.User;
import com.LikeCloud.LikeCloud.domain.entity.YearPlan;
import com.LikeCloud.LikeCloud.domain.type.Day;
import com.LikeCloud.LikeCloud.dto.DailyPlanRequestDto;
import com.LikeCloud.LikeCloud.dto.MonthlyPlanRequestDto;
import com.LikeCloud.LikeCloud.dto.YearPlanRequestDto;
import com.LikeCloud.LikeCloud.repository.DailyPlanReposioty;
import com.LikeCloud.LikeCloud.repository.UserRepository;
import com.LikeCloud.LikeCloud.repository.YearPlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class YearPlanServiceImpl implements YearPlanService {

    @Autowired
    YearPlanRepository yearPlanRepository;

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
    public void save(YearPlanRequestDto yearPlanRequestDto) {
        try {
            // UserRepository를 이용하여 User 정보를 불러옴
            User user = userRepository.findById(1)
                    .orElseThrow(() -> new RuntimeException("User를 찾을 수 없습니다."));

            // YearPlan 객체 생성 및 값 설정
            YearPlan yearPlan = YearPlan.builder()
                    .user(user)
                    .yearPlan(yearPlanRequestDto.getYearPlan())
                    .halfPlan(yearPlanRequestDto.getHalfPlan())
                    .waterDrop(0)
                    .steam(0)
                    .miniCloud(0)
                    .bigCloud(0)
                    .year(yearPlanRequestDto.getYear())
                    .dailyFix(false)
                    .done(false)
                    .monthlyPlans(new ArrayList<>())
                    .dailyPlans(new ArrayList<>())
                    .build();

            if (yearPlan.getId() == null) {
                YearPlan savedYearPlan = yearPlanRepository.save(yearPlan);
                savedYearPlan.setUser(user);

                if (yearPlanRequestDto.getHalfPlan() != null) {
                    savedYearPlan.setHalfPlan(yearPlanRequestDto.getHalfPlan());
                }

                // Save monthly plans
                List<MonthlyPlanRequestDto> monthlyPlanRequestDtos = yearPlanRequestDto.getMonthlyPlan();
                if (monthlyPlanRequestDtos != null) {
                    for (MonthlyPlanRequestDto monthlyPlanDto : monthlyPlanRequestDtos) {
                        MonthlyPlan monthlyPlan = MonthlyPlan.builder()
                                .user(user)
                                .yearPlan(savedYearPlan)
                                .year(monthlyPlanDto.getYear())
                                .month(monthlyPlanDto.getMonth())
                                .plan(monthlyPlanDto.getMonthlyPlan())
                                .build();
                        savedYearPlan.getMonthlyPlans().add(monthlyPlan);
                    }
                }

                // Save daily plans
                List<DailyPlanRequestDto> dailyPlanRequestDto = yearPlanRequestDto.getDailyPlan();
                if (dailyPlanRequestDto != null) {
                    for (DailyPlanRequestDto dailyPlanDto : dailyPlanRequestDto) {
                        DailyPlan dailyPlan = DailyPlan.builder()
                                .user(user)
                                .yearPlan(savedYearPlan)
                                .day(findDay(dailyPlanDto.getDay()))  // 수정된 부분
                                .plan(dailyPlanDto.getPlan())
                                .done(false)
                                .exception(false)
                                .build();
                        savedYearPlan.getDailyPlans().add(dailyPlan);
                    }
                }
            }
        } catch (Exception e) {
            log.error("YearPlan 저장 중 오류 발생: {}", e.getMessage());
            // 예외 처리 필요한 경우 처리
        }
    }
}
