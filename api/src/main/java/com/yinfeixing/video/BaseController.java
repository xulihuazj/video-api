package com.yinfeixing.video;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.yinfeiixng.video.common.Constant;
import com.yinfeiixng.video.error.BizErrorCode;
import com.yinfeiixng.video.error.SystemErrorCode;
import com.yinfeiixng.video.exception.BusinessException;
import com.yinfeixing.utils.emojiUtils.EmojiUtils;
import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.utils.net.IP;
import com.yinfeixing.video.request.APIRequest;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Set;
/**
 * BaseController是所有Controller的基类,用于一些统一/公共部分的处理
 * @description 所有Controller的基类
 */
public abstract class BaseController {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    
    protected String getBizString(HttpServletRequest httpRequest) throws IOException{
    	String bizString = null;
    	if (httpRequest.getMethod().equals("GET")) {
    		bizString = this.convertGetParam(httpRequest.getParameterMap());
    		bizString = EmojiUtils.emojiConvert(bizString);
    	}else{
    		 byte[] buffer = BaseController.getRequestPostBytes(httpRequest);
             String charEncoding = httpRequest.getCharacterEncoding();
             if (charEncoding == null) {
                 charEncoding = "UTF-8";
             }
             if (buffer != null) {
                 //字符串转json对象
                 JSONObject json = JSONObject.parseObject(EmojiUtils.emojiConvert(new String(buffer, charEncoding)));
                 //打印整个请求对象
                 if(null != json){
                     LogHelper.info(json.toJSONString());
                 }
                 APIRequest request = JSONObject.parseObject(json.toJSONString(), new TypeReference<APIRequest>() {
                 });
                 if (json != null && request.getBizRequest() != null) {
                	 bizString = JSONObject.toJSONString(request.getBizRequest());
                 }
             }
    	}
    	return bizString;
    }
    
    /**
     * @Function: BaseController.java
     * @Description: 获取json请求对象
     * @param:type:业务对象类型，groups:数据校验时的分组
     * @return：T
     * @author: xulh
     * @date: 2018年3月6日 上午10:17:12
     */
    protected <C> APIRequest<C> getObjectByRequest(Class<C> type, HttpServletRequest httpRequest, Class<?>... groups) throws Exception {
        APIRequest<C> request = null;
        GlobalLocalContext localContext = LocalContextHolder.getContext();
        // TODO 还得调整
        if (httpRequest.getMethod().equals("GET")) {
            request = new APIRequest();
            //
            String convertString = this.convertGetParam(httpRequest.getParameterMap());
            C biz = JSONObject.parseObject(EmojiUtils.emojiConvert(convertString), type);
            //对象数据校验
            this.validate(biz, groups);
            request.setToken(httpRequest.getHeader("Authorization"));
            request.setBizRequest(biz);
            localContext.setSource(httpRequest.getParameter("source"));
            localContext.setVersion(httpRequest.getParameter("version"));
            localContext.setDeviceId(httpRequest.getParameter("device_id"));
            localContext.setCurrentIp(IP.getIpAddress(httpRequest));
            request.setSource(localContext.getSource());
        } else {
            byte[] buffer = BaseController.getRequestPostBytes(httpRequest);
            String charEncoding = httpRequest.getCharacterEncoding();
            if (charEncoding == null) {
                charEncoding = "UTF-8";
            }
            if (buffer != null) {
                //字符串转json对象
                JSONObject json = JSONObject.parseObject(EmojiUtils.emojiConvert(new String(buffer, charEncoding)));
                //打印整个请求对象
                if(null != json){
                    LogHelper.info(json.toJSONString());
                }
                request = JSONObject.parseObject(json.toJSONString(), new TypeReference<APIRequest<C>>() {});
                request.setToken(httpRequest.getHeader("Authorization"));
                localContext.setSource(httpRequest.getParameter("source"));
                localContext.setVersion(request.getVersion());
                localContext.setDeviceId(request.getDeviceId());
                localContext.setCurrentIp(IP.getIpAddress(httpRequest));
                if (json != null && request.getBizRequest() != null && type != null) {

                    C biz = JSONObject.parseObject(JSONObject.toJSONString(request.getBizRequest()), type);
                    //对象数据校验
                    this.validate(biz, groups);

                    request.setBizRequest(biz);
                }

            }
        }
        LocalContextHolder.setContext(localContext);
        //获得请求头里面的参数
        return request;
    }

