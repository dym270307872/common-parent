package cn.dyaoming.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashUtil {

	private static final String MD5 = "MD5";
	private static final String SHA1 = "SHA-1";
	private static final String SHA256 = "SHA-256";

	public static String md5Encode(String value) {
		try {
			
			return EncodingUtil.toHexString(encode(value.getBytes(StandardCharsets.UTF_8), MD5));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String sha1Encode(String value) {
		try {
			return EncodingUtil.toHexString(encode(value.getBytes(StandardCharsets.UTF_8), SHA1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String sha256Encode(String value) {
		try {
			return EncodingUtil.toHexString(encode(value.getBytes(StandardCharsets.UTF_8), SHA256));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] encode(byte[] bytes, String encodeType) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(encodeType);

			messageDigest.update(bytes);

			return messageDigest.digest();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new byte[0];
	}
	
}
