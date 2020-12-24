package cn.dyaoming.utils;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 序列化反序列化工具
 * </p>
 * 
 * @author dyaoming
 * @since 2020/12/24
 * @version 0.0.5
 */
public class SerializeUtil {

	private final static Logger LOGGER = LoggerFactory.getLogger(SerializeUtil.class);

	/**
	 * 序列化操作
	 * 
	 * @param obj 需要序列化的对象
	 * @return 序列化后的二进制数据
	 */
	public static byte[] serialize(Object obj) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);) {
			oos.writeObject(obj);
			byte[] byteArray = baos.toByteArray();
			return byteArray;
		} catch (Exception e) {
			LOGGER.warn(obj.getClass().getName() + "对象序列化操作失败", e);
		}
		return null;
	}

	/**
	 * 反序列化操作
	 * 
	 * @param bytes 序列化后的二进制数据
	 * @return 反序列化后的对象
	 */
	public static Object unSerialize(byte[] bytes) {
		try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);) {
			return ois.readObject();
		} catch (Exception e) {
			LOGGER.warn("反序列化操作失败", e);
		}
		return null;
	}
}
