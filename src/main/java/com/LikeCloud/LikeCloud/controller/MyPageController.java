package com.LikeCloud.LikeCloud.controller;

import com.LikeCloud.LikeCloud.domain.entity.User;
import com.LikeCloud.LikeCloud.dto.DoingPlanResponseDto;
import com.LikeCloud.LikeCloud.dto.DonePlanResponseDto;
import com.LikeCloud.LikeCloud.repository.UserRepository;
import com.LikeCloud.LikeCloud.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/DonePlan")
    public ResponseEntity<?> getDonePlan() {
        try {
            // UserRepository를 사용하여 실제로 존재하는 유저를 조회
            User user = userRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("해당 아이디의 유저를 찾을 수 없습니다."));

            DonePlanResponseDto donePlanResponseDto = myPageService.getDonePlans(user.getId());
            return ResponseEntity.ok(donePlanResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("내부 서버 오류: " + e.getMessage());
        }
    }
}