/*
 * @(#)AesUtil.java 创建于2017-02-14
 * 
 * Copyright (c) 2017-2019 by Zhkj. 
 *
 */
package cn.dyaoming.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
*java 中AES算法的使用：
*密钥长度：128，192， 256 默认使用128
*工作模式：ECB,CBC,PCBC,CTR,CTS,CFB,CFB8至CFB128,OFB,OFB8至OFB128
*填充方式：Nopadding, PKCS5Padding,ISO10126Padding
*备注：java 6若使用256位密钥需要无政策限制条件
*此例给出的AES加密方式采用AES-128-EBC加密模式，PKCS5Padding补码，key需要为16位
*此例给出的AES加密方式采用AES-128-CBC加密模式，PKCS5Padding补码，key需要为16位 偏移向量与key一致
*/

/**
 * 
 * 类名称：AesUtil
 * <P/>
 * 类描述： AES加密解密 工具类。
 * <P/>
 * 创建时间：2017-02-14
 * <P/>
 * 创建人： 于昌亮
 * <P/>
 * 修改人：无
 * <P/>
 * 修改时间：无
 * <P/>
 * 修改备注：无
 * <P/>
 * 版本：V1.0
 * 
 */
public class AesUtil
{
		
	public AesUtil()
	{
		
	}

	

	
	
	/**
	 * 
	 * 功能描述：AES加密  方法。
	 * <P/>
	 * 创建时间：2016-03-17
	 * <P/>
	 * 创建人： 于昌亮
	 * 
	 * @param encryptString String类型 要加密的数据
	 * 
	 * @param encryptKey String类型 加密时使用的KEY
	 * 
	 * @return byte[]类型 加密后结果
	 * 
	 */
	public static byte[] encrypt_ECB模式(String encryptString, String encryptKey)
	{
		try
		{
				if (encryptKey == null)
				{
					System.out.print("Key为空null");
					return null;
				}
		
				// 判断Key是否为16位
				if (encryptKey.length() != 16)
				{
					System.out.print("Key长度不是16位");
					return null;
				}
		
				byte[] raw = encryptKey.getBytes("UTF-8");
				SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
				// "算法/模式/补码方式"
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
				byte[] encrypted = cipher.doFinal(encryptString.getBytes("UTF-8"));
		
				// 此处不使用BASE64做转码功能。
				return encrypted;
				
		}catch (Exception e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
			
			return null;
		}
		
	}

	
	
