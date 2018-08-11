package com.javaweb.util.core;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.codec.binary.Hex;

public class SecretUtil {
	
	private static final int KEYBORD_USERD_START = 33;//键盘常用字符开始位数
	
	private static final int KEYBORD_USERD_END = 127;//键盘常用字符结束位数
	
	private static final int KEYBORD_USERD_LENGTH = KEYBORD_USERD_END-KEYBORD_USERD_START;//键盘常用字符总长度

	private static final int CHINESE_USED_START = 19968;//中文常用字符开始位数 (int)'\u4e00'
	
	private static final int CHINESE_USED_END = 40869;//中文常用字符结束位数 (int)'\u9fa5'
	
	private static final int CHINESE_USERD_LENGTH = CHINESE_USED_END-CHINESE_USED_START;//中文常用字符总长度
	
	private static final char[] KEYBORD_USED_CHARACTERSET = new char[KEYBORD_USERD_LENGTH];//键盘常用字符数组
	
	private static final char[] CHINESE_USED_CHARACTERSET = new char[KEYBORD_USERD_LENGTH+CHINESE_USERD_LENGTH];//中文常用字符数组
	
	private static final Map<Character,Integer> CHARACTE_KV = new HashMap<>();//字符数字键值对

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
	
	//默认根据当前时间生成唯一字符串
	public static String defaultGenUniqueStr(){
		synchronized ("defaultGenUniqueStr") {
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				
			}
			return  DateUtil.getStringDate(DateUtil.DATETIME_PATTERN_TYPE2);
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
	
	//sha256加密
	public static String getSha256(String string) throws Exception {
		 MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		 byte[] hash = messageDigest.digest(string.getBytes("UTF-8"));
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
		return (bytes[0]&0xFF)<<24
			 | (bytes[1]&0xFF)<<16
			 | (bytes[2]&0xFF)<<8
			 | (bytes[3]&0xFF);
	}
    	
}
