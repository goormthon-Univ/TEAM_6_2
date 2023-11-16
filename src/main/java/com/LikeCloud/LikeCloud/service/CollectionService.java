package com.LikeCloud.LikeCloud.service;

import com.LikeCloud.LikeCloud.domain.entity.MyCloud;
import com.LikeCloud.LikeCloud.domain.entity.User;
import com.LikeCloud.LikeCloud.domain.type.CloudType;
import com.LikeCloud.LikeCloud.dto.CollectionResDto;
import com.LikeCloud.LikeCloud.dto.CollectionResDto.CloudListRes;
import com.LikeCloud.LikeCloud.dto.CollectionResDto.CollectionListRes;
import com.LikeCloud.LikeCloud.repository.MyCloudRepository;
import com.LikeCloud.LikeCloud.repository.UserRepository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CollectionService {
    private final UserRepository userRepository;
    private final MyCloudRepository myCloudRepository;

    public CollectionResDto.CollectionListRes getCollection(Integer type) {
        User user = findUser();
        CloudType cloudType = findCloudType(type);
        List<MyCloud> myCloudList = myCloudRepository.findByUserIdAndCloudType(user.getId(), cloudType);

        return new CollectionListRes(myCloudList.stream().map(CloudListRes::new).collect(Collectors.toList()));

    }

    public User findUser() {
        User user = userRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("User를 찾을 수 없습니다."));
        return user;
    }

    public CloudType findCloudType(Integer type) {
        CloudType cloudType = Arrays.stream(CloudType.values())
            .filter(c -> c.getNum() == type)
            .findAny().get();
        return cloudType;
    }

}
