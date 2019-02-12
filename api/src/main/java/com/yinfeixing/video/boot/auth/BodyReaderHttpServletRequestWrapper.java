package com.yinfeixing.video.boot.auth;


import com.cf.utils.log.LogHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * BodyReaderHttpServletRequestWrapper.java 1.0.0 2018-06-21  15:34
 * Copyright © 2014-2018,52mamahome.com.All rights reserved
 * history :
 * 1. 2018-06-21  15:34 @author  zhoujiahao
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] body;
    private Logger logger = LogManager.getLogger(BodyReaderHttpServletRequestWrapper.class);

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request)
            throws IOException {
        super(request);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bs = new byte[1024];
        int len;
        while ((len = request.getInputStream().read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        body = bos.toByteArray();
        LogHelper.debug(logger, "BodyReaderHttpServletRequestWrapper 构造获取数据：{0}", bos.toString("utf-8"));
        bos.close();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        LogHelper.debug(logger, "BodyReaderHttpServletRequestWrapper getInputStream 获取数据：{0}", new String(body, "utf-8"));
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }
}
