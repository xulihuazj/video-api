package com.yinfeixing.video.boot.auth;


import com.yinfeiixng.video.common.Constant;
import com.yinfeixing.utils.string.StringUtil;
import com.yinfeixing.video.api.LocalContextHolder;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * 支付通知过滤 requestFilter
 */
@Component
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LocalContextHolder.setContext(LocalContextHolder.createEmptyContext());
        String traceId =  UUID.randomUUID().toString();
        traceId = StringUtil.isEmpty(traceId)? "":traceId.replace("-","");
        ThreadContext.put(Constant.TRACE_ID, traceId);

        // 具有@SystemType注解标注的api
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            //如果是微信支付
            if (((HttpServletRequest) request).getRequestURI().contains("/order/weixin/pay/notify")) {
//                requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request);
            }
        }
        if (null == requestWrapper) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }

    @Override
    public void destroy() {
        ThreadContext.clearAll();
    }
}
