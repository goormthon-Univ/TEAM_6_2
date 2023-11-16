package com.LikeCloud.LikeCloud.repository;

import com.LikeCloud.LikeCloud.domain.entity.DailyPlan;
import com.LikeCloud.LikeCloud.domain.type.Day;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {
    @Query("select d from DailyPlan d " +
        "where d.yearPlan.id = :yearPlanId and d.day = :day")
    Optional<DailyPlan> findByYearAndDate(@Param("yearPlanId") Long yearPlanId, @Param("day") Day day);

    @Query("select d from DailyPlan d " +
        "where d.shortPlan.id = :shortPlanId and d.day = :day")
    Optional<DailyPlan> findByShortAndDate(@Param("shortPlanId") Long shortPlanId, @Param("day") Day day);

    @Query("select d from DailyPlan d " +
        "where d.yearPlan.id in :yearPlanIds and d.day = :day")
    List<DailyPlan> findByYearsAndDate(@Param("yearPlanIds") List<Long> yearPlanIds, @Param("day") Day day);

    @Transactional
    @Modifying
    @Query("update DailyPlan dp set dp.done = false, exception = false")
    void updateDoneStatus();

    List<DailyPlan> findByYearPlanId(Long planId);

    List<DailyPlan> findByShortPlanId(Long planId);
}
