package cn.dyaoming.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.dyaoming.errors.AppUtilException;

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
	
	private static final String HMAC_MD5 = "HmacMD5";
	private static final String HMAC_SHA_1 = "HmacSHA1";
	private static final String HMAC_SHA_256 = "HmacSHA256";

	public static String md5(String message, String key) {
		String outPut = null;
		try {
			byte[] bytes = encode(message.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8),
					HMAC_MD5);
			outPut = BaseCodingUtil.toHexString(bytes);
		} catch (Exception e) {
			LOGGER.warn("Error HmacSHA256========" + e.getMessage());
		}
		return outPut;
	}

	public static String sha1(String message, String key) {
		String outPut = null;
		try {
			byte[] bytes = encode(message.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8),
					HMAC_SHA_1);
			outPut = BaseCodingUtil.toHexString(bytes);
		} catch (Exception e) {
			LOGGER.warn("Error HmacSHA256========" + e.getMessage());
		}
		return outPut;
	}

	public static String sha256(String message, String encryptKey) {
		String outPut = null;
		try {
			byte[] bytes = encode(message.getBytes(StandardCharsets.UTF_8), encryptKey.getBytes(StandardCharsets.UTF_8),
					HMAC_SHA_256);
			outPut = BaseCodingUtil.toHexString(bytes);
		} catch (Exception e) {
			LOGGER.warn("Error HmacSHA256========" + e.getMessage());
		}
		return outPut;
	}

	/**
	 * <p>hmac编码方法</p>
	 *
	 * @param messageBytes 加密文本字节组
	 * @param encryptKeyBytes
	 * @param encodeType
	 * @return
	 * @throws AppUtilException
	 */
	public static byte[] encode(byte[] messageBytes, byte[] encryptKeyBytes, String encodeType) throws AppUtilException {
		try {
			Mac hmacMac = Mac.getInstance(encodeType);
			SecretKeySpec secretKey = new SecretKeySpec(encryptKeyBytes, encodeType);
			hmacMac.init(secretKey);
			return hmacMac.doFinal(messageBytes);
		} catch (Exception e) {
			throw AppUtilException.create("","",e);
		}
	}
}
