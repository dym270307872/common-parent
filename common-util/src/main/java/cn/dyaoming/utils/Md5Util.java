/*
 * @(#)Md5Util.java 创建于2014-11-14
 * 
 * Copyright (c) 2014-2016 by Hnzh. 
 *
 */
package cn.dyaoming.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * 
 * 类名称：Md5Util
 * <P/>
 * 类描述：MD5加密工具类。
 * <P/>
 * 创建时间：2014-11-14
 * <P/>
 * 创建人： 于昌亮
 * <P/>
 * 修改人：无
 * <P/>
 * 修改时间：无
 * <P/>
 * 修改备注：无
 * <P/>
 * 版本：v1.0
 *
 */
public class Md5Util
{
	
	public Md5Util()
	{
		
	}

	public static String getMD5L8(String str){
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			return new BigInteger(1, md.digest()).toString(16);
		}catch (Exception e){
			return null;
		}

	}
	
	/**
	 * 
	 * 功能描述：加密方法。
	 * <P/>
	 * 创建时间：2014-11-14
	 * <P/>
	 * 创建人： 于昌亮
	 * 
	 * @param s String类型 需要加密的字符串
	 * 
	 * @return String类型 加密后字符串
	 * 
	 */
	public final static String Md5(String s)
	{
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	    try 
	    {
	       byte[] strTemp = s.getBytes(StandardCharsets.UTF_8);
	       MessageDigest mdTemp = MessageDigest.getInstance("MD5");
	       mdTemp.update(strTemp);
	       byte[] md = mdTemp.digest();
	       int j = md.length;
            char[] str = new char[j * 2];
	       int k = 0;
	       for (int i = 0; i < j; i++) 
	       {
	         byte byte0 = md[i];
	         str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	         str[k++] = hexDigits[byte0 & 0xf];
	       }
	       
	       return new String(str);
	   }catch (Exception e)
	   {
	     return null;
	   }
	}
	
	
	
	/**
	 * 
	 * 功能描述：加密方法。
	 * <P/>
	 * 创建时间：2014-11-14
	 * <P/>
	 * 创建人： 于昌亮
	 * 
	 * @param s String类型 需要加密的字符串
	 * 
	 * @param icv String类型 验值字符串
	 * 
	 * @return String类型 加密后字符串（结果会转换成大写）
	 * 
	 */
	public final static String icvMd5(String s,String icv)
	{
		return Md5(Md5(s) + Md5(icv)).toUpperCase();
	}
	
	
	
	public static void main(String[] args)
	{

		System.out.println(Md5Util.icvMd5("123456","ABCD"));

	}

}
