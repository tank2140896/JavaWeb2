package com.javaweb.util.core;

import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

import com.javaweb.constant.CommonConstant;

public class HttpUtil {
	
	private static SSLContext sslContext = null;
	
	static{
		try{
			SSLContext sSLContext = new SSLContextBuilder().loadTrustMaterial(null,new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] arg0,String arg1) {
					return true;
				}
			}).setProtocol("TLSv1.2").build();
			sslContext = sSLContext;
		}catch(Exception e){
			
		}
	}
	
	//获取URL连接
	public static URLConnection getURLConnection(String url) throws Exception {
		return new URL(url).openConnection();
	}
	
	//默认GET请求
	public static String defaultGetRequest(String url) throws Exception {
		CloseableHttpClient httpClient = null;
		if(url.contains("https")){
			SSLConnectionSocketFactory sslcsf = new SSLConnectionSocketFactory(sslContext);
			httpClient = HttpClients.custom().setSSLSocketFactory(sslcsf).build();
		}else{
			httpClient = HttpClientBuilder.create().build(); 
		}
		HttpGet get = new HttpGet(url);
		get.setHeader("Content-Type", "application/json;charset=UTF-8");
		//RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
		//post.setConfig(requestConfig);
		HttpResponse httpResponse = httpClient.execute(get);
		HttpEntity httpEntity = httpResponse.getEntity();
		//String response = new ObjectMapper().readValue(httpEntity.getContent(),String.class);
		String response = IOUtils.toString(httpEntity.getContent(),StandardCharsets.UTF_8);
		httpClient.close();
		return response;
	}
	
	//默认POST请求
	public static String defaultPostRequest(String url,String body) throws Exception {
		CloseableHttpClient httpClient = null;
		if(url.contains("https")){
			SSLConnectionSocketFactory sslcsf = new SSLConnectionSocketFactory(sslContext);
			httpClient = HttpClients.custom().setSSLSocketFactory(sslcsf).build();
		}else{
			httpClient = HttpClientBuilder.create().build(); 
		}
		HttpPost post = new HttpPost(url);
		post.setHeader("Content-Type", "application/json;charset=UTF-8");
		//RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
		//post.setConfig(requestConfig);
		StringEntity stringEntity = new StringEntity(body,StandardCharsets.UTF_8);
		post.setEntity(stringEntity);
		HttpResponse httpResponse = httpClient.execute(post);
		HttpEntity httpEntity = httpResponse.getEntity();
		//String response = new ObjectMapper().readValue(httpEntity.getContent(),String.class);
		String response = IOUtils.toString(httpEntity.getContent(),StandardCharsets.UTF_8);
		httpClient.close();
		return response;
	}
	
	//随机生成IP地址
	public static String getRadmonIp(){
		StringBuilder stringBuilder = new StringBuilder();
		for(int i=0;i<4;i++){
			stringBuilder.append(MathUtil.getRandomNumForLCRC(0,255)).append(CommonConstant.DOT);
		}
		String out = stringBuilder.toString();
		out = out.substring(0,out.length()-1);
		return out;
	}

}
