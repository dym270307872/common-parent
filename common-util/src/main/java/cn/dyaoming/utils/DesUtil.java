/*
 * @(#)DesUtil.java 创建于2017-02-14
 *
 * Copyright (c) 2017-2019 by Zhkj.
 *
 */
package cn.dyaoming.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 * <p>
 * Des加密解密 工具类
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-04-21
 * @version 0.0.1
 */
public class DesUtil {

    private final static Logger logger = LoggerFactory.getLogger(DesUtil.class);


	private static byte[]	iv		= { (byte) 0x26, (byte) 0x39, (byte) 0x15,
			(byte) 0x20, (byte) 0x41, (byte) 0xAC, (byte) 0x6D, (byte) 0xEF };



	/**
	 * 功能描述：DES解密 方法。
	 * 
	 *
	 * @param decryptString byte[]类型 要解密的数据（需要使用Base64将字符串转换成byte[]）
	 * @param decryptKey String类型 解密时使用的KEY
	 * @return String类型 解密后结果
	 */
	public static byte[] decrypt(byte[] decryptString, String decryptKey) {
		try {
			// DES算法要求有一个可信任的随机数源
			// SecureRandom random = new SecureRandom();
			// 创建一个DESKeySpec对象
			DESKeySpec desKey = new DESKeySpec(decryptKey.getBytes("UTF-8"));
			// 创建一个密匙工厂
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// 将DESKeySpec对象转换成SecretKey对象
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

			// 偏移量
			IvParameterSpec iv = new IvParameterSpec(decryptKey.getBytes("UTF-8"));

			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, securekey, iv);

			// IvParameterSpec ivp = new IvParameterSpec(iv);

			// 真正开始解密操作
			return cipher.doFinal(decryptString);

		} catch(Exception e) {
			return null;
		}
	}



	/**
	 * 功能描述：DES加密 方法。
	 * 
	 *
	 * @param encryptString String类型 要加密的数据
	 * @param encryptKey String类型 加密时使用的KEY
	 * @return byte[]类型 加密后结果
	 */
	public static byte[] encrypt(String encryptString, String encryptKey) {
		try {
			// DES算法要求有一个可信任的随机数源
			// SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(encryptKey.getBytes("UTF-8"));
			// DESKeySpec desKey = new DESKeySpec(encryptKey.getBytes());

			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);

			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

			// 偏移量
			IvParameterSpec iv = new IvParameterSpec(encryptKey.getBytes("UTF-8"));

			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);

			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(encryptString.getBytes());
			// return Base64.encode(cipher.doFinal(encryptString.getBytes()));

		} catch(Exception e) {
			// e.printStackTrace();
			return null;
		}

	}

}