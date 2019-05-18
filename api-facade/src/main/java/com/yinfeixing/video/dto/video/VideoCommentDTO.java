package com.yinfeixing.video.dto.video;

import com.yinfeixing.entity.ToString;

public class VideoCommentDTO extends ToString {

    private String commentSource;

    // 评分
    private String commentScore;

    // 连接
    private String commentScoreLink;

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
