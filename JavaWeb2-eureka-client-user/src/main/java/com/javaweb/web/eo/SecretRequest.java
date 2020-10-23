package com.javaweb.web.eo;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.constant.CommonConstant;
import com.javaweb.util.core.AesDesUtil;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.RsaUtil;
import com.javaweb.util.core.SecretUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecretRequest {

	private String code;
	
	private String sign;
	
	public boolean rsaCheckPass(HttpServletRequest request,TokenData tokenData,boolean isAuthController,boolean needToken){
		String isAuth = request.getHeader("isAuth");//0（不加密）；1（加密）
		String token = request.getHeader("token");//token
		String currentTime = request.getHeader("currentTime");//格式为yyyyMMddHHmmss
		String nonce = request.getHeader("nonce");//长度为24位的随机小写字母和数字的组合
		String thatCode = request.getHeader("code");//post和put的不是放在header里的
		String thatSign = request.getHeader("sign");//post和put的不是放在header里的
		String requestContentType = request.getHeader("requestContentType");//1（application/json）；2（multipart/form-data）；3（application/x-www-form-urlencoded）
		if((!"1".equals(requestContentType))&&(!"2".equals(requestContentType))&&(!"3".equals(requestContentType))){
			return false;
		}
		if(("0".equals(isAuth))&&(!isAuthController)){//只有此特殊情况下（前后端都需要特殊处理）不需要加密处理，其它一律认为需要加密处理
			return true;
		}
		try{
			if(needToken){
				if(!(token.equals(tokenData.getToken()))){
					return false;
				}
			}
			Duration duration = DateUtil.getDuration(DateUtil.getDateTime(currentTime,DateUtil.DATETIME_PATTERN_TYPE1),LocalDateTime.now());//日期校验
			if(duration.getSeconds()>60*5){//客户端时间与本地时间间隔（算上各种延迟）不应该超过300秒（5分钟）
				return false;
			}
			nonce = RsaUtil.decrypt(nonce,RsaUtil.getPrivateKey(tokenData.getRsaPrivateKeyOfBackend()));
			if(nonce.length()!=24){//随机数长度判断
				return false;
			}
			if("GET".equals(request.getMethod().toUpperCase())||"DELETE".equals(request.getMethod().toUpperCase())){
				if(thatCode==null||CommonConstant.EMPTY_VALUE.equals(thatCode.trim())){
					return false;
				}
				if(thatSign==null||CommonConstant.EMPTY_VALUE.equals(thatSign.trim())){
					return false;
				}
				this.code = thatCode;
				this.sign = thatSign;
			}
			return codeSignCheck(tokenData,currentTime,nonce);
		}catch(Exception e){
			return false;
		}
	}
	
	private boolean codeSignCheck(TokenData tokenData,String currentTime,String nonce) throws Exception {
		//RSA结合DES（推荐），原因参考：https://ask.csdn.net/questions/763621和https://github.com/travist/jsencrypt/issues/137
		//1.用DES解密code
		String d1 = AesDesUtil.decryptDes(this.code,SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(nonce.getBytes("UTF8")))); 
		//1.用后端私钥解密code
		//String d1 = RsaUtil.decrypt(this.code,RsaUtil.getPrivateKey(tokenData.getRsaPrivateKeyOfBackend()));
		//2.用前端公钥验签
		boolean pass = RsaUtil.verify(SecretUtil.getSecret(currentTime+","+nonce+","+d1,"MD5"),RsaUtil.getPublicKey(tokenData.getRsaPublicKeyOfFrontend()),this.sign);
		if(pass){
			this.code = d1;
			return true;
		}else{
			return false;
		}
	}
	
	public <T> T getEntity(Class<T> c){
		try {
			//System.out.println(this.code);
			//objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
			return new ObjectMapper().readValue(this.code,c);
		} catch (Exception e) {
			return null;
		} 
	}
	
}
