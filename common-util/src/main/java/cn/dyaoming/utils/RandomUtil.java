/*
 * @(#)RandomUtil.java 创建于2017-02-09
 * 
 * Copyright (c) 2017-2019 by Zhkj. 
 *
 */
package cn.dyaoming.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 
 * 类名称：RandomUtil
 * <P/>
 * 类描述：随机数 工具类。
 * <P/>
 * 创建时间：2016-08-21
 * <P/>
 * 创建人： 董耀明
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
public class RandomUtil
{
	
	private static Logger logger = LogManager.getLogger(RandomUtil.class);
	
	
	
	public RandomUtil()
	{

	}
	
	
	
	/**
	 * 
	 * 功能描述：生成随机字符（包括数字和字母） 方法。
	 * <P/>
	 * 创建时间：2016-08-21
	 * <P/>
	 * 创建人： 董耀明
	 * 
	 * @param len int类型 长度
	 * 
	 * @return String类型 返回值
	 * 
	 */
	public static String randomNumChar(int len)
	{
		char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		Random random = new Random();
		
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < len; i++) 
		{
			buffer.append(chr[random.nextInt(62)]);
		}
		
		return buffer.toString();
	}
	
	
	
	/**
	 * 
	 * 功能描述：生成随机数字（包括数字） 方法。
	 * <P/>
	 * 创建时间：2016-08-21
	 * <P/>
	 * 创建人： 董耀明
	 * 
	 * @param len int类型 长度
	 * 
	 * @return String类型 返回值
	 * 
	 */
	public static String randomNum(int len)
	{
		char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',};
		
		Random random = new Random();
		
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < len; i++) 
		{
			buffer.append(chr[random.nextInt(10)]);
		}
		
		return buffer.toString();
	}
	
	
	
	/**
	 * 
	 * 功能描述：生成随机字符（包括字母） 方法。
	 * <P/>
	 * 创建时间：2016-08-21
	 * <P/>
	 * 创建人： 董耀明
	 * 
	 * @param len int类型 长度
	 * 
	 * @return String类型 返回值
	 * 
	 */
	public static String randomChar(int len)
	{
		char[] chr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		Random random = new Random();
		
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < len; i++) 
		{
			buffer.append(chr[random.nextInt(52)]);
		}
		
		return buffer.toString();
	}
	
	
	
	public static void main(String[] args)
	{
		
		System.out.println("随机数："+randomNumChar(16));
		System.out.println("随机数："+randomNum(12));
		System.out.println("随机数："+randomNumChar(8));
		System.out.println("随机数："+randomNumChar(8));
		System.out.println("随机数："+randomNumChar(8));
		System.out.println("随机数："+randomNumChar(8));
		System.out.println("随机数："+randomNumChar(8));
		System.out.println("随机数："+randomNumChar(8));
		System.out.println("随机数："+randomNumChar(8));
		
		
	   Long a =	(new Date()).getTime()/1000;
	   try
	{
		Thread.sleep(2000);
	} catch (InterruptedException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  

	   Long b =	(new Date()).getTime()/1000;
	   
	   System.out.println("a:"+a);
	   System.out.println("b:"+b);
	   System.out.println("b-a:"+(b-a));
	   
	   

		Map tm = new HashMap();
		tm.put("DYM","DYM");
		tm.put("DYM1","DYM1");
		tm.put("DYM","DYM1");
		
		System.out.println("长度："+tm.size());
		System.out.println("长度："+tm.get("DYM"));
	   
	}
	
	

}
