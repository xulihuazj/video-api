package com.yinfeiixng.video.model.mongo;

import com.yinfeixing.entity.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "video_comment_info")
public class VideoCommentModel extends ToString {

    @Id
    private String id;

    @Field(value = "video_id")
    @Indexed(name = "video_director_info_idx_video_id")
    private String videoId;

    // 类型：MOVIE、
    @Field(value = "video_type")
    private String videoType;

    // 来源
    @Field(value = "comment_source")
    private String commentSource;

    // 评分
    @Field(value = "comment_score")
    private String commentScore;

    // 连接
    @Field(value = "comment_score_link")
    private String commentScoreLink;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getCommentSource() {
        return commentSource;
    }

    public void setCommentSource(String commentSource) {
        this.commentSource = commentSource;
    }

    public String getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(String commentScore) {
        this.commentScore = commentScore;
    }

    public String getCommentScoreLink() {
        return commentScoreLink;
    }

    public void setCommentScoreLink(String commentScoreLink) {
        this.commentScoreLink = commentScoreLink;
    }
}
