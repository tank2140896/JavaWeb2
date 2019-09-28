package com.javaweb.util.core;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.codec.binary.Hex;

import com.javaweb.constant.SystemConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SecretUtil {
	
	public static final int KEYBORD_USERD_START = 33;//键盘常用字符开始位数
	
	public static final int KEYBORD_USERD_END = 127;//键盘常用字符结束位数
	
	public static final int KEYBORD_USERD_LENGTH = KEYBORD_USERD_END-KEYBORD_USERD_START;//键盘常用字符总长度

	public static final int CHINESE_USED_START = 19968;//中文常用字符开始位数 (int)'\u4e00'
	
	public static final int CHINESE_USED_END = 40869;//中文常用字符结束位数 (int)'\u9fa5'
	
	public static final int CHINESE_USERD_LENGTH = CHINESE_USED_END-CHINESE_USED_START;//中文常用字符总长度
	
	public static final char[] KEYBORD_USED_CHARACTERSET = new char[KEYBORD_USERD_LENGTH];//键盘常用字符数组
	
	public static final char[] CHINESE_USED_CHARACTERSET = new char[KEYBORD_USERD_LENGTH+CHINESE_USERD_LENGTH];//中文常用字符数组
	
	public static final Map<Character,Integer> CHARACTE_KV = new LinkedHashMap<>();//字符数字键值对
	
	public static String CHARACTE_KV_KEY_STRING = "";//字符数字键值对key的字符串
	
	static{
		/** 初始化键盘常用字符数组和中文常用字符数组 start */
		int count = 0;
		for (int i = KEYBORD_USERD_START; i < KEYBORD_USERD_END; i++) {
			KEYBORD_USED_CHARACTERSET[count] = (char)i;
			CHINESE_USED_CHARACTERSET[count] = (char)i;
			count++;
		}
		for (int i = CHINESE_USED_START; i < CHINESE_USED_END; i++) {
			CHINESE_USED_CHARACTERSET[count++] = (char)i;
		}
		/** 初始化键盘常用字符数组和中文常用字符数组 end */
		/** 初始化字符数字键值对 start */
		CHARACTE_KV.put('0', 0);
		CHARACTE_KV.put('1', 1);
		CHARACTE_KV.put('2', 2);
		CHARACTE_KV.put('3', 3);
		CHARACTE_KV.put('4', 4);
		CHARACTE_KV.put('5', 5);
		CHARACTE_KV.put('6', 6);
		CHARACTE_KV.put('7', 7);
		CHARACTE_KV.put('8', 8);
		CHARACTE_KV.put('9', 9);
		CHARACTE_KV.put('A', 10);
		CHARACTE_KV.put('B', 11);
		CHARACTE_KV.put('C', 12);
		CHARACTE_KV.put('D', 13);
		CHARACTE_KV.put('E', 14);
		CHARACTE_KV.put('F', 15);
		/** 初始化字符数字键值对 end */
		for(Character character: CHARACTE_KV.keySet()) {
		    CHARACTE_KV_KEY_STRING+=character;
		}
	}

	//获取UUID
	public static String getRandomUUID() {
		return UUID.randomUUID().toString();
	}
	
	//默认生成随机密码
	public static String defaultGenRandomPass(int passLen) {
		return IntStream.generate(new IntSupplier() {
					public int getAsInt() {
						return (int)(Math.random()*KEYBORD_USERD_LENGTH);
					}
				}).mapToObj(each->Character.toString(KEYBORD_USED_CHARACTERSET[each])).limit(passLen).collect(Collectors.joining());
	}
	
	//默认根据当前时间生成唯一字符串(不适用于大量高并发的情景)
	public static String defaultGenUniqueStr(){
		synchronized ("defaultGenUniqueStr") {
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				
			}
			return DateUtil.getStringDate(DateUtil.DATETIME_PATTERN_TYPE2);
		}
	}
	
	//十进制转化成任意进制数（目前只适用于正整数） 
	public static String decimal2AnyDecimal(long decimal,int arbitraryHexadecimal,char characterSet[]){  
		StringBuilder stringBuilder = new StringBuilder();
		while(decimal!=0){
			stringBuilder.append(characterSet[(int) (decimal%arbitraryHexadecimal)]);
			decimal/=arbitraryHexadecimal;
		}
		return stringBuilder.reverse().toString();
   	} 
    
	//任意进制数转化成十进制（目前只适用于正整数）  
	public static String anyDecimal2decimal(String anyDecimal,int arbitraryHexadecimal,Map<Character,Integer> characteKV){  
		int anyDecimalOfLength = anyDecimal.length();
		long num = 0;
		long ret = 0;
		for(int i=anyDecimalOfLength-1;i>-1;i--){
			int indexValue = characteKV.get(anyDecimal.charAt(i));
			ret += indexValue*Math.pow(arbitraryHexadecimal, num++);
		}
		return String.valueOf(ret);  
   	}
	
	//加密(SHA1(SHA-1)、SHA256(SHA-256)、MD5(MD5)等加密)
	public static String getSecret(String data,String algorithm) throws Exception {
		 MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
		 byte[] hash = messageDigest.digest(data.getBytes("UTF-8"));
		 return Hex.encodeHexString(hash);
	}
	
	//int转byte
	public static byte[] intToByte(int num){
		byte[] bytes = new byte[4];
		bytes[0] = (byte)((num>>24)&0xFF);
		bytes[1] = (byte)((num>>16)&0xFF);
		bytes[2] = (byte)((num>>8)&0xFF);
		bytes[3] = (byte)(num&0xFF);
		return bytes;
	}
	
	//byte转int
	public static int byteToInt(byte[] bytes) {
		return (bytes[0]&0xFF)<<24 | (bytes[1]&0xFF)<<16 | (bytes[2]&0xFF)<<8 | (bytes[3]&0xFF);
	}
	
	//生成JWT
	public static String createJwtToken(Map<String,Object> map,Date date){
		return Jwts.builder().setSubject(SystemConstant.PROJECT_NAME)
							 .setClaims(map)
							 .setIssuedAt(new Date())
		              	     .setExpiration(date/*new Date(System.currentTimeMillis()+SystemConstant.SYSTEM_DEFAULT_JWT_TIME_OUT*60*1000)*/)
		              	     .signWith(SignatureAlgorithm.HS256,SystemConstant.DEFAULT_SECURITY_KEY)
		              	     .compact();
	}
	
	//解析JWT
	public static Claims analyseJwtToken(String token){
		return Jwts.parser().setSigningKey(SystemConstant.DEFAULT_SECURITY_KEY)
				            .parseClaimsJws(token)
				            .getBody();
	}
	
	//16进制字符串转byte数组
    public static byte[] hexStringToBytes(String hexString) {   
        if (hexString == null || hexString.equals("")) {   
            return null;   
        }   
        hexString = hexString.toUpperCase();   
        int length = hexString.length() / 2;   
        char[] hexChars = hexString.toCharArray();   
        byte[] d = new byte[length];   
        for (int i = 0; i < length; i++) {   
            int pos = i * 2;   
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));   
        }   
        return d;   
    } 
    
    //字符转byte
    public static byte charToByte(char c) {   
        return (byte) CHARACTE_KV_KEY_STRING.indexOf(c);   
    } 
    
    //byte数组转16进制字符串
    public static String bytesToHexString(byte[] src){   
        StringBuilder stringBuilder = new StringBuilder("");   
        if (src == null || src.length <= 0) {   
            return null;   
        }   
        for (int i = 0; i < src.length; i++) {   
            int v = src[i] & 0xFF;   
            String hv = Integer.toHexString(v);   
            if (hv.length() < 2) {   
                stringBuilder.append(0);   
            }   
            stringBuilder.append(hv);   
        }   
        return stringBuilder.toString();   
    }
    
    //字符串转16进制字符串
    public static String str2HexStr(String str) {    
        char[] chars = CHARACTE_KV_KEY_STRING.toCharArray();    
        StringBuilder sb = new StringBuilder("");  
        byte[] bs = str.getBytes();    
        int bit;    
        for (int i = 0; i < bs.length; i++) {    
            bit = (bs[i] & 0x0f0) >> 4;    
            sb.append(chars[bit]);    
            bit = bs[i] & 0x0f;    
            sb.append(chars[bit]);    
        }    
        return sb.toString();    
    }
    
    //16进制字符串转字符串
    public static String hexStr2Str(String hexStr) {  
       ByteArrayOutputStream baos = new ByteArrayOutputStream(hexStr.length() / 2);  
       //将每2位16进制整数组装成一个字节  
       for (int i = 0; i < hexStr.length(); i += 2) {
           baos.write((CHARACTE_KV_KEY_STRING.indexOf(hexStr.charAt(i)) << 4 | CHARACTE_KV_KEY_STRING.indexOf(hexStr.charAt(i + 1))));  
       } 
       return new String(baos.toByteArray());  
    }
    	
}
