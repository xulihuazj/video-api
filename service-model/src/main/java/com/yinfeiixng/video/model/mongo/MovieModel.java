package com.yinfeiixng.video.model.mongo;

import com.yinfeixing.entity.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 电影信息实体类
 * <p>ClassName：MovieModel</>
 * <p>Description：视频信息实体类</p>
 *
 * @author xulh
 */
//标注在实体类上，类似于hibernate的entity注解，标明由mongo来维护该表。
@Document(collection = "movie_info")
public class MovieModel extends ToString {

    private static final long serialVersionUID = 3503790361839243561L;

    @Id//主键，不可重复，自带索引，可以在定义的列名上标注，需要自己生成并维护不重复的约束。
    private String id;

    // @Field：代表一个字段，可以不加，不加的话默认以参数名为列名
    @Field(value = "video_name")
    @Indexed(name = "movie_info_idx_movie_name", direction = IndexDirection.ASCENDING)
    private String movieName;

    @Field(value = "alias_name")
    @Indexed(name = "movie_info_idx_alias_name", direction = IndexDirection.ASCENDING)
    private String aliasName;

    // 摘要、简介
    @Field(value = "summary")
    private String summary;

    // 影片版本
    @Field(value = "movie_resolution")
    private String movieResolution;

    // 地区
    @Field(value = "zone_name")
    @Indexed(name = "movie_info_idx_zone_name", direction = IndexDirection.ASCENDING)
    private String zoneName;

    // 语言
    @Field(value = "language_name")
    @Indexed(name = "movie_info_idx_language_name", direction = IndexDirection.ASCENDING)
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
    @Field(value = "video_image")
    private String movieImage;

    // 剧情描述
    @Field(value = "describe")
    private String describe;

    //@Transient：该注解标注的，将不会被录入到数据库中。只作为普通的javaBean属性。
    @Transient
    private String extInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
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

    public String getMovieResolution() {
        return movieResolution;
    }

    public void setMovieResolution(String movieResolution) {
        this.movieResolution = movieResolution;
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

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
}