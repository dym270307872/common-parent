package cn.dyaoming.cache.interfaces;


import java.util.Collection;


/**
 * <p>
 * 正则缓存接口类
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-08-18
 * @version V1.0
 */
public interface CacheRegexInterface {

    /**
     * <p>
     * 获取 keys
     * </p>
     * 
     * @param pattern String类型 key的表达式，也可以使用通配符(*)
     * @return Collection 类型 返回结果
     * @version 0.0.2
     */
    public Collection<String> getKeys(String pattern);



    /**
     * <p>
     * 删除缓存内容
     * </p>
     * 
     * @param pattern String类型 key的表达式，也可以使用通配符(*)
     * @return boolean类型 返回结果
     * @version 0.0.2
     */
    public boolean deleteRegexCacheData(String pattern);

}
