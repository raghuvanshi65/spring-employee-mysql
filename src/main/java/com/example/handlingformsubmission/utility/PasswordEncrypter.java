package com.example.handlingformsubmission.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordEncrypter {
	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordEncrypter.class);
	
	public String hashPassword(String pass) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes()); 
			byte[] bytes = md.digest();  
			StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));    
            return s.toString();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("No such Algorithm exist, password encryption encountered a bug");
			return null;
		}
	}
	
	public boolean compare(String hashed , String newPass) {
		return hashed.equals(hashPassword(newPass));
	}
}
