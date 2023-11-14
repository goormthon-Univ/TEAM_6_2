package com.LikeCloud.LikeCloud.domain.entity;

import com.LikeCloud.LikeCloud.domain.BaseTimeEntity;
import com.LikeCloud.LikeCloud.domain.type.Day;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Entity
public class DailyPlan extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "daily_plan_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "year_plan_id")
    private YearPlan yearPlan;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "short_plan_id")
    private ShortPlan shortPlan;

    @Enumerated(value = STRING)
    @Column(nullable = false)
    private Day day;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String plan;

    @Column(nullable = false)
    private Boolean exception;

    @Column(nullable = false)
    private Boolean done;

    public void setYearPlan(YearPlan yearPlan) {
        this.yearPlan = yearPlan;
    }

    public void updateDone(Integer exception) {
        if (exception == 0) {
            this.exception = false;
            this.done = true;
        } else if(exception == 1) {
            this.exception = true;
            this.done = false;
        }
    }
}
