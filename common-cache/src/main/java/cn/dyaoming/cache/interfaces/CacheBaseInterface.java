package cn.dyaoming.cache.interfaces;


import cn.dyaoming.sync.interfaces.SyncLockInterface;

/**
 *
 * 功能描述：缓存接口类。
 *
 * @author dym
 * @since 2021/01/05
 * @version 0.0.1
 */
public interface CacheBaseInterface extends SyncLockInterface {

	/**
	 * 加密标识头
	 */
	byte[]  DEFALUTHEAD   = { -27, -73, -78, -27, -118, -96, -27, -81, -122,
			58 };
	/**
	 * 默认加密标识
	 */
	boolean DEFALUTSECRET = false;
	/**
	 * 默认缓存时长（秒）
	 */
	long    DEFALUTTIME   = -1L;

	/**
	 * 功能描述：初始化数据库下标。
	 *
	 * @param dbIndex 数据库下标
	 * @version 0.0.3
	 * @since 2019-11-22
	 */
	void init(String dbIndex);

	/**
	 * 功能描述：判断是否存在键值。
	 *
	 * @param cacheKey Object类型 键
	 * @return boolean类型 返回结果
	 * @version 0.0.1
	 */
	boolean exists(Object cacheKey);

	/**
	 * <p>
	 * 功能描述：设置缓存对象类型内容。
	 * </p>
	 *
	 * @param cacheKey Object类型 键
	 * @param value    Object类型 内容
	 * @return boolean类型 返回结果
	 */
	default boolean setCacheObjectData(Object cacheKey, Object value) {
		return this.setCacheObjectData(cacheKey, value, DEFALUTTIME, DEFALUTSECRET);
	}

	/**
	 * <p>
	 * 功能描述：设置缓存对象类型内容。
	 * </p>
	 *
	 * @param cacheKey  Object类型 键
	 * @param value     Object类型 内容
	 * @param validTime long类型 有效时间（单位：秒）
	 * @return boolean类型 返回结果
	 */
	default boolean setCacheObjectData(Object cacheKey, Object value, final long validTime) {
		return this.setCacheObjectData(cacheKey, value, validTime, DEFALUTSECRET);
	}

	/**
	 * <p>
	 * 功能描述：设置缓存对象类型内容。
	 * </p>
	 *
	 * @param cacheKey Object类型 键
	 * @param value    Object类型 内容
	 * @param secret   boolean类型 有效时间（单位：秒）
	 * @return boolean类型 返回结果
	 */
	default boolean setCacheObjectData(Object cacheKey, Object value, boolean secret) {
		return this.setCacheObjectData(cacheKey, value, DEFALUTTIME, secret);
	}

	/**
	 * 功能描述：设置缓存对象类型内容。
	 *
	 * @param cacheKey  Object类型 键
	 * @param value     Object类型 内容
	 * @param validTime long类型 有效时间（单位：秒）
	 * @param secret    boolean类型 加密标识
	 * @return boolean类型 返回结果
	 * @version 0.0.1
	 */
	boolean setCacheObjectData(Object cacheKey, Object value, final long validTime,
			boolean secret);

	/**
	 * 功能描述：删除缓存内容。
	 *
	 * @param cacheKey Object类型 键
	 * @return boolean类型 返回结果
	 * @version 0.0.1
	 */
	boolean deleteCacheData(Object cacheKey);

	/**
	 * 功能描述：获取缓存内容。
	 *
	 * @param cacheKey Object类型 键
	 * @return Object类型 返回结果
	 * @version 0.0.1
	 */
	Object getCacheData(Object cacheKey);

	/**
	 * 功能描述：获取缓存内容。
	 *
	 * @param <T>      泛型对象，标明返回值泛型
	 * @param cacheKey Object类型 键
	 * @param type     Class类型 内容类型
	 * @return T类型 返回结果
	 * @version 0.0.1
	 */
	<T> T getCacheData(Object cacheKey, Class<T> type);

	/**
	 * 描述：清空缓存
	 *
	 * @version 0.0.1
	 */
	void clear();

}
