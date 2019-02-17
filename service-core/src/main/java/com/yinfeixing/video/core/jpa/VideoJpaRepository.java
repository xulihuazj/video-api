package com.yinfeixing.video.core.jpa;

import com.yinfeixing.video.dataobject.video.VideoDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;

public interface VideoJpaRepository extends Serializable,
        JpaRepository<VideoDO, Long>, JpaSpecificationExecutor<VideoDO> {

    @Query("select t from video_info t where t.video_name = ?1")
    VideoDO findByUsername(String videoName);



}
