package com.yinfeixing.utils.httpclient;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;

public class HttpclientManager {
    private static final String HTTP = "http";

    private static final String HTTPS = "https";

    private static PoolingHttpClientConnectionManager httpClientConnectionManager = null;
    
    private static void setHttpClientConnectionManager(){
    	try {
            if(httpClientConnectionManager == null){
            	SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, (X509Certificate[] arg0, String arg1) -> true).build();
                // 设置协议http和https对应的处理socket链接工厂的对象
                Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                        .register(HTTP, PlainConnectionSocketFactory.INSTANCE)
                        .register(HTTPS, new SSLConnectionSocketFactory(sslContext)).build();
            	synchronized (HttpclientManager.class) {
            		if(httpClientConnectionManager == null){
            			httpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            			httpClientConnectionManager.setMaxTotal(200);
            			httpClientConnectionManager.setDefaultMaxPerRoute(10);
            		}
				}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public static CloseableHttpClient getHttpClient() {
		setHttpClientConnectionManager();
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(httpClientConnectionManager)
				.setConnectionManagerShared(true).build();
		return httpClient;
	}
}
