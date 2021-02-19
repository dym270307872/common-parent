package cn.dyaoming.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * hmac加密工具类
 * </p>
 * 
 * @author dym
 * @since 2021/02/18
 * @version 0.0.6
 */
public class HmacUtil {
	private final static Logger LOGGER = LoggerFactory.getLogger(HmacUtil.class);

	private static final String HMACSHA256 = "HmacSHA256";

	public static String sha256(String message, String key) {
		String outPut = null;
		try {
			Mac sha256Hmac = Mac.getInstance(HMACSHA256);
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), HMACSHA256);
			sha256Hmac.init(secretKey);
			byte[] bytes = sha256Hmac.doFinal(message.getBytes());
			outPut = EncodingUtil.toHexString(bytes);
		} catch (Exception e) {
			LOGGER.warn("Error HmacSHA256========" + e.getMessage());
		}
		return outPut;
	}
	
}
