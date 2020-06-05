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
     * @author chenly
     *         getInstance 获得对象实例
     * @return GenerateIdUtil
     * @since 1.0.0
     */
    public static GeneratorKeyUtil getInstance() {
        return generateIdUtil;
    }



    /**
     * @author chenly
     *         getNextId 返回一个当前时间的long类型数字
     * @return long
     * @since 1.0.0
     */
    public static synchronized long getNextId() {

        return System.currentTimeMillis() + random.nextInt(); // 单纯时间同时保存多条记录时会造成主键冲突
                                                              // modifed by
                                                              // liusp at
                                                              // 20130312
    }



    /**
     * @author zhangmin
     *         randomUUID 取得36个长度的UUID编码
     * @return String
     * @since 1.0.0
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}