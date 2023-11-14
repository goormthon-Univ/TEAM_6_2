package com.LikeCloud.LikeCloud.repository;

import com.LikeCloud.LikeCloud.domain.entity.YearPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YearPlanRepository extends JpaRepository<YearPlan, Long> {
    List<YearPlan> findByUserId(Integer id);
}
