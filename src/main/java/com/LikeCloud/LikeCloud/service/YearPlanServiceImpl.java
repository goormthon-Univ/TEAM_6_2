package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.domain.entity.DailyPlan;
import com.LikeCloud.LikeCloud.domain.entity.MonthlyPlan;
import com.LikeCloud.LikeCloud.domain.entity.User;
import com.LikeCloud.LikeCloud.domain.entity.YearPlan;
import com.LikeCloud.LikeCloud.domain.type.Day;
import com.LikeCloud.LikeCloud.dto.DailyPlanRequestDto;
import com.LikeCloud.LikeCloud.dto.MonthlyPlanRequestDto;
import com.LikeCloud.LikeCloud.dto.YearPlanRequestDto;
import com.LikeCloud.LikeCloud.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Long.valueOf;
import static javax.management.Query.value;

@Slf4j
@Service
@Transactional
public class YearPlanServiceImpl implements YearPlanService {

    @Autowired
    YearPlanRepository yearPlanRepository;

    @Autowired
    MonthlyPlanRepository monthlyPlanRepository;

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
    public void save(YearPlanRequestDto yp) {
        try {
            // UserRepository를 이용하여 User 정보를 불러옴
            User user = userRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("User를 찾을 수 없습니다."));

            // Save year plans and half plan
            YearPlan yearPlan = yp.toEntity(user, yp.getYear(), yp.getYearPlan(), yp.getHalfPlan());
            YearPlan savedYearPlan = yearPlanRepository.save(yearPlan);
            savedYearPlan.setUser(user);

            // Save monthly plans
            List<MonthlyPlan> monthlyPlans = yp.getMonthlyPlan().stream()
                    .map(mp -> mp.toEntity(user, yearPlan, yp.getYear(), mp.getMonth(), mp.getMonthlyPlan()))
                    .collect(Collectors.toList());
            monthlyPlanRepository.saveAll(monthlyPlans);

            // Save daily plans
            System.out.println(yp.getDailyPlan());
            List<DailyPlan> dailyPlans = yp.getDailyPlan().stream()
                    .map(dp -> dp.toEntity(user, yearPlan, null, findDay(dp.getDay()), dp.getPlan()))
                    .collect(Collectors.toList());
            System.out.println(dailyPlans.size());
            dailyPlanReposioty.saveAll(dailyPlans);

        } catch (Exception e) {
            log.error("YearPlan 저장 중 오류 발생: {}", e.getMessage());
            // 예외 처리 필요한 경우 처리
        }
    }
}
