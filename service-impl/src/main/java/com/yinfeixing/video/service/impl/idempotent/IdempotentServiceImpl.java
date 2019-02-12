package com.yinfeixing.video.service.impl.idempotent;

import com.yinfeixing.video.service.idempotent.IdempotentService;
import org.springframework.stereotype.Service;

@Service
public class IdempotentServiceImpl implements IdempotentService {
    @Override
    public void idempotentVerify(String key, String uuid) {

    }

    @Override
    public void deleteIdempotentLock(String key, String uuid) {

    }

    @Override
    public boolean idempotentVerify2(String key, String uuid) {
        return false;
    }
}
