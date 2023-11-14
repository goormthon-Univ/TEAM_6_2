package com.LikeCloud.LikeCloud.repository;

import com.LikeCloud.LikeCloud.domain.entity.ShortPlan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortPlanRepository extends JpaRepository<ShortPlan, Long> {
    List<ShortPlan> findByUserId(Long UserId);
}