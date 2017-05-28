package org.ffcc.communityVessells.app.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptMD5 {
	private static MessageDigest digest;

	public static String encrypt(String password){
		try{
			//instantiate MD5 algorithm
			digest=MessageDigest.getInstance("MD5");
			//get the bytes of the password and calculated the digested bytes
			byte[] passwordBytes=password.getBytes();
			digest.reset();
			byte[] digestedBytes = digest.digest(passwordBytes);
			StringBuffer output = new StringBuffer();
			for(int i=0;i<digestedBytes.length;i++){
				output.append(Integer.toHexString(0xff & digestedBytes[i]));
			}
			return output.toString();
		}
		catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return null;
	}
}
