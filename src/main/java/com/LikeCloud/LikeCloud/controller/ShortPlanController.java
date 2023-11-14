package com.LikeCloud.LikeCloud.controller;

import com.LikeCloud.LikeCloud.domain.entity.User;
import com.LikeCloud.LikeCloud.dto.ShortPlanRequestDto;
import com.LikeCloud.LikeCloud.dto.YearPlanRequestDto;
import com.LikeCloud.LikeCloud.repository.UserRepository;
import com.LikeCloud.LikeCloud.service.ShortPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShortPlanController {

    private final UserRepository userRepository;

    private final ShortPlanService shortPlanService;

    @PostMapping("/ShortPlans")
    public ResponseEntity<?> postYearPlan(@RequestBody ShortPlanRequestDto shortPlanRequestDto) {
        try {
            // UserRepository를 사용하여 실제로 존재하는 유저를 조회
            User user = userRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("해당 아이디의 유저를 찾을 수 없습니다."));

            shortPlanService.save(shortPlanRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Short Plan이 성공적으로 저장되었습니다!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Short Plan 저장 중 오류 발생: " + e.getMessage());
        }
    }
}
