package cn.dyaoming.utils;


import java.util.Random;
import java.util.UUID;


/**
 * <p>
 * 主键生成工具类
 * </p>
 * 
 * @author DYAOMING
 * @version 0.0.4
 */
public class GeneratorKeyUtil {
    /**
     * 对象实例
     */
    private static GeneratorKeyUtil generateIdUtil = new GeneratorKeyUtil();

    /**
     * 随机数实例
     */
    private static Random random = new Random(9);

    private static final String SERIAL = UUID.randomUUID().toString().replace("-", "");



    public static String getSeral() {
        return SERIAL + String.format("%06d", System.currentTimeMillis());
    }



    /**
     * getInstance 获得对象实例
     * 
     * @author chenly
     * @return GenerateIdUtil
     * @since 1.0.0
     */
    public static GeneratorKeyUtil getInstance() {
        return generateIdUtil;
    }



    /**
     * getNextId 返回一个当前时间的long类型数字
     * 
     * @author chenly
     * @return long
     * @since 1.0.0
     */
    public static synchronized long getNextId() {
        // 单纯时间同时保存多条记录时会造成主键冲突
        return System.currentTimeMillis() + random.nextInt();
    }



    /**
     * randomUUID 取得36个长度的UUID编码
     * 
     * @author zhangmin
     * @return String
     * @since 1.0.0
     */
    public static String randomUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}