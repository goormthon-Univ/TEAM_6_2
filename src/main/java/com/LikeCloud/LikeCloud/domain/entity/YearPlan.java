package com.LikeCloud.LikeCloud.domain.entity;

import com.LikeCloud.LikeCloud.domain.BaseTimeEntity;
import javax.persistence.criteria.CriteriaBuilder.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Entity
public class YearPlan extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "year_plan_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String yearPlan;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String halfPlan;

    @OneToMany(mappedBy = "yearPlan", cascade = CascadeType.ALL)
    private List<DailyPlan> dailyPlans = new ArrayList<>();

    @Column(nullable = false)
    private Integer waterDrop;

    @Column(nullable = false)
    private Integer steam;

    @Column(nullable = false)
    private Integer miniCloud;

    @Column(nullable = false)
    private Integer bigCloud;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Boolean dailyFix;  //0이면 고정 주, 1이면 비고정

    @Column(nullable = false)
    private Boolean done;

    public void setUser(User user) {
        this.user = user;
    }

    public void setHalfPlan(String halfPlan) {
        this.halfPlan = halfPlan;
    }

    @OneToMany(mappedBy = "yearPlan", cascade = CascadeType.ALL)
    private List<MonthlyPlan> monthlyPlans = new ArrayList<>();

    public void addMonthlyPlan(MonthlyPlan monthlyPlan) {
        this.monthlyPlans.add(monthlyPlan);
        monthlyPlan.setYearPlan(this);
    }

    public List<MonthlyPlan> getMonthlyPlans() {
        return this.monthlyPlans;
    }

    public List<DailyPlan> getDailyPlans() {
        return dailyPlans;
    }

    public void setDailyPlans(List<DailyPlan> dailyPlans) {
        this.dailyPlans = dailyPlans;
    }

    public void updateCloud(Integer[] clouds) {
        this.waterDrop = clouds[0];
        this.steam = clouds[1];
        this.miniCloud = clouds[2];
        this.bigCloud = clouds[3];
    }

    public void updateDone() {
        this.done = true;
    }
}
