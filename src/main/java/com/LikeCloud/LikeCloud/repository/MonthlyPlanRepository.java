package com.LikeCloud.LikeCloud.repository;

import com.LikeCloud.LikeCloud.domain.entity.MonthlyPlan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MonthlyPlanRepository extends JpaRepository<MonthlyPlan, Long> {
//    @Query("select m from MonthlyPlan m " +
//        "where m.user.id = :userId " +
//        "and m.yearPlan.id = :yearPlanId " +
//        "and m.year = :year and m.month = :month")
    @Query("select m from MonthlyPlan m " +
        "join fetch m.yearPlan yp " +
        "where yp.id = :yearPlanId and m.year = :year and m.month = :month")
    Optional<MonthlyPlan> findByYearDate(@Param("yearPlanId") Long yearPlanId,
        @Param("year") Integer year, @Param("month") Integer month);

    @Query("select m from MonthlyPlan m " +
        "join fetch m.yearPlan yp " +
        "where yp.id in :yearPlanIds and m.year = :year and m.month = :month")
    List<MonthlyPlan> findByYearAndDate(@Param("yearPlanIds") List<Long> yearPlanIds,
        @Param("year") Integer year, @Param("month") Integer month);

}
