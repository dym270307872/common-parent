package cn.dyaoming.utils;


import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 类描述：随机数 工具类。
 * 
 * @author DYAOMING
 * @serial 2019-04-21
 * @version 0.0.1
 */
public class RandomUtil {

    private final static Logger logger = LoggerFactory.getLogger(RandomUtil.class);



    /**
     * 功能描述：生成随机字符（包括数字和字母） 方法。
     * 
     * @param len int类型 长度
     * @return String类型 返回值
     */
    public static String randomNumChar(int len) {
        char[] chr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        Random random = new Random();

        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < len; i++) {
            buffer.append(chr[random.nextInt(62)]);
        }

        return buffer.toString();
    }



    /**
     * 功能描述：生成随机数字（包括数字） 方法。
     * 
     * @param len int类型 长度
     * @return String类型 返回值
     */
    public static String randomNum(int len) {
        char[] chr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        Random random = new Random();

        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < len; i++) {
            buffer.append(chr[random.nextInt(10)]);
        }

        return buffer.toString();
    }



    /**
     * 功能描述：生成随机字符（包括字母） 方法。
     * 
     * @param len int类型 长度
     * @return String类型 返回值
     */
    public static String randomChar(int len) {
        char[] chr = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        Random random = new Random();

        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < len; i++) {
            buffer.append(chr[random.nextInt(52)]);
        }

        return buffer.toString();
    }

}
