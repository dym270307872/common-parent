package cn.dyaoming.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


/**
 * 类描述：字符串 工具类。
 *
 * @author DYAOMING
 * @serial 2019-04-21
 * @version 0.0.5
 */
public class StringUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

    private final static String EMPTY = "null";

    public static boolean isEmpty(Object obj) {
        return (obj == null || "".equals(obj));
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }


    public static boolean isAnyEmpty(Object... objs) {
        for(Object obj : objs) {
            if (isEmpty(obj)) { return true; }
        }
        return false;
    }

    public static boolean isNoneEmpty(Object... objs) {
        return !isAnyEmpty(objs);
    }


    public static boolean isBlank(Object obj) {
        return (obj == null || "".equals(obj));
    }

    public static boolean isNotBlank(Object obj) {
        return !isBlank(obj);
    }






    public static boolean isEmpty(final CharSequence cs) {
        return (cs == null) || (cs.length() == 0);
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }



    public static boolean isAnyEmpty(CharSequence... css) {
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
        for(CharSequence cs : css) {
            if (isBlank(cs)) { return true; }
        }
        return false;
    }

    public static boolean isNoneBlank(CharSequence... css) {
        return !isAnyBlank(css);
    }



    public static String trimWhitespace(final String str) {
        return isEmpty(str) ? null : str.trim();
    }



    public static String trimWhiteNull(final String str) {
        return isBlank(str) ? null : trimWhitespace(str);
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
}
