package com.yinfeiixng.video.model.mongo;

import com.yinfeixing.entity.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 视频图片
 */
@Document(collection = "video_image_info")
public class VideoImageModel extends ToString {

    @Id
    private String id;

    @Field(value = "video_image_id")
    @Indexed(name = "idx_video_image_id", unique = true, direction = IndexDirection.ASCENDING)
    private Long videoImageId;

    @Field(value = "video_id")
    @Indexed(name = "idx_video_id")
    private Long videoId;

    // 图片地址
    @Field(value = "video_image_url")
    private String videoImageUrl;

    @Field(value = "video_image_status")
    @Indexed(name = "idx_video_image_status")
    private String videoImageStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVideoImageId() {
        return videoImageId;
    }

    public void setVideoImageId(Long videoImageId) {
        this.videoImageId = videoImageId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getVideoImageUrl() {
        return videoImageUrl;
    }

    public void setVideoImageUrl(String videoImageUrl) {
        this.videoImageUrl = videoImageUrl;
    }

    public String getVideoImageStatus() {
        return videoImageStatus;
    }

    public void setVideoImageStatus(String videoImageStatus) {
        this.videoImageStatus = videoImageStatus;
    }
}
