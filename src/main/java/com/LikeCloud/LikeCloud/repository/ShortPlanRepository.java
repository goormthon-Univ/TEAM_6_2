package com.LikeCloud.LikeCloud.repository;

import com.LikeCloud.LikeCloud.domain.entity.ShortPlan;
import com.LikeCloud.LikeCloud.domain.entity.YearPlan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ShortPlanRepository extends JpaRepository<ShortPlan, Long> {
    List<ShortPlan> findByUserId(Long UserId);
    @Transactional
    @Modifying
    @Query("update ShortPlan sp set sp.waterDrop = sp.waterDrop - 1 " +
        "where sp.id = :shortPlanId")
    void updateWaterDrop(@Param("shortPlanId") Long shortPlanId);
}