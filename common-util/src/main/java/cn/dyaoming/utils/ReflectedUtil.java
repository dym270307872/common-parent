package cn.dyaoming.utils;

/**
 * <p>反射工具类</p>
 * 
 * @author DYAOMING
 * @since 2019-04-21
 * @version 0.0.1
 */
public class ReflectedUtil {

    /**
     * @param className 类名称
     * @return 类
     */
    public static Class getclass(String className){
     Class clazz = null;
        try{
            clazz = Class.forName(className);
//            obj = clazz.newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        return clazz;
    }
}
