package cn.dyaoming.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>
 * AES加解密工具类
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-04-09
 * @version 0.0.1
 */
public class AesUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(AesUtil.class);
    
	/**
	 *  默认秘钥
	 */
	private final static String DEFAULT_KEY = "e67M5gbCL9cZs0pS";



	/**
	 * 功能描述：AES加密 方法。
	 *
	 * @param encryptString String类型 要加密的数据
	 * @return byte[]类型 加密后结果
	 */
	public static byte[] encrypt(String encryptString) {
		try {
			String encryptKey = DEFAULT_KEY;

			byte[] raw = encryptKey.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			// "算法/模式/补码方式"
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			IvParameterSpec iv = new IvParameterSpec(encryptKey.getBytes("UTF-8"));
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(encryptString.getBytes("UTF-8"));

			// 此处不使用BASE64做转码功能。
			return encrypted;

		} catch(Exception e) {
		    LOGGER.warn("AES加密失败",e);
		}
		return null;

	}



	/**
	 * <p>
	 * 功能描述：AES加密 默认方法。
	 * 创建人： DYM
	 * </P>
	 *
	 * @param encryptByte byte[]类型 要解密的数据（需要使用Base64将字符串转换成byte[]）
	 * @return String类型 解密后结果
	 */
	public static byte[] encrypt(byte[] encryptByte) {
		try {
			String encryptKey = DEFAULT_KEY;

			byte[] raw = encryptKey.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			// "算法/模式/补码方式"
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			IvParameterSpec iv = new IvParameterSpec(raw);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(encryptByte);

			// 此处不使用BASE64做转码功能。
			return encrypted;

		} catch(Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();

			return null;
		}

	}



	/**
	 * <p>
	 * 功能描述：AES解密 默认方法。
	 * 创建人： DYM
	 * </P>
	 *
	 * @param decryptByte byte[]类型 要解密的数据（需要使用Base64将字符串转换成byte[]）
	 * @return String类型 解密后结果
	 */
	public static byte[] decrypt(byte[] decryptByte) {
		try {
			String decryptKey = DEFAULT_KEY;

			byte[] raw = decryptKey.getBytes("UTF-8");

			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(raw);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			try {
				byte[] original = cipher.doFinal(decryptByte);
				return original;

			} catch(Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch(Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();

			return null;
		}
	}






	/**
	 * 功能描述：AES加密 方法。
	 * 
	 * 创建时间：2016-03-17
	 * 
	 * 创建人： 于昌亮
	 * 
	 * @param encryptString String类型 要加密的数据
	 * @param encryptKey String类型 加密时使用的KEY
	 * @return byte[]类型 加密后结果
	 */
	public static byte[] encrypt(String encryptString, String encryptKey) {
		try {
			if (encryptKey == null) {
				System.out.print("Key为空null");
				return null;
			}

			// 判断Key是否为16位
			if (encryptKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}

			byte[] raw = encryptKey.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			// "算法/模式/补码方式"
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			IvParameterSpec iv = new IvParameterSpec(encryptKey.getBytes("UTF-8"));
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(encryptString.getBytes("UTF-8"));

			// 此处不使用BASE64做转码功能。
			return encrypted;

		} catch(Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();

			return null;
		}

	}





	/**
	 * 功能描述：AES解密 方法。
	 * 
	 * 创建时间：2016-03-17
	 * 
	 * 创建人： 于昌亮
	 * 
	 * @param decryptString byte[]类型 要解密的数据（需要使用Base64将字符串转换成byte[]）
	 * @param decryptKey String类型 解密时使用的KEY
	 * @return String类型 解密后结果
	 */
	public static byte[] decrypt(byte[] decryptString, String decryptKey) {
		try {
			// 判断Key是否正确
			if (decryptKey == null) {
				System.out.print("Key为空null");
				return null;
			}

			// 判断Key是否为16位
			if (decryptKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}

			// byte[] raw = decryptKey.getBytes("ASCII");
			byte[] raw = decryptKey.getBytes("UTF-8");

			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(decryptKey.getBytes("UTF-8"));
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			try {
				byte[] original = cipher.doFinal(decryptString);
				return original;

			} catch(Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch(Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();

			return null;
		}
	}

}
