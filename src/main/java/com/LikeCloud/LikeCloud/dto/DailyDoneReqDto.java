package com.LikeCloud.LikeCloud.dto;

import javax.persistence.criteria.CriteriaBuilder.In;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class DailyDoneReqDto {

    @NoArgsConstructor
    @Getter
    public static class DailyDoneReq {
        private Integer year_plan_id;
        private Integer short_plan_id;
        private Integer image_num;

        public DailyDoneReq(Integer year_plan_id, Integer short_plan_id, Integer image_num) {
            this.year_plan_id = year_plan_id;
            this.short_plan_id = short_plan_id;
            this.image_num = image_num;
        }
    }

}
