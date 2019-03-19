/*
 * @(#)DesUtil.java 创建于2017-02-14
 *
 * Copyright (c) 2017-2019 by Zhkj.
 *
 */
package cn.dyaoming.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * DES加密介绍
 * DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
 * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
 * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现
 * 。
 * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 */

/**
 *
 * 类名称：DesUtil
 * <P/>
 * 类描述： Des加密解密 工具类。
 * <P/>
 * 创建时间：2017-02-14
 * <P/>
 * 创建人： 董耀明
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
public class DesUtil {

    private static Logger logger = LogManager.getLogger(DesUtil.class);

    private static byte[] iv = {(byte) 0x26, (byte) 0x39, (byte) 0x15,
            (byte) 0x20, (byte) 0x41, (byte) 0xAC, (byte) 0x6D, (byte) 0xEF};


    public DesUtil() {

    }


    /**
     *
     * 功能描述：DES解密  方法。
     * <P/>
     * 创建时间：2018-11-28
     * <P/>
     * 创建人： 董耀明
     *
     * @param decryptString byte[]类型 要解密的数据（需要使用Base64将字符串转换成byte[]）
     *
     * @param decryptKey String类型 解密时使用的KEY
     *
     * @return String类型 解密后结果
     *
     */
    public static byte[] decrypt(byte[] decryptString, String decryptKey) {
        try {
            // DES算法要求有一个可信任的随机数源
            //SecureRandom random = new SecureRandom();
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(decryptKey.getBytes("UTF-8"));
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            // 偏移量
            IvParameterSpec iv = new IvParameterSpec(decryptKey.getBytes("UTF-8"));

            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, iv);

            //IvParameterSpec ivp = new IvParameterSpec(iv);

            // 真正开始解密操作
            return cipher.doFinal(decryptString);

        } catch (Exception e) {
            return null;
        }
    }


    /**
     *
     * 功能描述：DES加密  方法。
     * <P/>
     * 创建时间：2018-11-28
     * <P/>
     * 创建人： 董耀明
     *
     * @param encryptString String类型 要加密的数据
     *
     * @param encryptKey String类型 加密时使用的KEY
     *
     * @return byte[]类型 加密后结果
     *
     */
    public static byte[] encrypt(String encryptString, String encryptKey) {
        try {
            // DES算法要求有一个可信任的随机数源
            //SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(encryptKey.getBytes("UTF-8"));
            //DESKeySpec desKey = new DESKeySpec(encryptKey.getBytes());

            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);

            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

            // 偏移量
            IvParameterSpec iv = new IvParameterSpec(encryptKey.getBytes("UTF-8"));

            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);

            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(encryptString.getBytes());
            //return Base64.encode(cipher.doFinal(encryptString.getBytes()));

        } catch (Exception e) {
//			e.printStackTrace();
            return null;
        }

    }


    // 测试
    public static void main(String args[]) {
        //String aa = decrypt("z6Uxq0X+dO2xp0LBghg==", "K2cN5Kce");

        //System.out.println("" + aa);


        try {

            //加密
            //byte[] ty = Base64Util.decryptBASE64("SSNSP_JS张三&*-+sdf李四");
//			byte[] arr_mw = encrypt("identifier=com.hnzh.cyjkyy", "0XFDFOpQbDhWSpcp");
            byte[] arr_mw = encrypt("identifier=com.hnzh.cyjkyy", "qweasdzx");
            //将 密文数组进行Base64编码


            System.out.println("加密：" + new String(arr_mw));

            String str_mw = Base64Util.encryptBASE64(arr_mw);
            System.out.println("将密文进行Base64编码：" + str_mw);


            //解密
            byte[] arr_mw_jm_64 = Base64Util.decryptBASE64(str_mw);
            //将 Base64解码后 进行des解密
            //byte[] arr_mw_jm = decrypt(arr_mw_jm_64,"0XFDFOpQbDhWSpcp");
            byte[] arr_mw_jm = decrypt(arr_mw_jm_64, "qweasdzx");
//
            System.out.println("解密：" + new String(arr_mw_jm));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}