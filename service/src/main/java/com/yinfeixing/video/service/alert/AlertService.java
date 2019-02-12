package com.cf.pms.service.alert;

import com.cf.api.request.APIRequest;
import com.cf.api.request.alert.AddEvolveRequest;
import com.cf.api.request.alert.AlertInfoRequest;
import com.cf.api.request.alert.DataStatRequest;
import com.cf.api.request.alert.RiskRequest;
import com.cf.api.response.APIResponse;
import com.cf.api.response.alert.AddEvolveResponse;
import com.cf.api.response.alert.DataStatResponse;
import com.cf.api.response.alert.RiskResponse;

import java.util.Date;
import java.util.Map;

public interface AlertService {

    /**
     * @description: JOB告警轮询
     * @param:
     * @return:
     * @author: xulh
     * @date: 2018/7/4 9:31
     **/
    void alertEvent() throws Exception;
}
