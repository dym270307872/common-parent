package cn.dyaoming.utils;

import java.util.Base64;
public class EncodingUtil {

	
	public static String toHexString(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		String tempHex;
		for (byte data : bytes) {
			tempHex = Integer.toHexString(0xff & data);
			if (tempHex.length() == 1) {
				stringBuffer.append('0');
			}
			stringBuffer.append(tempHex);
		}
		tempHex = null;
		return stringBuffer.toString();
	}
	
	public static String encodeBase16(Object obj) {
		byte[] bytes = transformObject2Bytes(obj);
		return toHexString(bytes);
	}

	private static byte[] transformObject2Bytes(Object obj) {
		byte[] bytes = null;
		if (obj instanceof byte[]) {
			bytes = (byte[]) obj;
		} else {
			bytes = SerializeUtil.serialize(obj);
		}
		return bytes;
	}
	
	public static String encodeBase32(Object obj) {
		byte[] bytes = transformObject2Bytes(obj);
		return "";
	}
	
	
	public static String encodeBase64(Object obj) {
		byte[] bytes = transformObject2Bytes(obj);
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	
	public static byte[] decodeFromBase64(String encodeValue) {
		return Base64.getDecoder().decode(encodeValue);
	}

}
