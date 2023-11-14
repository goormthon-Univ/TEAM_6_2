package com.LikeCloud.LikeCloud.repository;

import com.LikeCloud.LikeCloud.domain.entity.ShortPlan;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortPlanRepository extends JpaRepository<ShortPlan, Long> {
    List<ShortPlan> findByUserId(Long UserId);

    Optional<ShortPlan> findById(Long id);
    void deleteById(Long id);
}