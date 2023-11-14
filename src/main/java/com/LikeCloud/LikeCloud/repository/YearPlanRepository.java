package com.LikeCloud.LikeCloud.repository;

import com.LikeCloud.LikeCloud.domain.entity.YearPlan;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface YearPlanRepository extends JpaRepository<YearPlan, Long> {

    List<YearPlan> findByUserId(Long userId);
    Optional<YearPlan> findById(Long id);
    void deleteById(Long id);
}