package cn.dyaoming.utils;

<<<<<<< HEAD
import java.util.Random;
import java.util.UUID;

=======

import java.util.Random;
import java.util.UUID;


>>>>>>> 4cc56f581bcec0821758f52ea881e0860d6e9d44
/**
 * <p>
 * 主键生成工具类
 * </p>
 * 
 * @author DYAOMING
 * @version 0.0.4
 */
public class GeneratorKeyUtil {
<<<<<<< HEAD
   
=======

>>>>>>> 4cc56f581bcec0821758f52ea881e0860d6e9d44
    /**
     * 对象实例
     */
    private static GeneratorKeyUtil generateIdUtil = new GeneratorKeyUtil();

    /**
     * 随机数实例
     */
    private static Random random = new Random(9);

    private static final String SERIAL = UUID.randomUUID().toString().replace("-", "");
<<<<<<< HEAD
    
    
    public static String getSeral() {
        return SERIAL + String.format("%06d",System.currentTimeMillis());
    }

=======



    public static String getSeral() {
        return SERIAL + String.format("%06d", System.currentTimeMillis());
    }



>>>>>>> 4cc56f581bcec0821758f52ea881e0860d6e9d44
    /**
     * @author chenly
     *         getInstance 获得对象实例
     * @return GenerateIdUtil
<<<<<<< HEAD
     * @exception
     *            @since 1.0.0
=======
     * @since 1.0.0
>>>>>>> 4cc56f581bcec0821758f52ea881e0860d6e9d44
     */
    public static GeneratorKeyUtil getInstance() {
        return generateIdUtil;
    }



    /**
     * @author chenly
     *         getNextId 返回一个当前时间的long类型数字
     * @return long
<<<<<<< HEAD
     * @exception
     *            @since 1.0.0
=======
     * @since 1.0.0
>>>>>>> 4cc56f581bcec0821758f52ea881e0860d6e9d44
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
<<<<<<< HEAD
     * @return String
     * @exception
     *            @since 1.0.0
=======
     * @since 1.0.0
>>>>>>> 4cc56f581bcec0821758f52ea881e0860d6e9d44
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}