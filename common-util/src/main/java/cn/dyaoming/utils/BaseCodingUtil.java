package cn.dyaoming.utils;


import java.nio.charset.StandardCharsets;
import java.util.Base64;


/**
 * <p>
 * 编码工具类
 * </p>
 * <p>
 * 用于对内容进行编码及解码操作,支持base16，base64
 * </p>
 * 
 * @author dyaoming
 * @since 2021/02/28
 * @version 0.0.6
 */
public class BaseCodingUtil {

    /**
     * <p>
     * 将byte数组转换成16进制编码
     * </p>
     * 
     * @param bytes 数组参数
     * @return 16进制编码结果
     */
    public static String toHexString(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for(byte data : bytes) {
            stringBuffer.append(Integer.toString((data >> 4) & 0x0F,16));
            stringBuffer.append(Integer.toString(data & 0x0F,16));
        }
        return stringBuffer.toString();
    }



    /**
     * <p>
     * 将对象转换成byte数组
     * </p>
     * 
     * @param obj 原始对象
     * @return 结果数组
     */
    private static byte[] transfromObjectToBytes(Object obj) {
        if (obj == null) {
            return null;
        } else if (obj instanceof byte[]) {
            return (byte[]) obj;
        } else {
            return obj.toString().getBytes(StandardCharsets.UTF_8);
        }
    }

    /**
     * <p>将对象编码成base16格式</p>
     * 
     * @param obj 原始对象
     * @return base16格式字符串
     */
    public static String encodeToBase16(Object obj) {
        byte[] bytes = transfromObjectToBytes(obj);
        return toHexString(bytes);
    }
    
    /**
     * <p>将base16格式字符串解码成byte数组</p>
     * 
     * @param encodeMessage base16格式字符串
     * @return byte数组
     */
    public static byte[] decodeFromBase16(String encodeMessage) {
        if (encodeMessage == null || encodeMessage.length() == 0) {
            return null;
        }

        char[] hexChars = encodeMessage.toCharArray();
        // 如果 hex 中的字符不是偶数个, 则忽略最后一个
        byte[] bytes = new byte[hexChars.length / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt("" + hexChars[i * 2] + hexChars[i * 2 + 1], 16);
        }
        return bytes;
    }


    
    /**
     * <p>将对象编码成base64格式</p>
     * 
     * @param obj 原始对象
     * @return base64格式字符串
     */
    public static String encodeToBase64(Object obj) {
        byte[] bytes = transfromObjectToBytes(obj);
        return Base64.getEncoder().encodeToString(bytes);
    }



    /**
     * <p>将base64格式字符串解码成byte数组</p>
     * 
     * @param encodeMessage base64格式字符串
     * @return byte数组
     */
    public static byte[] decodeFromBase64(String encodeMessage) {
        return Base64.getDecoder().decode(encodeMessage);
    }

}
