package com.mlm.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;

public class MD5 {
	// md5加密字符串  
    public static String md5Value(String value) {  
        try {  
            MessageDigest digest = MessageDigest.getInstance("md5");  
            byte result[] = digest.digest(value.getBytes());  
            BASE64Encoder encoder = new BASE64Encoder();  
            return encoder.encode(result);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
    
//    public static void main(String[] args) {
//    	System.out.println(MD5.md5Value("123456"));
//	}
}
