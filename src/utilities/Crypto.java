package utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {

	public static String hash(String toHash){
		StringBuffer sb = new StringBuffer();
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(toHash.getBytes());
			byte byteHash[] = digest.digest();
	        for (int i = 0; i < byteHash.length; i++) {
	        	sb.append(Integer.toString((byteHash[i] & 0xff) + 0x100, 16).substring(1));
	        }
		}catch (NoSuchAlgorithmException e1){
			e1.printStackTrace();
		}
		return sb.toString();
	}
}
