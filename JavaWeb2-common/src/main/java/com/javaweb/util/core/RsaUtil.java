package com.javaweb.util.core;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;

import com.javaweb.util.entity.RsaKey;

public class RsaUtil {
	
	/**
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>使用jsencrypt执行OpenSSL的RSA加密，解密</title>
	</head>
	<!--引入jsencrypt.js-->
	<script src="http://passport.cnblogs.com/scripts/jsencrypt.min.js"></script>
	<script type="text/javascript">
	//公钥
	var PUBLIC_KEY = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC29jBsOxfCe2Bihayud47peLs+CeRrCk3m9q2jI362JYAEL9ByUnQGutTZNxIPjsohfm1YM/NOoTS8vFil/fKFpEUt4AVzRu3GNDxiLXzwAt6c+2pUtEN7HagJR0tH3tKM6fDWmpDRB03zjLzpXB0gAkCEQO5z+A4Z2sHIjKP8/wIDAQAB';
	//私钥
	var PRIVATE_KEY = 'MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALb2MGw7F8J7YGKFrK53jul4uz4J5GsKTeb2raMjfrYlgAQv0HJSdAa61Nk3Eg+OyiF+bVgz806hNLy8WKX98oWkRS3gBXNG7cY0PGItfPAC3pz7alS0Q3sdqAlHS0fe0ozp8NaakNEHTfOMvOlcHSACQIRA7nP4DhnawciMo/z/AgMBAAECgYEAk8cJmYGpUTZhxXH+5fQ/hCg15IgM7MAn5/M6kFgtEmX7nmYYUR50BLOHE+M9PT+x15fMLb/YN5xRglgGqhwRc6wyo7Oglz5z7n+sG3QG+MdyPX3lOaav5y2UEtOf6QFkIYpfsmx9HhX2bKEq3VYDtXnKPVDXE9dgW5Houtv4bCECQQDypUuFuUg6eTyD7SBWYPDLXl72UIk/jXJhqQpsO7EaqVognTirUF1bgjgpvFRQGcGTRPcoc6ytHK9Gtxb0VU3JAkEAwQf9LE3CiHgmlkS84evLnMakrq8SSZf0Gwf/pAB8u0G+E5hdWdWbhCfg1x9FN1cu0ZC5PD8OwpSL/Eyi94Q4hwJBANU3EmnrKeDX76ktkSDWBkFuJ5vhoPjLXhsa4yHAQhvffGb0QHupHp/uiVODPcM1Hrg9/cD6BF4Juqs072K8IqECQQCC9RaP7hliWJFlNTEn3L8Al8u2L1Qq1YpopF6NNz9oE0wPIzmB7udZG2zwxWhCCUXcK2FgPC+ZVwo5mwyc+/kVAkArJhrSlZ2MFtgVJx5RYBCi21TEsQsjy+MdDonmEl+k40SbxtMLkMwJ+kcFhrAer378JfqkXvP1abkzQzMAwyUA';
	//使用公钥加密
	var encrypt = new JSEncrypt();
	//encrypt.setPrivateKey('-----BEGIN RSA PRIVATE KEY-----'+PRIVATE_KEY+'-----END RSA PRIVATE KEY-----');
	encrypt.setPublicKey('-----BEGIN PUBLIC KEY-----' + PUBLIC_KEY + '-----END PUBLIC KEY-----');
	var str = {"uid":"1223334","pwd":"asd"};
	var encrypted = encrypt.encrypt(JSON.stringify(str));
	console.log('加密前数据:%o', str);
	console.log('加密后数据:%o', encrypted);
	//使用私钥解密
	var decrypt = new JSEncrypt();
	//decrypt.setPublicKey('-----BEGIN PUBLIC KEY-----' + PUBLIC_KEY + '-----END PUBLIC KEY-----');
	decrypt.setPrivateKey('-----BEGIN RSA PRIVATE KEY-----'+PRIVATE_KEY+'-----END RSA PRIVATE KEY-----');
	var uncrypted = decrypt.decrypt(encrypted);
	console.log('解密后数据:%o', uncrypted);
	</script>
	</html>
	*/
    
	/**
	public static void main(String[] args) throws Exception {
    	KeyPair keyPair = getKeyPair();
    	String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
    	String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
    	System.out.println("公钥:"+publicKey);
    	System.out.println("私钥:"+privateKey);
    	//RSA加密
    	JSONObject jo = new JSONObject();
    	jo.put("uid","1223334");
    	jo.put("pwd","asd");
    	String data = jo.toString();
    	String encryptData = encrypt(data,getPublicKey(publicKey));
    	System.out.println("加密后内容:" + encryptData);
    	//RSA解密
    	String decryptData = decrypt(encryptData,getPrivateKey(privateKey));
    	System.out.println("解密后内容:" + decryptData);
    	//RSA签名
    	String sign = sign(data,getPrivateKey(privateKey));
    	System.out.println("签名结果:" + sign);
    	//RSA验签
    	boolean result = verify(data,getPublicKey(publicKey),sign);
    	System.out.println("验签结果:" + result);
	}
	*/

