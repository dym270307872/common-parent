/*
 * @(#)HttpAccessWebservice.java 创建于2017-02-14
 * 
 * Copyright (c) 2017-2019 by Zhkj. 
 *
 */
package cn.dyaoming.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * 类名称：HttpAccessWebservice
 * <P/>
 * 类描述：Http调用Webservice 工具类。
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
public class HttpAccessWebservice
{
	
	private static Logger logger = LogManager.getLogger(HttpAccessWebservice.class);
	
	

	public HttpAccessWebservice()
	{

	}
	
	

	/**
	 * 
	 * 功能描述：Http Get方式  调用Webservice 方法。
	 * <P/>
	 * 创建时间：2017-02-14
	 * <P/>
	 * 创建人： 于昌亮
	 * 
	 * @param args0 String类型 WebService URL 路径
	 * 
	 * @param args1 String类型 方法名称
	 * 
	 * @param args2 String类型 参数名
	 * 
	 * @param args3 String类型 参数值
	 *  
	 * @return String类型 返回结果
	 * 
	 */
	public static String httpGetAccessWebservcie(String args0, String args1,String args2,String args3)
	{
		
		String rv = "";

		BufferedReader br = null;

		try
		{
			//拼接 URL + PARAM
			StringBuffer sb_url = new StringBuffer();
			sb_url.append(args0);
			//sb_url_param.append("/");
			//sb_url_param.append(args1);
			sb_url.append("?");
			//sb_url_param.append(args2);
			//sb_url_param.append("=");
			//sb_url_param.append(args3);
			
			//处理 特殊 字符 空格 加号 等
			String str_param = args3.toString().replaceAll("\\+", "%2B");
			
			//URL wsUrl = new URL(sb_url + new String(URLEncoder.encode(str_param,"UTF-8")));
			URL wsUrl = new URL(sb_url + str_param);
			
			// 打开和URL之间的连接
			HttpURLConnection httpConn = (HttpURLConnection) wsUrl.openConnection();
			
			// 设置通用的请求属性
			// //设置连接属性
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出
			httpConn.setDoInput(true);// 使用 URL 连接进行输入
			httpConn.setUseCaches(false);// 忽略缓存
			// httpConn.setRequestMethod("POST");// 设置URL请求方法

			// 设置请求属性
			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
			httpConn.setRequestProperty("Content-Type","application/octet-stream");//用于指导实体数据的内容类型  
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpConn.setRequestProperty("Charset", "UTF-8");
			//连接
			httpConn.connect();
			
			// 获得响应状态
			int responseCode = httpConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responseCode)
			{
				// 连接成功
				// 当正确响应时处理数据
				StringBuffer sb = new StringBuffer();
				String readLine;
				// 处理响应流，必须与服务器响应流输出的编码一致
				br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				while ((readLine = br.readLine()) != null)
				{
					sb.append(readLine);
				}
				br.close();
				
				rv = sb.toString();
				
				//System.out.println("返回结果：" + rv);
				logger.info("返回结果：" + rv);
			}
		} catch (IOException e)
		{
			rv = null;
			
			System.out.println("接口未开放或暂时不能联通，接口地址："+args0);
			System.out.println("接口调用异常："+e.getMessage());
			
			logger.error("接口未开放或暂时不能联通，接口地址："+args0);
			logger.error("接口未开放或暂时不能联通，接口地址："+e.getMessage());
		} catch (Exception e)
		{
			rv = null;
			
			System.out.println("接口未开放或暂时不能联通，接口地址："+args0);
			System.out.println("接口调用异常："+e.getMessage());
			
			logger.error("接口未开放或暂时不能联通，接口地址："+args0);
			logger.error("接口未开放或暂时不能联通，接口地址："+e.getMessage());
		}

		
		
		return rv;
	}

	
	
	/**
	 * 
	 * 功能描述：Http Post方式  调用Webservice 方法。
	 * <P/>
	 * 创建时间：2017-02-14
	 * <P/>
	 * 创建人： 于昌亮
	 * 
	 * @param args0 String类型 WebService URL 路径
	 * 
	 * @param args1 String类型 方法名称
	 * 
	 * @param args2 String类型 参数名
	 * 
	 * @param args3 String类型 参数值
	 *  
	 * @return String类型 返回结果
	 * 
	 */
	public static String httpPostAccessWebservcie(String args0, String args1,String args2,String args3)
	{
		
		String rv = "";

		PrintWriter pw = null;
		BufferedReader br = null;

		try
		{
			
			//拼接 URL
			StringBuffer sb_url = new StringBuffer();
			sb_url.append(args0);
			//sb_url.append("/");
			//sb_url.append(args1);
			
			//URL wsUrl = new URL(URLEncoder.encode(sb_url.toString(), "UTF-8"));
			URL wsUrl = new URL(sb_url.toString());
			
			// 打开和URL之间的连接
			HttpURLConnection httpConn = (HttpURLConnection) wsUrl.openConnection();
			
			// 设置通用的请求属性
			// //设置连接属性
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出
			httpConn.setDoInput(true);// 使用 URL 连接进行输入
			httpConn.setUseCaches(false);// 忽略缓存
			httpConn.setRequestMethod("POST");// 设置URL请求方法
			httpConn.setInstanceFollowRedirects(true);
			
			// 设置请求属性
			// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
			httpConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//用于指导实体数据的内容类型  
			httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			httpConn.setRequestProperty("Charset", "UTF-8");
            
			// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
			// 要注意的是connection.getOutputStream会隐含的进行connect,所以下面这句可以不要
			
			//连接
			//要上传的参数  
			pw = new PrintWriter(new OutputStreamWriter(httpConn.getOutputStream(),"UTF-8"));
			//对上传参数进行编码
            //String content = "param1=" + URLEncoder.encode("日本7.7", "UTF-8")+"&param2="+ URLEncoder.encode("中国8.15", "UTF-8");  
            //String content = args2+"="+ URLEncoder.encode(args3, "utf-8");  
            
			//处理 参数，根据实际情况 进行编码、签名、加密、解密等
			//拼接 URL + PARAM
			StringBuffer sb_content = new StringBuffer();
			//sb_content.append("?");
			//sb_content.append(args2);
			//sb_content.append("=");
			sb_content.append(args3);
			
			//处理 特殊 字符 空格 加号 等
			String str_content = sb_content.toString().replaceAll("\\+", "%2B");
			
			//编码 
			String content = str_content;//URLEncoder.encode(str_content, "UTF-8");
			
			pw.print(content);
            pw.flush();
            pw.close();
		    
			// 获得响应状态
			int responseCode = httpConn.getResponseCode();
			if(HttpURLConnection.HTTP_OK == responseCode)
			{
				// 连接成功
				//当正确响应时处理数据
				StringBuffer sb = new StringBuffer();
				String readLine;
				// 处理响应流，必须与服务器响应流输出的编码一致
				br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				while ((readLine = br.readLine()) != null)
				{
					sb.append(readLine);
				}
				br.close();
				
				rv = sb.toString();
				
				//System.out.println("返回结果：" + rv);
				logger.info("返回结果：" + rv);
			}
		} catch (Exception e)
		{
			rv = null;
			e.printStackTrace();
			System.out.println("接口未开放或暂时不能联通，接口地址："+args0);
			System.out.println("接口调用异常："+e.getMessage());
		}

		return rv;
	}

	
	
	public static void main(String[] args)
	{

	}


	
}
