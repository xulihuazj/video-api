package com.yinfeixing.video.boot.apihandler;

import com.yinfeiixng.video.error.SystemErrorCode;
import com.yinfeiixng.video.exception.BusinessException;
import com.yinfeixing.entity.BaseEnum;
import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.utils.net.IP;
import com.yinfeixing.utils.string.StringUtil;
import com.yinfeixing.video.GlobalLocalContext;
import com.yinfeixing.video.LocalContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author xulh
 * 自定义数据绑定
 */
public class APIDataBinder extends ServletRequestDataBinder {

    private Logger logger = LogManager.getLogger(APIDataBinder.class);

    APIDataBinder(Object target) {
        super(target);
    }

    @Override
    public void bind(ServletRequest request) {
        initializeContext((HttpServletRequest) request);
        MutablePropertyValues originPropertyValues = new ServletRequestParameterPropertyValues(request);
        MutablePropertyValues camelCasePropertyValues = new ServletRequestParameterPropertyValues(request);
        // 将原始url参数转换为驼峰命名属性
        for (PropertyValue propertyValue : originPropertyValues.getPropertyValueList()) {
            String name = propertyValue.getName();
            String camelCaseName = StringUtil.camelCaseName(name);
            if (!camelCaseName.equals(name)) {
                PropertyValue value = originPropertyValues.getPropertyValue(name);
                camelCasePropertyValues.removePropertyValue(name);
                camelCasePropertyValues.addPropertyValue(camelCaseName, value.getValue());
            }
        }
        MultipartRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartRequest.class);
        if (multipartRequest != null) {
            bindMultipart(multipartRequest.getMultiFileMap(), camelCasePropertyValues);
        }

        Object target = this.getTarget();
        Field[] fields = target.getClass().getDeclaredFields();

        for (int j = 0; j < fields.length; j++) {
            try {
                String name = fields[j].getName();
                String camelCaseName = StringUtil.camelCaseName(name);
                if (!camelCasePropertyValues.contains(camelCaseName)) {
                    LogHelper.debug(logger, "不包含当前值，camelCaseName:{0}", camelCaseName);
                    continue;
                }
                String parameterValue = (String) camelCasePropertyValues.get(camelCaseName);
                name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                String type = fields[j].getGenericType().toString(); // 获取属性的类型
                if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                    Method m = target.getClass().getMethod("get" + name);
                    String value = (String) m.invoke(target);
                    if (StringUtils.isBlank(value)) {
                        m = target.getClass().getMethod("set" + name, String.class);
                        m.invoke(target, parameterValue);
                    }
                } else if (fields[j].getType().getEnumConstants() != null) {
                    Method m = target.getClass().getMethod("get" + name);
                    if (m.invoke(target) == null) {
                        for (Object enumProperty : fields[j].getType().getEnumConstants()) {
                            if (enumProperty instanceof BaseEnum) {
                                BaseEnum baseEnum = (BaseEnum) enumProperty;
                                if (baseEnum.getCode().equals(parameterValue)) {
                                    m = target.getClass().getMethod("set" + name, enumProperty.getClass());
                                    m.invoke(target, enumProperty);
                                    break;
                                }
                            }
                        }
                    }
                } else if (type.equalsIgnoreCase("int")) {
                    Method m = target.getClass().getMethod("get" + name);
                    int value = (int) m.invoke(target);
                    if (value == 0) {
                        m = target.getClass().getMethod("set" + name, int.class);
                        if (StringUtils.isNotEmpty(parameterValue)) {
                            m.invoke(target, Integer.valueOf(parameterValue));
                        } else {
                            m.invoke(target, 0);
                        }
                    }
                } else if (type.equalsIgnoreCase("class java.lang.Integer")) {
                    Method m = target.getClass().getMethod("get" + name);
                    Integer value = (Integer) m.invoke(target);
                    if (StringUtils.isNotEmpty(parameterValue)) {
                        Integer integerValue = Integer.valueOf(parameterValue);
                        if (value == null) {
                            m = target.getClass().getMethod("set" + name, Integer.class);
                            m.invoke(target, integerValue);
                        }
                    }
                } else if (type.equalsIgnoreCase("long")) {
                    Method m = target.getClass().getMethod("get" + name);
                    int value = (int) m.invoke(target);
                    if (value == 0) {
                        m = target.getClass().getMethod("set" + name, long.class);
                        if (StringUtils.isNotEmpty(parameterValue)) {
                            m.invoke(target, Long.valueOf(parameterValue));
                        } else {
                            m.invoke(target, 0);
                        }
                    }
                } else if (type.equalsIgnoreCase("class java.lang.Long")) {
                    Method m = target.getClass().getMethod("get" + name);
                    Long value = (Long) m.invoke(target);
                    if (StringUtils.isNotEmpty(parameterValue)) {
                        Long integerValue = Long.valueOf(parameterValue);
                        if (value == null) {
                            m = target.getClass().getMethod("set" + name, Long.class);
                            m.invoke(target, integerValue);
                        }
                    }
                } else if (type.equalsIgnoreCase("double")) {
                    Method m = target.getClass().getMethod("get" + name);
                    double value = (double) m.invoke(target);
                    if (value == 0) {
                        m = target.getClass().getMethod("set" + name, double.class);
                        if (StringUtils.isNotEmpty(parameterValue)) {
                            m.invoke(target, Double.valueOf(parameterValue));
                        } else {
                            m.invoke(target, 0);
                        }
                    }
                } else if (type.equalsIgnoreCase("class java.lang.Double")) {
                    Method m = target.getClass().getMethod("get" + name);
                    Double value = (Double) m.invoke(target);
                    if (StringUtils.isNotEmpty(parameterValue)) {
                        Double integerValue = Double.valueOf(parameterValue);
                        if (value == null) {
                            m = target.getClass().getMethod("set" + name, Double.class);
                            m.invoke(target, integerValue);
                        }
                    }
                }
            } catch (Exception e) {
                LogHelper.exception(e, logger, "【APIDataBinder】自定义绑定数据异常");
                throw new BusinessException(SystemErrorCode.SYSTEM_ERROR);
            }

        }
    }

    /**
     * 初始化 GlobalLocalContext
     */
    private void initializeContext(HttpServletRequest httpServletRequest) {
        GlobalLocalContext localContext = LocalContextHolder.getContext();
        localContext.setSource(httpServletRequest.getParameter("source"));
        localContext.setVersion(httpServletRequest.getParameter("version"));
        localContext.setDeviceId(httpServletRequest.getParameter("device_id"));
        localContext.setCurrentIp(IP.getIpAddress(httpServletRequest));
        LocalContextHolder.setContext(localContext);
    }


}
