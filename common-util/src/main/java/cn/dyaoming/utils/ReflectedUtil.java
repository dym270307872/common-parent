package cn.dyaoming.utils;

public class ReflectedUtil {

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
