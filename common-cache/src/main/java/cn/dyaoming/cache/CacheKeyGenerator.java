package cn.dyaoming.cache;


import java.lang.reflect.Array;
import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.ClassUtils;

/**
 * <p>缓存key生成序列化类</p>
 * 
 * @author DYAOMING
 * @since 2019-04-03
 * @version V1.0
 */
public class CacheKeyGenerator implements KeyGenerator {

    // custom cache key  
    public static final int NO_PARAM_KEY = 0;
    public static final int NULL_PARAM_KEY = 53;

    @Override
    public Object generate(Object target, Method method, Object... params) {

        StringBuilder key = new StringBuilder();
        key.append(target.getClass().getSimpleName()).append(".").append(method.getName()).append("[");
        if (params.length == 0) {
            return key.append(NO_PARAM_KEY).toString();
        }
        for (Object param : params) {
            if (param == null) {
//                log.warn("input null param for Spring cache, use default key={}", NULL_PARAM_KEY);  
                key.append(NULL_PARAM_KEY);
            } else if (ClassUtils.isPrimitiveArray(param.getClass())) {
                int length = Array.getLength(param);
                for (int i = 0; i < length; i++) {
                    key.append(Array.get(param, i));
                    key.append(',');
                }
            } else if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param instanceof String) {
                key.append(param);
            } else {
                key.append(param.hashCode());
            }
            key.append('-');
        }
        if (key.lastIndexOf("-") == key.length()-1) {
            key.deleteCharAt(key.length() - 1);
        }
        key.append(']');
        return key.toString();
    }
}  