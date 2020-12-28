package cn.dyaoming.cache.interfaces;


import cn.dyaoming.errors.AppDaoException;
import cn.dyaoming.sync.interfaces.SyncLockInterface;


/**
 * 功能描述：缓存接口类。
 *
 * @version 0.0.1
 */
public interface CacheBaseInterface extends SyncLockInterface {

    /**
     * 加密标识头
     */
    public final static byte[] DEFALUTHEAD = {-27, -73, -78, -27, -118, -96, -27, -81, -122,
            58};
    /**
     * 默认加密标识
     */
    public final static boolean DEFALUTSECRET = false;
    /**
     * 默认缓存时长（秒）
     */
    public final static long DEFALUTTIME = -1L;
	/**
	 * 字符集编码格式
	 */
	public final static String ENCODE_TYPE = "utf-8";

	/**
     * 功能描述：初始化数据库下标。
     *
     * @param dbIndex 数据库下标
     * @version 0.0.3
     * @since 2019-11-22
     */
    public void init(String dbIndex);


    /**
     * 功能描述：判断是否存在键值。
     *
     * @param key String类型 键
     * @return boolean类型 返回结果
     * @version 0.0.1
     */
    public boolean exists(Object key);


    /**
     * 功能描述：设置缓存对象类型内容。
     *
     * @param key   Object类型 键
     * @param value Object类型 内容
     * @return boolean类型 返回结果
     * @version 0.0.1
     */
    public boolean setCacheObjectData(Object key, Object value);


    /**
     * 功能描述：设置缓存对象类型内容。
     *
     * @param key       String类型 键
     * @param value     Object类型 内容
     * @param validTime long类型 有效时间（单位：秒）
     * @return boolean类型 返回结果
     * @version 0.0.1
     */
    public boolean setCacheObjectData(Object key, Object value, final long validTime);


    /**
     * 功能描述：设置缓存对象类型内容。
     *
     * @param key    Object类型 键
     * @param value  Object类型 内容
     * @param secret boolean类型 加密标识
     * @return boolean类型 返回结果
     * @version 0.0.1
     */
    public boolean setCacheObjectData(Object key, Object value, boolean secret);


    /**
     * 功能描述：设置缓存对象类型内容。
     *
     * @param key       String类型 键
     * @param value     Object类型 内容
     * @param validTime long类型 有效时间（单位：秒）
     * @param secret    boolean类型 加密标识
     * @return boolean类型 返回结果
     * @version 0.0.1
     */
    public boolean setCacheObjectData(Object key, Object value, final long validTime,
                                      boolean secret);


    /**
     * 功能描述：删除缓存内容。
     *
     * @param key String类型 键
     * @return boolean类型 返回结果
     * @version 0.0.1
     */
    public boolean deleteCacheData(Object key);


    /**
     * 功能描述：获取缓存内容。
     *
     * @param key String类型 键
     * @return Object类型 返回结果
     * @version 0.0.1
     */
    public Object getCacheData(Object key);


    /**
     * 功能描述：获取缓存内容。
     *
     * @param <T>  泛型对象，标明返回值泛型
     * @param key  String类型 键
     * @param type Class类型 内容类型
     * @return T类型 返回结果
     * @version 0.0.1
     */
    public <T> T getCacheData(String key, Class<T> type);


    /**
     * 描述：清空缓存
     *
     * @throws AppDaoException dao层异常类
     * @version 0.0.1
     * @version 0.0.1
     */
    public void clear() throws AppDaoException;

}
