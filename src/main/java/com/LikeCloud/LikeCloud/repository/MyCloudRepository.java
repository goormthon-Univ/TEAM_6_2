package com.LikeCloud.LikeCloud.repository;

import com.LikeCloud.LikeCloud.domain.entity.MyCloud;
import com.LikeCloud.LikeCloud.domain.type.CloudType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MyCloudRepository extends JpaRepository<MyCloud, Long> {
    List<MyCloud> findByUserIdAndCloudType(Long UserId, CloudType cloudType);
}
