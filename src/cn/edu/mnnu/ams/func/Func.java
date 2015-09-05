package cn.edu.mnnu.ams.func;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Func {
	public String md5(String plaintext) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		char[] charArray = plaintext.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer ciphertext = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) ciphertext.append("0");
			ciphertext.append(Integer.toHexString(val));
		}
		return ciphertext.toString();
	}
}
