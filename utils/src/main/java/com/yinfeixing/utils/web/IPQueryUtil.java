package com.yinfeixing.utils.web;

import com.alibaba.fastjson.JSONObject;
import com.yinfeixing.utils.log.LogHelper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class IPQueryUtil {
	public static String queryCity(String IP) throws ClientProtocolException, IOException{
		String cityName = "内网IP";
		JSONObject data = ipQuery(IP);
		if(data != null && !cityName.equals(data.getString("city"))){
			cityName = data.getString("city");
		}else{
			cityName = null;
		}
		return cityName;
	}
	
	public static JSONObject ipQuery(String IP) throws ClientProtocolException, IOException{
		JSONObject data = null;
    	HttpClient client = HttpClients.createDefault();
        //发送get请求
        HttpGet request = new HttpGet("http://ip.taobao.com/service/getIpInfo.php?ip="+IP);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
        request.setConfig(requestConfig);
        long m = System.currentTimeMillis();
        HttpResponse response = client.execute(request);
        LogHelper.info("ipQuery+++++++++++++++++++++++++"+(System.currentTimeMillis()-m));
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            /**读取服务器返回过来的json字符串数据**/
            String strResult = EntityUtils.toString(response.getEntity());
            data = JSONObject.parseObject(strResult);
        }
		return data;
	}
	
	
	
}
