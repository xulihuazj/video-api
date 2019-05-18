package com.yinfeiixng.video.model.mongo;

import com.yinfeixing.entity.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 演员
 */
@Document(collection = "video_performer_info")
public class VideoPerformerModel extends ToString {

    @Id
    private String id;

    // 视频ID
    @Indexed(name = "video_performer_info_idx_video_id", direction = IndexDirection.ASCENDING)
    private Long videoId;

    @Field(value = "video_type")
    private String videoType;


    // 演员名称
    @Field(value = "performer_name")
    @Indexed(name = "video_performer_info_idx_performer_name", direction = IndexDirection.ASCENDING)
    private String performerName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getPerformerName() {
        return performerName;
    }

    public void setPerformerName(String performerName) {
        this.performerName = performerName;
    }
}
