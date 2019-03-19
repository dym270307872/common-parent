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
 * 
 * 类名称：SortUtil
 * <P/>
 * 类描述： 排序工具类。
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
public class SortUtil
{
	
	private static Logger logger = LogManager.getLogger(Md5Util.class);
	
	
	
	public SortUtil()
	{
		
	}
	
	
	/**
	 * 
	 * 功能描述：字符串排序方法。
	 * <P/>
	 * 创建时间：2016-03-16
	 * <P/>
	 * 创建人： 于昌亮
	 * 
	 * @param target String类型  要处理的字符串
	 * 
	 * @return String类型 返回字符串
	 * 
	 */
	public static String SortStr(String target)
	{
		target = target.replaceAll(" ","");
		String rv = target;

		char[] t_arr = target.toCharArray();
		Arrays.sort(t_arr);
		rv = String.valueOf(t_arr);
		return rv;
	}
	
	
	/**
	 * 
	 * 功能描述：字符串排序方法（按照字符ASCII的值升序排序）。
	 * <P/>
	 * 创建时间：2017-02-14
	 * <P/>
	 * 创建人： 于昌亮
	 * 
	 * @param target String类型  要处理的字符串
	 * 
	 * @return String类型 返回字符串
	 * 
	 */
	public static String sortStringASCIIAsc(String target)
	{
		String rv = target;
		
		char[] arr_char = target.toCharArray();
		
		Arrays.sort(arr_char);
		rv = String.valueOf(arr_char);
		
		return rv;
	}
	
	
	
	/**
	 * 
	 * 功能描述：字符串排序方法（按照字符ASCII的值降序序排序）。
	 * <P/>
	 * 创建时间：2017-02-14
	 * <P/>
	 * 创建人： 于昌亮
	 * 
	 * @param target String类型  要处理的字符串
	 * 
	 * @return String类型 返回字符串
	 * 
	 */
	public static String sortStringASCIIDesc(String target)
	{
		String rv = target;
		
		char[] arr_char = target.toCharArray();
		
		Arrays.sort(arr_char);
		
		int a_c_l = arr_char.length;
		char[] t_arr_char = new char[a_c_l];
		
		for(int i = 0; i < a_c_l; i++)
		{
			t_arr_char[i] = arr_char[(a_c_l - 1) - i ];
		}

		rv = String.valueOf(t_arr_char);
		
		return rv;
	}
	
	
	
	
	public static void main(String[] args)
	{
		/*String aa = sortStringASCIIAsc("app_key=0XFD FOpQbDhWSpcp&identifier=com.hnzh.cyjkyy");
		System.out.println("升序排序后："+aa);
		
		
		String bb = sortStringASCIIDesc("app_key=0XFDFO pQbDhWSpcp&identifier=com.hnzh.cyjkyy");
		System.out.println("降序排序后："+bb);*/

		String cc = SortStr("app_key=0XFDFO pQbDhWSpcp&identifier=com.hnzh.cyjkyy");
		System.out.println("降序排序后："+cc);

	}
	
	
	
}
