package com.LikeCloud.LikeCloud.repository;

import com.LikeCloud.LikeCloud.domain.entity.YearPlan;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface YearPlanRepository extends JpaRepository<YearPlan, Long> {

    List<YearPlan> findByUserId(Long userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update YearPlan yp set yp.waterDrop = yp.waterDrop - 1 " +
            "where yp.id = :yearPlanId")
    void updateWaterDrop(@Param("yearPlanId") Long yearPlanId);

    Optional<YearPlan> findById(Long id);

    void deleteById(Long id);
}