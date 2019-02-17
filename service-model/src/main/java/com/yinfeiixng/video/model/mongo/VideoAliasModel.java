package com.yinfeiixng.video.model.mongo;

import com.yinfeixing.entity.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 视频别名
 */
@Document(collection = "video_alias_info")
public class VideoAliasModel extends ToString {

    @Id
    private String id;

    // 视频ID
    @Indexed(name = "video_image_info_idx_video_id", direction = IndexDirection.ASCENDING)
    private Long videoId;

    // 别名
    @Field(value = "alias_name")
    @Indexed(name = "video_performer_info_idx_alias_name", direction = IndexDirection.ASCENDING)
    private String aliasName;

    // 状态
    private String aliasStatus;

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

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getAliasStatus() {
        return aliasStatus;
    }

    public void setAliasStatus(String aliasStatus) {
        this.aliasStatus = aliasStatus;
    }
}