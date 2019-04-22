package cn.dyaoming.utils;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;


/**
 * <p>
 * Base64加密解密 工具类
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-04-21
 * @version 0.0.1
 */
public class Base64Util {

	/**
	 * 功能描述：Base64加密（将byte[]转换成字符串） 方法。
	 * 
	 * @param data byte[]类型 要加密的数据
	 * @return String类型 加密后结果
	 */
	public static String encryptBASE64(byte[] data) {
		return (new BASE64Encoder()).encodeBuffer(data);
	}



	/**
	 * 功能描述：Base64加密（将byte[]转换成字符串） 方法。
	 * 
	 * @param data String类型 要加密的数据
	 * @return String类型 加密后结果
	 */
	public static String encryptBASE64(String data) {
		return (new BASE64Encoder()).encodeBuffer(data.getBytes());
	}



	/**
	 * 功能描述：Base64加密（将byte[]转换成字符串） 方法。
	 * 
	 * @param data String类型 要加密的数据
	 * @param encode String类型 编码类型
	 * @return String类型 加密后结果
	 */
	public static String encryptBASE64(String data, String encode) {

		try {
			if (encode == null || "".equals(encode)) {

				return (new BASE64Encoder()).encodeBuffer(data.getBytes());
				// return (new
				// BASE64Encoder()).encodeBuffer(key.getBytes("UTF-8")).replaceAll("\r|\n",
				// "");

			} else {
				return (new BASE64Encoder()).encodeBuffer(data.getBytes(encode));
			}

		} catch(UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}



	/**
	 * 功能描述：Base64解密（将字符串转换成byte[]） 方法。
	 * 
	 * @param data String类型 要解密数据
	 * @return byte[]类型 解密后byte数组结果
	 */
	public static byte[] decryptBASE64(String data) {
		try {

			return (new BASE64Decoder()).decodeBuffer(data);

		} catch(Exception e) {

			return null;

		}
	}



	/**
	 * 功能描述：Base64解密（将字符串转换成byte[]） 方法。
	 * 
	 * @param data byte[]类型 要解密数据
	 * @param encode String类型 编码类型
	 * @return byte[]类型 解密后byte数组结果
	 * @throws Exception 异常
	 */
	public static byte[] decryptBASE64(byte[] data, String encode) throws Exception {
		try {
			if (encode == null || "".equals(encode)) {

				return (new BASE64Decoder()).decodeBuffer(new String(data));

			} else {
				return (new BASE64Decoder()).decodeBuffer(new String(data, encode));
			}

		} catch(Exception e) {
			return null;
		}
	}



	/**
	 * 功能描述：图片转Base64 方法。
	 * 
	 * @param imgFile 图片路径
	 * @return String类型 加密后结果
	 */
	public static String getImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		// String imgFile = "f://test//1.jpg";//待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data).replace("\r\n", ""); // 返回Base64编码过的字节数组字符串
	}



	/**
	 * base64字符串转化成图片
	 * 
	 * @param imgStr 图片base64码
	 * @param imgFilePath 保存路径
	 * @return 成功标志
	 */
	public static boolean generateImage(String imgStr, String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片
		System.out.println("生成图片" + imgFilePath);
		System.out.println("生成图片" + imgFilePath);
		System.out.println("生成图片" + imgFilePath);

		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			if (imgStr.indexOf(";base64,") >= 0) {
				imgStr = imgStr.split(";base64,")[1];
			}
			String imgFileName = imgFilePath;
			if (!isAbsolutePath(imgFilePath)) {
				imgFilePath = "C:/zhserver/UIAP/" + imgFilePath;
				System.out.println("生成图片" + imgFilePath);
				System.out.println("生成图片" + imgFilePath);
				System.out.println("生成图片" + imgFilePath);
				System.out.println("生成图片" + imgFilePath);

			}
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for(int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			// String imgFilePath = "F://test//2.jpg";//新生成的图片
			File filePath = new File(imgFilePath.substring(0, imgFilePath.lastIndexOf("/")));
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}



	/**
	 * 传入路径，返回是否是绝对路径，是绝对路径返回true，反之
	 * 
	 * @param path url路径
	 * @return 成功标志
	 * @since 2015年4月21日
	 */
	public static boolean isAbsolutePath(String path) {
		if (path.startsWith("/") || path.indexOf(":") > 0) {
			return true; 
		}
		return false;
	}

}
