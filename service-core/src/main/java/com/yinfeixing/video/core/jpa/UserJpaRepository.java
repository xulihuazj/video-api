package com.yinfeixing.video.core.jpa;

import com.yinfeixing.video.dataobject.client.ClientUserInfoDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.Serializable;

@Repository
@EnableTransactionManagement
public interface UserJpaRepository extends Serializable,
        JpaRepository<ClientUserInfoDO, Long>, JpaSpecificationExecutor<ClientUserInfoDO> {



}