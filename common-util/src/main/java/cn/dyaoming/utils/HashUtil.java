package cn.dyaoming.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import cn.dyaoming.errors.AppUtilException;

/**
 * <p>
 * hash算法工具类
 * </p>
 * 
 * @author dyaoming
 * @since 2021/02/28
 * @version 0.0.6
 */
public class HashUtil {

	private static final String HASH_MD5 = "MD5";
	private static final String HASH_SHA_1 = "SHA-1";
	private static final String HASH_SHA_256 = "SHA-256";

	public static String md5(String value) {
		try {
			byte[] bytes = encode(value.getBytes(StandardCharsets.UTF_8), HASH_MD5);
			return BaseCodingUtil.toHexString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String sha1(String value) {
		try {
			byte[] bytes = encode(value.getBytes(StandardCharsets.UTF_8), HASH_SHA_1);
			return BaseCodingUtil.toHexString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String sha256(String value) {
		try {
			byte[] bytes = encode(value.getBytes(StandardCharsets.UTF_8), HASH_SHA_256);
			return BaseCodingUtil.toHexString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <p>
	 * hash编码工具类
	 * </p>
	 * 
	 * @param bytes      需要编码的数组
	 * @param encodeType 编码类型
	 * @return 编码结果
	 * @throws AppUtilException 工具类异常
	 */
	public static byte[] encode(byte[] bytes, String encodeType) throws AppUtilException {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(encodeType);
			messageDigest.update(bytes);
			return messageDigest.digest();
		} catch (Exception e) {
			throw AppUtilException.create("hash编码异常，原因是：" + e.getMessage(), e);
		}
	}

}
