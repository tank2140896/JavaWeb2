package com.javaweb.config.thirdparty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;

//接口对接（别人来请求我们）
@RestController
public class DockingController {

	//step1:给予对方key、RSA公钥、UUID
	
	//step2:获取token（header传：1、RSA公钥加密key；2、time[yyyyMMddHHmmss]；3、MD5(time+","+UUID)）
	@GetMapping("/getToken")
	public String getToken(){
		//step2.1:可以做的全面点，存入表中，校验下给予的这个key（没有经过RSA公钥加密）是否正确、是否是指定的使用厂商、使用期限是否到期、是否被禁用等
		//step2.2:key作为键，DES(新的UUID+","+新的时间)作为value存入如redis中，给其一个存续期
		return "（token是key对应的value值）和（DES的秘钥）";
	}
	
	@PostMapping("/returnResult")
	//step3:请求接口（1、请求体：{'code':DES(body),'time':'yyyyMMddHHmmss,'sign':RSA公钥加密time'}；2、token）
	public String returnResult(@RequestBody GiveMeRequestBody giveMeRequestBody) throws Exception {
		return "调用方需要的数据";//返回调用方需要的数据
	}
	
}

@Getter
@Setter
class GiveMeRequestBody {
	private String code;
	private String time;
	private String sign;
}
