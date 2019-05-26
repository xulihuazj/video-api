package com.yinfeiixng.video.model.mongo;


import com.yinfeixing.entity.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 电视剧信息实体类
 * <p>ClassName：MovieModel</>
 * <p>Description：视频信息实体类</p>
 *
 * @author xulh
 */
@Document(collection = "television_info")
public class TelevisionModel extends ToString {

    @Id
    private String id;

    @Field(value = "television_name")
    @Indexed(name = "television_info_idx_television_name", direction = IndexDirection.ASCENDING)
    private String televisionName;

    // 别名
    @Field(value = "alias_name")
    @Indexed(name = "television_info_idx_alias_name", direction = IndexDirection.ASCENDING)
    private String aliasName;

    // 摘要、简介
    @Field(value = "summary")
    private String summary;

    // 影片版本
    @Field(value = "television_resolution")
    private String televisionResolution;

    // 地区
    @Field(value = "zone_name")
    @Indexed(name = "television_info_idx_zone_name", direction = IndexDirection.ASCENDING)
    private String zoneName;

    // 语言
    @Field(value = "language_name")
    @Indexed(name = "television_info_idx_language_name", direction = IndexDirection.ASCENDING)
    private String languageName;

    // 上映时间
    @Field(value = "release_time")
    private String releaseTime;

    // 年份
    @Field(value = "year_num")
    private String yearNum;

    // 长度
    @Field(value = "movie_length")
    private String movieLength;

    // 主图
    @Field(value = "movie_image")
    private String movieImage;

    // 剧情描述
    @Field(value = "describe")
    private String describe;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelevisionName() {
        return televisionName;
    }

    public void setTelevisionName(String televisionName) {
        this.televisionName = televisionName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTelevisionResolution() {
        return televisionResolution;
    }

    public void setTelevisionResolution(String televisionResolution) {
        this.televisionResolution = televisionResolution;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getYearNum() {
        return yearNum;
    }

    public void setYearNum(String yearNum) {
        this.yearNum = yearNum;
    }

    public String getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(String movieLength) {
        this.movieLength = movieLength;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
