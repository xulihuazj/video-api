package com.yinfeixing.video.core.jpa;

import com.yinfeixing.video.dataobject.client.ClientUserInfoDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface VideoJpaRepository extends Serializable,
        JpaRepository<ClientUserInfoDO, Long>, JpaSpecificationExecutor<ClientUserInfoDO> {
}
