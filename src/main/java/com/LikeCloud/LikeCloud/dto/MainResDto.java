package com.LikeCloud.LikeCloud.dto;

import com.LikeCloud.LikeCloud.domain.entity.DailyPlan;
import com.LikeCloud.LikeCloud.domain.entity.MonthlyPlan;
import com.LikeCloud.LikeCloud.domain.entity.ShortPlan;
import com.LikeCloud.LikeCloud.domain.entity.YearPlan;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MainResDto {

    @Getter
    public static class MainListRes {
        private List<YearPlanListRes> yearPlans;
        private List<ShortPlanListRes> shortPlans;

        public MainListRes(List<YearPlanListRes> yearPlans, List<ShortPlanListRes> shortPlans) {
            this.yearPlans = yearPlans;
            this.shortPlans = shortPlans;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class YearPlanListRes {
        private Long year_plan_id;
        private String yearPlan;
        private String halfPlan;
        private String monthPlan;
        private String dailyPlan;
        private Integer waterDrop;
        private Integer steam;
        private Integer miniCloud;
        private Boolean done;
        private Boolean exception;

        public static YearPlanListRes from(YearPlan yearPlan, MonthlyPlan monthlyPlan, DailyPlan dailyPlan) {
            return YearPlanListRes.builder()
                .year_plan_id(yearPlan.getId())
                .yearPlan(yearPlan.getYearPlan())
                .halfPlan(yearPlan.getHalfPlan())
                .monthPlan(monthlyPlan != null ? monthlyPlan.getPlan() : null)  // monthlyPlan이 null이면 빈 문자열
                .dailyPlan(dailyPlan != null ? dailyPlan.getPlan() : null)  // dailyPlan이 null이면 빈 문자열
                .waterDrop(yearPlan.getWaterDrop())
                .steam(yearPlan.getSteam())
                .miniCloud(yearPlan.getMiniCloud())
                .done(dailyPlan != null ? dailyPlan.getDone() : null)  // dailyPlan이 null이면 빈 문자열
                .exception(dailyPlan != null ? dailyPlan.getException() : null)  // dailyPlan이 null이면 빈 문자열
                .build();
        }

    }

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ShortPlanListRes {
        private Long short_plan_id;
        private String shortPlan;
        private String dailyPlan;
        private Integer waterDrop;
        private Integer steam;
        private Integer miniCloud;
        private Integer period;
        private Boolean done;
        private Boolean exception;

        public static ShortPlanListRes from(ShortPlan shortPlan, DailyPlan dailyPlan) {
            return ShortPlanListRes.builder()
                .short_plan_id(shortPlan.getId())
                .shortPlan(shortPlan.getShortPlan())
                .dailyPlan(dailyPlan != null ? dailyPlan.getPlan() : null)
                .waterDrop(shortPlan.getWaterDrop())
                .steam(shortPlan.getSteam())
                .miniCloud(shortPlan.getMiniCloud())
                .period(shortPlan.getPeriod())
                .done(dailyPlan != null ? dailyPlan.getDone() : null)
                .exception(dailyPlan != null ? dailyPlan.getException() : null)
                .build();
        }
    }

}
