package cn.dyaoming.cache.interfaces;


import cn.dyaoming.errors.AppDaoException;


/**
 * 功能描述：缓存接口类。
 */
public interface CacheBaseInterface {

	/**
	 * 加密标识头
	 */
	public final static byte[]	DEFALUTHEAD		= { -27, -73, -78, -27, -118, -96, -27, -81, -122,
			58 };
	/**
	 * 默认加密标识
	 */
	public final static boolean	DEFALUTSECRET	= false;
	/**
	 * 默认缓存时长（秒）
	 */
	public final static long	DEFALUTTIME		= 0L;



	/**
	 * 功能描述：判断是否存在键值。
	 * 
	 * @param key String类型 键
	 * @return boolean类型 返回结果
	 * @throws AppDaoException dao层异常类
	 */
	public boolean exists(Object key) throws AppDaoException;



	/**
	 * 功能描述：设置缓存对象类型内容。
	 * 
	 * @param key Object类型 键
	 * @param value Object类型 内容
	 * @return boolean类型 返回结果
	 * @throws AppDaoException dao层异常类
	 */
	public boolean setCacheObjectData(Object key, Object value) throws AppDaoException;



	/**
	 * 功能描述：设置缓存对象类型内容。
	 * 
	 * @param key String类型 键
	 * @param value Object类型 内容
	 * @param validTime long类型 有效时间（单位：秒）
	 * @return boolean类型 返回结果
	 * @throws AppDaoException dao层异常类
	 */
	public boolean setCacheObjectData(Object key, Object value, final long validTime)
			throws AppDaoException;



	/**
	 * 功能描述：设置缓存对象类型内容。
	 * 
	 * @param key Object类型 键
	 * @param value Object类型 内容
	 * @param secret boolean类型 加密标识
	 * @return boolean类型 返回结果
	 * @throws AppDaoException dao层异常类
	 */
	public boolean setCacheObjectData(Object key, Object value, boolean secret)
			throws AppDaoException;



	/**
	 * 功能描述：设置缓存对象类型内容。
	 * 
	 * @param key String类型 键
	 * @param value Object类型 内容
	 * @param validTime long类型 有效时间（单位：秒）
	 * @param secret boolean类型 加密标识
	 * @return boolean类型 返回结果
	 * @throws AppDaoException dao层异常类
	 */
	public boolean setCacheObjectData(Object key, Object value, final long validTime,
			boolean secret)
			throws AppDaoException;



	/**
	 * 功能描述：删除缓存内容。
	 * 
	 * @param key String类型 键
	 * @return boolean类型 返回结果
	 * @throws AppDaoException dao层异常类
	 */
	public boolean deleteCacheData(Object key) throws AppDaoException;



	/**
	 * 功能描述：获取缓存内容。
	 * 
	 * @param key String类型 键
	 * @return Object类型 返回结果
	 * @throws AppDaoException dao层异常类
	 */
	public Object getCacheData(Object key) throws AppDaoException;



	/**
	 * 功能描述：获取缓存内容。
	 * 
	 * @param <T> 泛型对象，标明返回值泛型
	 * @param key String类型 键
	 * @param type Class类型 内容类型
	 * @return T类型 返回结果
	 * @throws AppDaoException dao层异常类
	 */
	public <T> T getCacheTData(String key, Class<T> type) throws AppDaoException;



	/**
	 * 描述：清空缓存
	 * 
	 * @version 0.0.1
	 * @throws AppDaoException dao层异常类
	 */
	public void clear() throws AppDaoException;

}
