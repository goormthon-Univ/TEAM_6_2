package com.LikeCloud.LikeCloud.dto;

import com.LikeCloud.LikeCloud.domain.entity.MyCloud;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class CollectionResDto {

    @Getter
    public static class CollectionListRes {
        private List<CloudListRes> clouds;

        public CollectionListRes(List<CloudListRes> cloudListRes) {
            this.clouds = cloudListRes;
        }
    }

    @Getter
    public static class CloudListRes {
        private Integer cloud_id;
        private Integer image_num;

        public CloudListRes(MyCloud myCloud) {
            this.cloud_id = Math.toIntExact(myCloud.getId());
            this.image_num = myCloud.getCloudImage();
        }
    }
}
