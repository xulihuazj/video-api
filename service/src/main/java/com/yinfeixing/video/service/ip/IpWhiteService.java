package com.yinfeixing.video.service.ip;

import java.util.Map;

public interface IpWhiteService {

    /**
     * @Function: IpWhiteService.java
     * @Description: 检查路径及IP是否可以放行
     * @param:TODO
     * @return：boolean
     * @author: mazy
     * @date: 2018年5月31日 下午4:21:33
     */
    boolean checkIpWhite(String url, String IP);

    /**
     * @Function: IpWhiteService.java
     * @Description: 加载ip白名单配置，并存储到redis
     * @param:TODO
     * @return：Map<String, Map<String   ,   IpWhiteListModel>>
     * @author: mazy
     * @date: 2018年5月31日 下午4:58:08
     */
//    Map<String, Map<String,IpWhiteListModel>> loaderConfiguration();

}