	/**
	 * 
	 * 功能描述：AES加密  方法。
	 * <P/>
	 * 创建时间：2016-03-17
	 * <P/>
	 * 创建人： 于昌亮
	 * 
	 * @param encryptString String类型 要加密的数据
	 * 
	 * @param encryptKey String类型 加密时使用的KEY
	 * 
	 * @return byte[]类型 加密后结果
	 * 
	 */
	public static byte[] encrypt(String encryptString, String encryptKey)
	{
		try
		{
				if (encryptKey == null)
				{
					System.out.print("Key为空null");
					return null;
				}
		
				// 判断Key是否为16位
				if (encryptKey.length() != 16)
				{
					System.out.print("Key长度不是16位");
					return null;
				}
		
				byte[] raw = encryptKey.getBytes("UTF-8");
				SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
				// "算法/模式/补码方式"
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
				// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
				IvParameterSpec iv = new IvParameterSpec(encryptKey.getBytes("UTF-8"));
				cipher.init(Cipher.ENCRYPT_MODE, skeySpec,iv);
				byte[] encrypted = cipher.doFinal(encryptString.getBytes("UTF-8"));
		
				// 此处不使用BASE64做转码功能。
				return encrypted;
				
		}catch (Exception e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	
	
	/**
	 * 
	 * 功能描述：AES解密  方法。
	 * <P/>
	 * 创建时间：2016-03-17
	 * <P/>
	 * 创建人： 于昌亮
	 * 
	 * @param decryptString byte[]类型 要解密的数据（需要使用Base64将字符串转换成byte[]）
	 * 
	 * @param decryptKey String类型 解密时使用的KEY
	 * 
	 * @return String类型 解密后结果
	 * 
	 */
	public static byte[] decrypt_ECB模式(byte[] decryptString, String decryptKey)
	{
		try
		{
			//判断Key是否正确
			if (decryptKey == null)
			{
				System.out.print("Key为空null");
				return null;
			}

			// 判断Key是否为16位
			if (decryptKey.length() != 16)
			{
				System.out.print("Key长度不是16位");
				return null;
			}
			
			byte[] raw = decryptKey.getBytes("UTF-8");

			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);

			try
			{
				byte[] original = cipher.doFinal(decryptString);
				return original;

			} catch (Exception e)
			{
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
			
			return null;
		}
	}

	
	
	/**
	 * 
	 * 功能描述：AES解密  方法。
	 * <P/>
	 * 创建时间：2016-03-17
	 * <P/>
	 * 创建人： 于昌亮
	 * 
	 * @param decryptString byte[]类型 要解密的数据（需要使用Base64将字符串转换成byte[]）
	 * 
	 * @param decryptKey String类型 解密时使用的KEY
	 * 
	 * @return String类型 解密后结果
	 * 
	 */
	public static byte[] decrypt(byte[] decryptString, String decryptKey)
	{
		try
		{
			//判断Key是否正确
			if (decryptKey == null)
			{
				System.out.print("Key为空null");
				return null;
			}

			// 判断Key是否为16位
			if (decryptKey.length() != 16)
			{
				System.out.print("Key长度不是16位");
				return null;
			}
			
			//byte[] raw = decryptKey.getBytes("ASCII");
			byte[] raw = decryptKey.getBytes("UTF-8");
			
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(decryptKey.getBytes("UTF-8"));
			cipher.init(Cipher.DECRYPT_MODE, skeySpec,iv);

			try
			{
				byte[] original = cipher.doFinal(decryptString);
				return original;

			} catch (Exception e)
			{
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception e)
		{
			System.out.println(e.toString());
			e.printStackTrace();
			
			return null;
		}
	}
	
	
	
	public static void main(String[] args) throws Exception
	{
	
		/* * 此处使用AES-128-ECB加密模式，key需要为16位。 */
//		String cKey = "0XFDFOpQbDhWSpcp";
//		// 需要加密的字串
//		String cSrc = "SSNSP_JS张三&*-+sdf李四";
//		System.out.println(cSrc);
//		
//		// 加密
//		String enString = AesUtil.encrypt_原来的(cSrc, cKey);
//		System.out.println("加密后的字串是：" + enString);
//		// 解密
//		String DeString = AesUtil.decrypt_原来的(enString, cKey);
//		System.out.println("解密后的字串是：" + DeString);
//		
//		
//		//加密
//		//byte[] ty = Base64Util.decryptBASE64("SSNSP_JS张三&*-+sdf李四");
//		//byte[] arr_mw = encrypt("identifier=com.hnzh.cyjkyy", "0XFDFOpQbDhWSpcp");
//		byte[] arr_mw = encrypt_ECB模式("SSNSP_JS张三&*-+sdf李四", "0XFDFOpQbDhWSpcp");
//		//将 密文数组进行Base64编码
//		String str_mw = Base64Util.encryptBASE64(arr_mw);
//				
//		System.out.println("加密：" + new String(arr_mw) );
//		System.out.println("将密文进行Base64编码：" + str_mw );
//				
//				
//		//解密 
//		byte[] arr_mw_jm_64 = Base64Util.decryptBASE64(str_mw);
//		//将 Base64解码后 进行des解密 
//		byte[] arr_mw_jm = decrypt_ECB模式(arr_mw_jm_64,"0XFDFOpQbDhWSpcp");
//				
//		System.out.println("解密：" + new String(arr_mw_jm) );
//		
//		
//		//加密
//		//byte[] ty = Base64Util.decryptBASE64("SSNSP_JS张三&*-+sdf李四");
//		//byte[] arr_mw = encrypt("identifier=com.hnzh.cyjkyy", "0XFDFOpQbDhWSpcp");
//		byte[] arr_mw_CBC = encrypt("刘一", "TBhvSDyFCmXXiYWp");
//		//将 密文数组进行Base64编码
//		String str_mw_CBC = Base64Util.encryptBASE64(arr_mw_CBC);
//				
//		System.out.println("加密CBC模式：" + new String(arr_mw_CBC) );
//		System.out.println("将密文进行Base64编码CBC模式：" + str_mw_CBC );
//				
//				
//		//解密 
//		byte[] arr_mw_jm_64_CBC = Base64Util.decryptBASE64(str_mw_CBC);
//		//将 Base64解码后 进行des解密 
//		byte[] arr_mw_jm_CBC = decrypt(arr_mw_jm_64_CBC,"TBhvSDyFCmXXiYWp");
//				
//		System.out.println("解密CBC模式：" + new String(arr_mw_jm_CBC) );
		
		
//		String key = "PHQaDyxfTdutEYdE";
		String key = "eAMegubCLhcZstpS";
		String mi = "qGHJG/bTfjKFFt7K8f0tXKVmme4EotShgtyfBo0GfYQ=";

//		String ming = "username=15821982729&validcode=5558";
//		String pa = "xzqh=&grbh=&sfzh=419003195710021559&xm=王彦云&sbkh=&szlb=购药&bdsj=2013-11-24 10:19:47&jylsh=201311241019476147&pagenum=1&pagesize=10";

//		System.out.println(pa);
//		String param =Base64Util.encryptBASE64(encrypt(pa,key)).replace("\r\n", "");
//		System.out.println(param);
//		System.out.println(new String(decrypt(Base64Util.decryptBASE64(pa.replace("\r\n", "")),key), "utf-8"));
		byte[] arr_mw_64 = Base64Util.decryptBASE64(mi);
		
		byte[] arr_mw_jm = AesUtil.decrypt(arr_mw_64,key);

		System.out.println(new String(arr_mw_jm, "utf-8"));
		
		
		
	}
	
	
	

}
