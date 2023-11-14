package com.LikeCloud.LikeCloud.controller;

import com.LikeCloud.LikeCloud.dto.MainResDto;
import com.LikeCloud.LikeCloud.dto.MainResDto.MainListRes;
import com.LikeCloud.LikeCloud.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/main")
@RestController
public class MainController {

    private final MainService mainService;
    @GetMapping
    public ResponseEntity<MainResDto.MainListRes> getPlanList() {
        return ResponseEntity.ok(mainService.getPlanList());
    }

}
