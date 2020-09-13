package cn.dyaoming.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 类描述：字符串 工具类。
 *
 * @author DYAOMING
 * @serial 2019-04-21
 * @version 0.0.1
 */
public class StringUtil {

    private final static Logger logger = LoggerFactory.getLogger(StringUtil.class);



    public static boolean isEmpty(Object obj) {
        return(obj == null || "".equals(obj));
    }



    public static boolean isEmpty(CharSequence cs) {
        return (cs == null) || (cs.length() == 0);
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }



    public static boolean isAnyEmpty(CharSequence... css) {
        if (ArrayUtil.isEmpty(css)) { return true; }
        for(CharSequence cs : css) {
            if (isEmpty(cs)) { return true; }
        }
        return false;
    }



    public static boolean isNoneEmpty(CharSequence... css) {
        return !isAnyEmpty(css);
    }



    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) { return true; }
        for(int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) { return false; }
        }
        return true;
    }



    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }



    public static boolean isAnyBlank(CharSequence... css) {
        if (ArrayUtil.isEmpty(css)) { return true; }
        for(CharSequence cs : css) {
            if (isBlank(cs)) { return true; }
        }
        return false;
    }



    public static boolean isNoneBlank(CharSequence... css) {
        return !isAnyBlank(css);
    }



    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }



    public static String trimToNull(final String str) {
        final String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }



    /**
     * 功能描述：处理空字符串方法。
     *
     * @param args0 Object类型 要处理的字符串
     * @return String类型 返回字符串
     */
    public static String processNullString(Object args0) {
        String rS = null;

        if (null == args0 || "null".equals(args0)
                || "null".equals(args0.toString().toLowerCase())) {
            rS = "";
        } else {
            rS = args0.toString().trim();
        }

        return rS;
    }



    /**
     * 功能描述：字符串转换字符数组方法。
     *
     * @param args0 String类型 待转换的字符串
     * @param args1 String类型 待分割字符串的分割符
     * @return String[]类型 转换后的字符串数组
     */
    public static String[] conventStringToStrArray(String args0, String args1) {
        // 第一种分割方法s
        // StringTokenizer commaToker = new StringTokenizer(args0.replace("
        // ","").replace("null", ""),args1);
        // return new String[commaToker.countTokens()];
        // 第二种分割方法
        return args0.replace(" ", "").replace("null", "").split("\\" + args1, 99);

    }



    /**
     * 功能描述：字符串转换字符数组方法（不去掉空格）。
     * 
     * @param args0 String类型 待转换的字符串
     * @param args1 String类型 待分割字符串的分割符
     * @return String[]类型 转换后的字符串数组
     */
    public static String[] conventStringToStrArray1(String args0, String args1) {
        // 第一种分割方法s
        // StringTokenizer commaToker = new StringTokenizer(args0.replace("
        // ","").replace("null", ""),args1);
        // return new String[commaToker.countTokens()];
        // 第二种分割方法
        // return args0.replace("null", "").split("\\"+args1,99);
        return args0.trim().split("\\" + args1, 99);
    }



    /**
     * 功能描述：字符串转换字符数组方法。
     * 
     * @param args0 String类型 待转换的字符串
     * @param args1 String类型 分隔符1
     * @param args2 String类型 分隔符2
     * @return String[]类型 转换后的字符串数组
     */
    public static String[] conventStringToStrArray(String args0, String args1, String args2) {
        return (args0.replace(" ", "").replace("null", "").replace(args1, args2))
                .split("\\" + args2, 99);
    }



    /**
     * 功能描述：字符串转换整型数字类型方法。
     * 
     * @param args0 String类型 待转换的字符串
     * @return int类型 转换后的数字
     */
    public static int conventStringToInteger(Object args0) {
        return Integer.parseInt(args0.toString().trim());
    }

}
