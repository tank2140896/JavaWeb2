package com.javaweb.util.core;

import java.security.Key;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;

/*
String getKey = "efv1k81f6jzrwi4c1485236l";//24位key
SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");  
Key key = keyFactory.generateSecret(new DESedeKeySpec(getKey.getBytes("UTF8")));
前端参考： https://www.cnblogs.com/claireyuancy/p/6805790.html 
其中两行重要代码要修改：
var encrypted = CryptoJS.TripleDES.encrypt   
iv:CryptoJS.enc.Utf8.parse('01234567'),  
*/
public class DesUtil {
	
    //加密String明文输入，String密文输出  
    public static String encrypt(String str,Key key) throws Exception {  
    	byte[] secretByte = encryptDesCode(str.getBytes("UTF8"),key);  
        Encoder encoder = Base64.getEncoder();
        return new String(encoder.encode(secretByte),"UTF-8");  
    }
    
    //DES加密  
    public static byte[] encryptDesCode(byte[] b,Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");  
        cipher.init(Cipher.ENCRYPT_MODE,key);  
        return cipher.doFinal(b);  
    }
    
    //解密 String密文输入,String明文输出  
    public static String decrypt(String str,Key key) throws Exception {  
    	Decoder decoder = Base64.getDecoder();  
        byte[] b = decoder.decode(str);  
        return new String(decryptDesCode(b,key),"UTF8");  
    }
    
    //DES解密    
    private static byte[] decryptDesCode(byte[] b,Key key) throws Exception {  
    	Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");  
    	cipher.init(Cipher.DECRYPT_MODE,key);  
    	return cipher.doFinal(b);  
    }
    
}
