package com.javaweb.web.eo;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.util.core.RsaUtil;
import com.javaweb.util.core.SecretUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RsaRequest {

	private String code;
	
	private String sign;
	
	public boolean rsaCheckPass(HttpServletRequest request,TokenData tokenData,boolean isAuthController,boolean needToken){
		String isAuth = request.getHeader("isAuth");//0（不加密）；1（加密）
		String token = request.getHeader("token");
		String currentTime = request.getHeader("currentTime");//格式为yyyyMMddHHmmss
		String nonce = request.getHeader("nonce");//长度为10位的随机小写字母和数字的组合
		String thatSign = request.getHeader("sign");//post和put的不是放在header里的
		if(("0".equals(isAuth))&&(!isAuthController)){//只有此特殊情况下不需要加密处理，其它一律认为需要加密处理
			return true;
		}
		try{
			if(needToken){
				if(!(token.equals(tokenData.getToken()))){
					return false;
				}
			}
			new SimpleDateFormat("yyyyMMddHHmmss").parse(currentTime);//日期校验，判断传的是不是日期
			if(nonce.length()!=10){//随机数长度判断
				return false;
			}
			if("GET".equals(request.getMethod().toUpperCase())||"DELETE".equals(request.getMethod().toUpperCase())){
				//1.用后端私钥解密sign
				String d1 = RsaUtil.decrypt(thatSign,RsaUtil.getPrivateKey(tokenData.getRsaPrivateKeyOfBackend()));
				//2.计算MD5(currentTime+","+nonce)
				String d2 = SecretUtil.getSecret(currentTime+","+nonce,"MD5");
				//3.判断两个值是否一致
				return d1.equals(d2);
			}else{
				//1.用后端私钥解密code
				String d1 = RsaUtil.decrypt(this.code,RsaUtil.getPrivateKey(tokenData.getRsaPrivateKeyOfBackend()));
				//2.用前端公钥验签
				boolean pass = RsaUtil.verify(SecretUtil.getSecret(currentTime+","+nonce+","+d1,"MD5"),RsaUtil.getPublicKey(tokenData.getRsaPublicKeyOfFrontend()),this.sign);
				if(pass){
					this.code = d1;
					return true;
				}else{
					return false;
				}
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public <T> T getEntity(Class<T> c){
		try {
			return new ObjectMapper().readValue(this.code,c);
		} catch (Exception e) {
			return null;
		} 
	}
	
}
