/*
 * @(#)StringUtil.java 创建于2017-02-09
 * 
 * Copyright (c) 2017-2019 by Zhkj. 
 *
 */
package cn.dyaoming.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * 类名称：StringUtil
 * <P/>
 * 类描述：字符串 工具类。
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
public class StringUtil
{
	
	private static Logger logger = LogManager.getLogger(StringUtil.class);
	
	

	public StringUtil()
	{

	}
	
	
	
	/**
	 * 
	 * 功能描述：处理空字符串方法。
	 * <P/>
	 * 创建时间：2016-08-21
	 * <P/>
	 * 创建人： 董耀明
	 * 
	 * @param args0 Object类型  要处理的字符串
	 * 
	 * @return String类型 返回字符串
	 * 
	 */
	public static String processNullString(Object args0)
	{
		String rS = null;
		
		if(null == args0 || "null".equals(args0) || args0 == "null" || "null".equals(args0.toString().toLowerCase()))
		{
			rS = "";
		}else
		{
			rS = args0.toString().trim();
		}
		
		return rS;
	}	
	
	
	
	/**
	 * 
	 * 功能描述：字符串转换字符数组方法。
	 * <P/>
	 * 创建时间：2016-08-21
	 * <P/>
	 * 创建人： 董耀明
	 * 
	 * @param args0 String类型   待转换的字符串
	 * 
	 * @param args1 String类型  待分割字符串的分割符
	 * 
	 * @return String[]类型 转换后的字符串数组
	 * 
	 */
	public static String[] conventStringToStrArray(String args0,String args1)
	{
		//第一种分割方法s
		//StringTokenizer commaToker = new StringTokenizer(args0.replace(" ","").replace("null", ""),args1);
		//return new String[commaToker.countTokens()];
		//第二种分割方法
		return args0.replace(" ","").replace("null", "").split("\\"+args1,99);
		
		
	}
	
	/**
	 * 
	 * 功能描述：字符串转换字符数组方法（不去掉空格）。
	 * <P/>
	 * 创建时间：2012-12-16
	 * <P/>
	 * 创建人： 董耀明
	 * 
	 * @param args0 String类型   待转换的字符串
	 * 
	 * @param args1 String类型  待分割字符串的分割符
	 * 
	 * @return String[]类型 转换后的字符串数组
	 * 
	 */
	public static String[] conventStringToStrArray1(String args0,String args1)
	{
		//第一种分割方法s
		//StringTokenizer commaToker = new StringTokenizer(args0.replace(" ","").replace("null", ""),args1);
		//return new String[commaToker.countTokens()];
		//第二种分割方法
		//return args0.replace("null", "").split("\\"+args1,99);
		return args0.trim().split("\\"+args1,99);
	}
	
	
	
	/**
	 * 
	 * 功能描述：字符串转换字符数组方法。
	 * <P/>
	 * 创建时间：2012-12-16
	 * <P/>
	 * 创建人： 董耀明
	 * 
	 * @param args0 String类型   待转换的字符串
	 * 
	 * @param args1 String类型  分隔符1
	 * 
	 * @param args2 String类型  分隔符2
	 * 
	 * @return String[]类型 转换后的字符串数组
	 * 
	 */
	public static String[] conventStringToStrArray(String args0,String args1,String args2)
	{
		return (args0.replace(" ","").replace("null", "").replace(args1, args2)).split("\\"+args2,99);
	}
	
	
	 
    /**
	 * 
	 * 功能描述：字符串转换整型数字类型方法。
	 * <P/>
	 * 创建时间：2012-12-16
	 * <P/>
	 * 创建人： 董耀明
	 * 
	 * @param args0 String类型   待转换的字符串
	 * 
	 * @return int类型 转换后的数字
	 * 
	 */
	public static int conventStringToInteger(Object args0)
	{
		return Integer.parseInt(args0.toString().trim());
	}
	
	
	
	public static void main(String[] args)
	{
		
		String[] arr_param_template = StringUtil.conventStringToStrArray("88888-88888-88888-88888^36101^^^^^^^^^${idCardNum}|${name}|^","&");
		
	}
	
	
	

}
