package com.LikeCloud.LikeCloud.domain.entity;

import com.LikeCloud.LikeCloud.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
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
public class ShortPlan extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "short_plan_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String shortPlan;

    @OneToMany(mappedBy = "shortPlan", cascade = CascadeType.ALL)
    private List<DailyPlan> dailyPlans = new ArrayList<>();

    @Column(nullable = false)
    private Integer period;

    @Column(nullable = false)
    private Integer waterDrop;

    @Column(nullable = false)
    private Integer steam;

    @Column(nullable = false)
    private Integer miniCloud;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Boolean dailyFix;  //0이면 고정 주, 1이면 비고정

    @Column(nullable = false)
    private Boolean done;

    public void setUser(User user) {
        this.user = user;
    }

    public List<DailyPlan> getDailyPlans() {
        return dailyPlans;
    }

    public void updateCloud(Integer[] clouds) {
        this.waterDrop = clouds[0];
        this.steam = clouds[1];
        this.miniCloud = clouds[2];
    }
}
