package com.LikeCloud.LikeCloud.controller;

import com.LikeCloud.LikeCloud.dto.CollectionResDto;
import com.LikeCloud.LikeCloud.dto.CollectionResDto.CollectionListRes;
import com.LikeCloud.LikeCloud.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collection")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping
    public ResponseEntity<CollectionResDto.CollectionListRes> getCollection(@RequestParam("type") Integer type) {
        return ResponseEntity.ok(collectionService.getCollection(type));
    }

}