    private String convertGetParam(Map<String, String[]> parameterMap) {

        if (parameterMap != null && parameterMap.size() > 0) {
            JSONObject jsonObject = new JSONObject();
            parameterMap.forEach((k, v) -> {
                if (v.length == 1) {
                    // 不然下一步转换单一值有问题
                    jsonObject.put(k, v[0]);
                } else {
                    jsonObject.put(k, v);
                }
            });
            return jsonObject.toJSONString();
        }
        return null;
    }

    /**
     * @Function: BaseController.java
     * @Description: 获取json请求byte[]
     * @param:TODO
     * @return：byte[]
     * @author: mazy
     * @date: 2018年3月6日 上午10:06:37
     */
	private static byte[] getRequestPostBytes(HttpServletRequest request) throws IOException {
		int contentLength = request.getContentLength();
		byte[] body = null;
		if (request.getAttribute(Constant.API_BODY_KEY) != null) {
			body = (byte[]) request.getAttribute(Constant.API_BODY_KEY);
		} else if(contentLength > 0){
			byte[] buffer = new byte[contentLength];
			try {
				for (int i = 0; i < contentLength;) {

					int readlen = request.getInputStream().read(buffer, i, contentLength - i);
					if (readlen == -1) {
						break;
					}
					i += readlen;
				}
			} catch (Exception e) {
				LogHelper.error(e);
			}finally {
				request.getInputStream().close();
			}
			body = buffer;
			request.setAttribute(Constant.API_BODY_KEY, body);
		}
		return body;
	}

    /**
     * API输出
     *
     * @param httpRequest  HttpServletRequest
     * @param httpResponse HttpServletResponse
     * @param object       需要输出的Object
     */
    protected void output(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object object, DateFormat dateFormat) {
        OutputStream ps = null;
        ObjectMapper objectMapper = new ObjectMapper();
        // 转成蛇形
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            httpResponse.setHeader("Content-type", "application/json;charset=UTF-8");
            ps = httpResponse.getOutputStream();
            // 输出所有
            ps.write(EmojiUtils.emojiRecovery(objectMapper.writeValueAsString(object)).getBytes("UTF-8"));
        } catch (IOException e) {
            LogHelper.error(e.getMessage(), e);
            throw new BusinessException(SystemErrorCode.SYSTEM_ERROR, e.getMessage());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (IOException e) {
                    LogHelper.error(e);
                }
            }
        }
    }

    protected void output(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object object) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.output(httpRequest, httpResponse, object, dateFormat);
    }

    /**
     * @Function: BaseController.java
     * @Description: 数据校验
     * @param:obj:需要校验的对象，groups:分组
     * @return：void
     * @author: xulh
     * @date: 2018年3月6日 上午10:37:17
     */
    protected void validate(Object obj, Class<?>... groups) throws Exception {
        if (obj == null) {
            if (ArrayUtils.isNotEmpty(groups)) {
                throw new BusinessException(BizErrorCode.PARAMETER_CHECK_ERROR);
            }
            return;
        }
        String errorMsg = null;
        if (groups != null) {
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj, groups);
            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Object> cv : constraintViolations) {
                    errorMsg = cv.getMessage();
                }
            }
            if (errorMsg != null) {
                throw new BusinessException(BizErrorCode.PARAMETER_CHECK_ERROR, errorMsg);
            }
        }
    }

    /**
     * @Function: BaseController.java
     * @Description: 成功时写入json数据
     * @param:obj:需要返回的业务对象
     * @return：void
     * @author: xulh
     * @date: 2018年3月6日 上午10:35:44
     */
    protected void success(Object obj, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception {
        this.output(httpRequest, httpResponse, obj);
    }

    protected void success(Object obj, HttpServletRequest httpRequest, HttpServletResponse httpResponse, DateFormat dateFormat) throws Exception {
        this.output(httpRequest, httpResponse, obj, dateFormat);
    }
    
}