    private static final int MAX_ENCRYPT_BLOCK = 117;//RSA最大加密明文大小
    private static final int MAX_DECRYPT_BLOCK = 128;//RSA最大解密密文大小
	
    public static RsaKey getRsaKey() {
	RsaKey rsaKey = new RsaKey();
	try{
	    KeyPair keyPair = getKeyPair();
	    PublicKey publicKey = keyPair.getPublic();
	    PrivateKey privateKey = keyPair.getPrivate();
	    String stringPublicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
	    String stringPrivateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
	    rsaKey.setRsaPublicKey(publicKey);
	    rsaKey.setRsaPrivateKey(privateKey);
	    rsaKey.setRsaStringPublicKey(stringPublicKey);
	    rsaKey.setRsaStringPrivateKey(stringPrivateKey);
	}catch(Exception e){
	    //do nothing
	}
	return rsaKey;
    }
	
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }
    
    //获取私钥
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
    	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    	byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
    	PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
    	return keyFactory.generatePrivate(keySpec);
    }
     
    //获取公钥
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }
    
    //签名
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("SHA1withRSA");//MD5withRSA
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }

    //验签
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("SHA1withRSA");//MD5withRSA
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }
    
    //加密（正常是公钥加密私钥解密，要用私钥加密的话，修改下将PublicKey变为PrivateKey即可）
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
    	Cipher cipher = Cipher.getInstance("RSA");//RSA/ECB/NoPadding或RSA/ECB/PKCS1Padding
    	cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    	int inputLen = data.getBytes().length;
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	int offset = 0;
    	byte[] cache;
    	int i = 0;
    	//对数据分段加密
    	while (inputLen - offset > 0) {
		if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
			cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
		} else {
			cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
		}
		out.write(cache, 0, cache.length);
		i++;
		offset = i * MAX_ENCRYPT_BLOCK;
    	}
    	byte[] encryptedData = out.toByteArray();
    	out.close();
    	//获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
    	//加密后的字符串
    	return new String(Base64.encodeBase64String(encryptedData));
	}

    //解密（正常是公钥加密私钥解密，要用公钥解密的话，修改下将PrivateKey变为PublicKey即可）
    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
    	Cipher cipher = Cipher.getInstance("RSA");//RSA/ECB/NoPadding或RSA/ECB/PKCS1Padding
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        //对数据分段解密
        while (inputLen - offset > 0) {
        	if (inputLen - offset > MAX_DECRYPT_BLOCK) {
        		cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
         }
         byte[] decryptedData = out.toByteArray();
         out.close();
         //解密后的内容 
         return new String(decryptedData,"UTF-8");
    }
    
    //加密文件
    public static void encryptFile(String inputPath, String outputPath, String publicKeyStr) throws Exception {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            SecureRandom random = new SecureRandom();
            keygen.init(random);
            SecretKey key = keygen.generateKey();
            PublicKey publicKey = getPublicKey(publicKeyStr);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.WRAP_MODE, publicKey);
            byte[] wrappedKey = cipher.wrap(key);
            DataOutputStream out = new DataOutputStream(new FileOutputStream(outputPath));
            out.writeInt(wrappedKey.length);
            out.write(wrappedKey);
            InputStream in = new FileInputStream(inputPath);
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            crypt(in, out, cipher);
            in.close();
            out.close();
        } catch (Exception e) {
            //do nothing
        }
    }

    //解密文件
    public static void decryptFile(String inputPath, String outputPath, String privateKeyStr) throws Exception {
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(inputPath));
            int length = in.readInt();
            byte[] wrappedKey = new byte[length];
            in.read(wrappedKey, 0, length);
            PrivateKey privateKey = getPrivateKey(privateKeyStr);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.UNWRAP_MODE, privateKey);
            Key key = cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);
            OutputStream out = new FileOutputStream(outputPath);
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            crypt(in, out, cipher);
            in.close();
            out.close();
        } catch (Exception e) {
        	//do nothing
        }
    }
    
    //对数据分段加密解密
    public static void crypt(InputStream in, OutputStream out, Cipher cipher) throws Exception {
        int blockSize = cipher.getBlockSize();
        int outputSize = cipher.getOutputSize(blockSize);
        byte[] inBytes = new byte[blockSize];
        byte[] outBytes = new byte[outputSize];
        int inLength = 0;
        boolean next = true;
        while (next) {
        	inLength = in.read(inBytes);
            if (inLength == blockSize) {
                int outLength = cipher.update(inBytes,0,blockSize,outBytes);
                out.write(outBytes,0,outLength);
            } else {
                next = false;
            }
        }
        if (inLength > 0) {
            outBytes = cipher.doFinal(inBytes,0,inLength);
        } else {
            outBytes = cipher.doFinal();
        }
        out.write(outBytes);
    }
    
}
