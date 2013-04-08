package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class FileDigest {

	public static byte[] digestSHA1(String s) {
		byte[] digesta=null;
		try {
			MessageDigest alg = MessageDigest.getInstance("SHA-1");
			alg.update(s.getBytes());
			digesta = alg.digest();
		}catch (NoSuchAlgorithmException e) {
			System.out.println("Error: digestSHA1 failed");
		}
		return digesta;
	}
	
	public static byte[] digestMD5(String s) {
		byte[] digesta=null;
		MessageDigest alg;
		try {
			alg = MessageDigest.getInstance("MD5");
			alg.update(s.getBytes());
			digesta = alg.digest();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error: digestMD5 failed");
		}
		return digesta;
	}
	
	public static boolean isEqual(byte[] b,String s,int algo){
		MessageDigest alg;
		boolean r=false;
		try{
			if(algo==0){
				alg = MessageDigest.getInstance("MD5");
			}
			else{
				alg = MessageDigest.getInstance("SHA-1");
			}
			alg.update(s.getBytes());
			if (MessageDigest.isEqual(b, alg.digest())) {
				r=true;
			} else {
				r=false;
			}
		}catch (NoSuchAlgorithmException e) {
			System.out.println("Error: isEqual failed");
		}
		return r;
	}

	public static String byte2Str(byte[] b)
	{
		String str = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				str = str + "0" + stmp;
			else
				str = str + stmp;
		}
		return str;
	}

}
