package com.yinfeiixng.video.model.mongo;

import com.yinfeixing.entity.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * 视频信息实体类
 * <p>ClassName：VideoModel</>
 * <p>Description：视频信息实体类</p>
 *
 * @author xulh
 */
//标注在实体类上，类似于hibernate的entity注解，标明由mongo来维护该表。
@Document(collection = "movie_info")
public class VideoModel extends ToString {

    private static final long serialVersionUID = 3503790361839243561L;

    @Id//主键，不可重复，自带索引，可以在定义的列名上标注，需要自己生成并维护不重复的约束。
    private String id;

    @Field(value = "video_info_video_id")
    //声明该字段需要加索引，加索引后以该字段为条件检索将大大提高速度
    //唯一索引的话是@Indexed(unique = true)
    //@CompoundIndex：复合索引，加复合索引后通过复合索引字段查询将大大提高速度
    @Indexed(name = "video_info_idx_video_id", unique = true, direction = IndexDirection.ASCENDING)
    private Long videoId;

    // @Field：代表一个字段，可以不加，不加的话默认以参数名为列名
    @Field(value = "video_name")
    @Indexed(name = "video_info_idx_video_name", direction = IndexDirection.ASCENDING)
    private String videoName;

    // 演员表
    @DBRef
    @Field(value = "video_performer_list")
    private List<VideoPerformerModel> videoPerformerList;

    // 摘要、简介
    @Field(value = "summary")
    private String summary;

    // 剧情描述
    @Field(value = "describe")
    private String describe;

    //@Transient：该注解标注的，将不会被录入到数据库中。只作为普通的javaBean属性。
    @Transient
    private String extInfo;

    //@DBRef：关联另一个document对象。类似于mysql的表关联，但并不一样，mongo不会做级联的操作。
    @DBRef
    @Field(value = "video_image_list")
    private List<VideoImageModel> videoImageList;

    // 视频别名
    @DBRef
    @Field(value = "video_alias_list")
    private List<VideoAliasModel> videoAliasList;

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

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public List<VideoPerformerModel> getVideoPerformerList() {
        return videoPerformerList;
    }

    public void setVideoPerformerList(List<VideoPerformerModel> videoPerformerList) {
        this.videoPerformerList = videoPerformerList;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public List<VideoImageModel> getVideoImageList() {
        return videoImageList;
    }

    public void setVideoImageList(List<VideoImageModel> videoImageList) {
        this.videoImageList = videoImageList;
    }

    public List<VideoAliasModel> getVideoAliasList() {
        return videoAliasList;
    }

    public void setVideoAliasList(List<VideoAliasModel> videoAliasList) {
        this.videoAliasList = videoAliasList;
    }
}
