package com.javaweb.web.eo;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.util.core.DateUtil;
import com.javaweb.util.core.DesUtil;
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
		String nonce = request.getHeader("nonce");//长度为24位的随机小写字母和数字的组合
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
			Duration duration = DateUtil.getDuration(DateUtil.getDateTime(currentTime,DateUtil.DATETIME_PATTERN_TYPE1),LocalDateTime.now());//日期校验
			if(duration.getSeconds()>60*5){//客户端时间与本地时间间隔（算上各种延迟）不应该超过300秒（5分钟）
				return false;
			}
			nonce = RsaUtil.decrypt(nonce,RsaUtil.getPrivateKey(tokenData.getRsaPrivateKeyOfBackend()));
			if(nonce.length()!=24){//随机数长度判断
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
				//RSA结合3DES（推荐），原因参考：https://ask.csdn.net/questions/763621和https://github.com/travist/jsencrypt/issues/137
				//1.用3DES解密code
				String d1 = DesUtil.decrypt(this.code,SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(nonce.getBytes("UTF8")))); 
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
