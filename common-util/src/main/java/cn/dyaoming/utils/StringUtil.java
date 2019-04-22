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
 * 类描述：字符串 工具类。
 *
 * @author DYAOMING
 * @serial 2019-04-21
 * @version 0.0.1
 */
public class StringUtil {

	private static Logger logger = LogManager.getLogger(StringUtil.class);



	/**
	 * 功能描述：处理空字符串方法。
	 *
	 * @param args0 Object类型 要处理的字符串
	 * @return String类型 返回字符串
	 */
	public static String processNullString(Object args0) {
		String rS = null;

		if (null == args0 || "null".equals(args0)
				|| "null".equals(args0.toString().toLowerCase())) {
			rS = "";
		} else {
			rS = args0.toString().trim();
		}

		return rS;
	}



	/**
	 * 功能描述：字符串转换字符数组方法。
	 *
	 * @param args0 String类型 待转换的字符串
	 * @param args1 String类型 待分割字符串的分割符
	 * @return String[]类型 转换后的字符串数组
	 */
	public static String[] conventStringToStrArray(String args0, String args1) {
		// 第一种分割方法s
		// StringTokenizer commaToker = new StringTokenizer(args0.replace("
		// ","").replace("null", ""),args1);
		// return new String[commaToker.countTokens()];
		// 第二种分割方法
		return args0.replace(" ", "").replace("null", "").split("\\" + args1, 99);

	}



	/**
	 * 功能描述：字符串转换字符数组方法（不去掉空格）。
	 * 
	 * @param args0 String类型 待转换的字符串
	 * @param args1 String类型 待分割字符串的分割符
	 * @return String[]类型 转换后的字符串数组
	 */
	public static String[] conventStringToStrArray1(String args0, String args1) {
		// 第一种分割方法s
		// StringTokenizer commaToker = new StringTokenizer(args0.replace("
		// ","").replace("null", ""),args1);
		// return new String[commaToker.countTokens()];
		// 第二种分割方法
		// return args0.replace("null", "").split("\\"+args1,99);
		return args0.trim().split("\\" + args1, 99);
	}



	/**
	 * 功能描述：字符串转换字符数组方法。
	 * 
	 * @param args0 String类型 待转换的字符串
	 * @param args1 String类型 分隔符1
	 * @param args2 String类型 分隔符2
	 * @return String[]类型 转换后的字符串数组
	 */
	public static String[] conventStringToStrArray(String args0, String args1, String args2) {
		return (args0.replace(" ", "").replace("null", "").replace(args1, args2))
				.split("\\" + args2, 99);
	}



	/**
	 * 功能描述：字符串转换整型数字类型方法。
	 * 
	 * @param args0 String类型 待转换的字符串
	 * @return int类型 转换后的数字
	 */
	public static int conventStringToInteger(Object args0) {
		return Integer.parseInt(args0.toString().trim());
	}

}
