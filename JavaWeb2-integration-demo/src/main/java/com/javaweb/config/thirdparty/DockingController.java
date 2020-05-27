package com.javaweb.config.thirdparty;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.util.core.DesUtil;

import lombok.Getter;
import lombok.Setter;

//接口对接（别人来请求我们）
@RestController
public class DockingController {

	//step1:给予对方一个key和appId
	
	//step2:通过key获取token
	public String getTokenBykey(@RequestParam(value="key") String key,@RequestParam(value="appId") String appId){
		//step2.1:可以做的全面点，存入表中，校验下给予的这个key是否正确、是否是指定的使用厂商、使用期限是否到期、是否被禁用等
		//step2.2:将token存入如redis中，给其一个存续期
		return "token";//这个token我们约定是一个3DES的key值
	}
	
	//step3:请求方将body参数用3DES加密传给我们，我们解密并返回数据给请求方
	public String returnResult(@RequestBody GiveMeRequestBody giveMeRequestBody) throws Exception {
		String code = giveMeRequestBody.getCode();
		code = DesUtil.decrypt(code,null/*秘钥*/);
		Object obj = new ObjectMapper().readValue(code,Object.class/*某个实体类*/);
		return obj.toString();//返回调用方需要的数据
	}
	
}

@Getter
@Setter
class GiveMeRequestBody {
	private String code;
}