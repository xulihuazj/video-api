package com.yinfeixing.video.service.impl.ip;

import com.yinfeixing.video.service.ip.IpWhiteService;
import org.springframework.stereotype.Service;

@Service
public class IpWhiteServiceImpl implements IpWhiteService {
    @Override
    public boolean checkIpWhite(String url, String IP) {
        return false;
    }
}
