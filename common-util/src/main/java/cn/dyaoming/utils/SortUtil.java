/*
 * @(#)SortUtil.java 创建于2017-02-14
 * 
 * Copyright (c) 2017-2019 by Zhkj. 
 *
 */
package cn.dyaoming.utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


/**
 * 类描述： 排序工具类。
 * 
 * @author DYAOMING
 * @serial 2019-04-21
 * @version 0.0.1
 */
public class SortUtil {

	private static Logger logger = LogManager.getLogger(Md5Util.class);



	/**
	 * 功能描述：字符串排序方法。
	 * 
	 * @param target String类型 要处理的字符串
	 * @return String类型 返回字符串
	 */
	public static String sortStr(String target) {
		target = target.replaceAll(" ", "");
		String rv = target;

		char[] t_arr = target.toCharArray();
		Arrays.sort(t_arr);
		rv = String.valueOf(t_arr);
		return rv;
	}



	/**
	 * 功能描述：字符串排序方法（按照字符ASCII的值升序排序）。
	 * 
	 * @param target String类型 要处理的字符串
	 * @return String类型 返回字符串
	 */
	public static String sortStringASCIIAsc(String target) {
		String rv = target;

		char[] arr_char = target.toCharArray();

		Arrays.sort(arr_char);
		rv = String.valueOf(arr_char);

		return rv;
	}



	/**
	 * 功能描述：字符串排序方法（按照字符ASCII的值降序序排序）。
	 *
	 * @param target String类型 要处理的字符串
	 * @return String类型 返回字符串
	 */
	public static String sortStringASCIIDesc(String target) {
		String rv = target;

		char[] arr_char = target.toCharArray();

		Arrays.sort(arr_char);

		int a_c_l = arr_char.length;
		char[] t_arr_char = new char[a_c_l];

		for(int i = 0; i < a_c_l; i++) {
			t_arr_char[i] = arr_char[(a_c_l - 1) - i];
		}

		rv = String.valueOf(t_arr_char);

		return rv;
	}

}
