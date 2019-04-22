package cn.dyaoming.utils;


import java.io.*;


/**
 * <p> 序列化反序列化工具</p>
 * @author DYAOMING
 * @serial 2019-04-21
 * @version 0.0.1
 */
public class SerializeUtil {


	/**
	 * 序列化方法
	 * @param obj 需要序列化的对象
	 * @return 序列化后的二进制代码
	 */
	public static byte[] serialize(Object obj) {

		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;

		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);

			oos.writeObject(obj);
			byte[] byteArray = baos.toByteArray();
			return byteArray;

		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * 反序列化
	 * 
	 * @param bytes 序列化二进制编码
	 * @return 反序列化后的对象
	 */
	public static Object unSerialize(byte[] bytes) {

		ByteArrayInputStream bais = null;

		try {
			// 反序列化为对象
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();

		} catch(Exception e) {
			// e.printStackTrace();
			return null;
		}
	}
}
