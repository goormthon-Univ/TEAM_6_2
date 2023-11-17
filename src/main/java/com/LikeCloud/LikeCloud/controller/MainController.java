package com.LikeCloud.LikeCloud.controller;

import com.LikeCloud.LikeCloud.dto.DailyDoneReqDto.CancelDailyDoneReq;
import com.LikeCloud.LikeCloud.dto.DailyDoneReqDto.DailyDoneReq;
import com.LikeCloud.LikeCloud.dto.MainResDto;
import com.LikeCloud.LikeCloud.dto.MainResDto.MainListRes;
import com.LikeCloud.LikeCloud.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;
    @GetMapping
    public ResponseEntity<MainResDto.MainListRes> getPlanList(@RequestHeader("userId") Integer userId) {
        return ResponseEntity.ok(mainService.getPlanList(userId));
    }

    @PostMapping("/DailyDone")
    public ResponseEntity<?> dailyDone(@RequestHeader("userId") Integer userId, @RequestParam("type") Integer type, @RequestParam("exception") Integer exception, @RequestBody DailyDoneReq dailyDoneReq) {
        mainService.dailyDone(userId, type, exception, dailyDoneReq);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/DailyDone")
    public ResponseEntity<MainResDto.waterDropRes> cancelDailyDone(@RequestHeader("userId") Integer userId, @RequestParam("exception") Integer exception, @RequestBody CancelDailyDoneReq cancelDailyDoneReq) {
        return ResponseEntity.ok(mainService.cancelDailyDone(cancelDailyDoneReq, exception));
    }

}
