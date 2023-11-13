package com.LikeCloud.LikeCloud.domain.entity;

import com.LikeCloud.LikeCloud.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

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
    private Boolean done;
}
