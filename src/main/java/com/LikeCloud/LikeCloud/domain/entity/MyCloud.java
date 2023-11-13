package com.LikeCloud.LikeCloud.domain.entity;

import com.LikeCloud.LikeCloud.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Entity
public class MyCloud extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "my_cloud_id")
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

    @Column(nullable = false)
    private Integer cloudImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String cloudType;
}
