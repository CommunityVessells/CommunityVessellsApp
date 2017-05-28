package org.ffcc.communityVessells.app.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptMD5 {
	private static MessageDigest digest;

	public static String encrypt(String password){
		try{
			//instantiate MD5 algorithm
			digest=MessageDigest.getInstance("MD5");
			//get the bytes of the password and calculate the digested bytes
			byte[] passwordBytes=password.getBytes();
			digest.reset();
			byte[] digestedBytes = digest.digest(passwordBytes);
			StringBuffer output = new StringBuffer();
			for(int i=0;i<digestedBytes.length;i++){
				/* digested[i] & 0xff ensures that only the 8 least significant 
				 * bits of digested[i] can be non-zero */
				output.append(Integer.toHexString(0xff & digestedBytes[i]));
			}
			return output.toString();
		}
		catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean authenticateUser(String input, String hash){
		//check if the input password is stored as a hash in the database
		return EncryptMD5.encrypt(input).equals(hash);
	}
}
