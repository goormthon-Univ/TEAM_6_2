package com.LikeCloud.LikeCloud.repository;

import com.LikeCloud.LikeCloud.domain.entity.ShortPlan;
import com.LikeCloud.LikeCloud.domain.entity.YearPlan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortPlanRepository extends JpaRepository<ShortPlan, Long> {
    List<ShortPlan> findByUserId(Long UserId);
}