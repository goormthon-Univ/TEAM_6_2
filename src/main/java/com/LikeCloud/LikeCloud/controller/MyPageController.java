package com.LikeCloud.LikeCloud.controller;

import com.LikeCloud.LikeCloud.domain.entity.DailyPlan;
import com.LikeCloud.LikeCloud.domain.entity.User;
import com.LikeCloud.LikeCloud.dto.*;
import com.LikeCloud.LikeCloud.repository.UserRepository;
import com.LikeCloud.LikeCloud.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    private final UserRepository userRepository;

    @GetMapping("/DoingPlan")
    public ResponseEntity<?> getDoingPlan() {
        try {
            // UserRepository를 사용하여 실제로 존재하는 유저를 조회
            User user = userRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("해당 아이디의 유저를 찾을 수 없습니다."));

            DoingPlanResponseDto responseDto = myPageService.getPlans(user.getId()); // 수정된 부분
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("내부 서버 오류: " + e.getMessage());
        }
    }

    @DeleteMapping("/DoingPlan")
    public ResponseEntity<?> deleteDoingPlan(@RequestBody DeletePlanRequestDto request) {
        try {
            // UserRepository를 사용하여 실제로 존재하는 유저를 조회
            User user = userRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("해당 아이디의 유저를 찾을 수 없습니다."));

            // 받은 요청에 따라 적절한 목표 삭제
            if (request.getYearPlanId() != null) {
                myPageService.deletePlan(request.getYearPlanId());
            } else if (request.getShortPlanId() != null) {
                myPageService.deletePlan(request.getShortPlanId());
            } else {
                // 예외: yearPlanId 또는 shortPlanId 중 하나는 반드시 제공되어야 함
                throw new IllegalArgumentException("yearPlanId 또는 shortPlanId는 반드시 제공되어야 합니다.");
            }

            return ResponseEntity.ok("목표가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("내부 서버 오류: " + e.getMessage());
        }
    }

    @GetMapping("/DonePlan")
    public ResponseEntity<?> getDonePlan() {
        try {
            // UserRepository를 사용하여 실제로 존재하는 유저를 조회
            User user = userRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("해당 아이디의 유저를 찾을 수 없습니다."));

            DonePlanResponseDto donePlanResponseDto = myPageService.getDonePlans(user.getId());
            return ResponseEntity.ok(donePlanResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal sever error: " + e.getMessage());
        }
    }

    @GetMapping("/DailyPlan/{planType}/{planId}")
    public ResponseEntity<?> getDailyPlan(@PathVariable String planType, @PathVariable Long planId) {
        try {
            User user = userRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("해당 아이디의 유저를 찾을 수 없습니다."));

            if (planType.equals("yearPlan") || planType.equals("shortPlan")) {
                List<DailyPlanResponseDto> dailyPlans = myPageService.getDailyPlansByPlanId(user.getId(), planType, planId);
                Map<String, List<DailyPlanResponseDto>> response = new HashMap<>();
                response.put("dailyPlans", dailyPlans);

                return ResponseEntity.ok(response);
            } else  {
                throw new RuntimeException("not good planType");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal sever error: " + e.getMessage());
        }
    }

    @PutMapping("/DailyPlan/{planType}/{planId}")
    public ResponseEntity<?> updateDailyPlan(@PathVariable String planType, @PathVariable Long planId,
                                             @RequestBody UpdateDailyPlanRequestDto requestDto) {
        try {
            User user = userRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("해당 아이디의 유저를 찾을 수 없습니다."));

            // planType에 따라 다른 메서드 호출
            if ("yearPlan".equals(planType) || "shortPlan".equals(planType)) {
                myPageService.updateDailyPlansByPlanId(1L, planId, planType, requestDto);
            } else {
                throw new RuntimeException("Not Good planType");
            }

            return ResponseEntity.status(HttpStatus.OK).body("Done");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
        }
    }
}