package cn.dyaoming.cache.interfaces;


import cn.dyaoming.errors.AppDaoException;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 功能描述：振辉缓存接口类。
 */
public interface CacheRegexInterface {

	/**
	 * 加密标识头
	 */
	public final static byte[] HEAD = { -27, -73, -78, -27, -118, -96, -27, -81, -122, 58 };



	/**
	 * 功能描述：判断是否存在键值。
	 * 
	 * @param key String类型 键
	 * @return boolean类型 返回结果
	 * @throws AppDaoException 异常内容
	 */
	public boolean exists(Object key) throws AppDaoException;



	/**
	 * 功能描述：获取 keys。
	 * 
	 * @param pattern String类型 key的表达式，也可以使用通配符(*)
	 * @return Collection类型 返回结果
	 * @throws AppDaoException 异常内容
	 */
	public Collection<String> getKeys(String pattern) throws AppDaoException;



	/**
	 * 功能描述：设置缓存字符串类型数据。
	 *
	 * @param key String类型 键
	 * @param value String类型 内容
	 * @return boolean类型 返回结果
	 * @throws AppDaoException 异常内容
	 */
	public boolean setCacheStringData(String key, String value) throws AppDaoException;



	/**
	 * 功能描述：设置缓存字符串类型数据。
	 * 
	 * @param key String类型 键
	 * @param value String类型 内容
	 *        位：毫
	 * @param validTime long类型 有效时间（单位：秒）
	 * @return boolean类型 返回结果
	 * @throws AppDaoException 异常内容
	 */
	public boolean setCacheStringData(String key, String value, final long validTime)
			throws AppDaoException;



	/**
	 * 功能描述：设置缓存对象类型内容。
	 * 
	 * @param key Object类型 键
	 * @param value Object类型 内容
	 * @return boolean类型 返回结果
	 * @throws AppDaoException 异常内容
	 */
	public boolean setCacheObjectData(Object key, Object value) throws AppDaoException;



	/**
	 * 功能描述：设置缓存对象类型内容。
	 *
	 * @param key String类型 键
	 * @param value Object类型 内容
	 * @param validTime long类型 有效时间（单位：秒）
	 * @return boolean类型 返回结果
	 * @throws AppDaoException 异常内容
	 */
	public boolean setCacheObjectData(Object key, Object value, final long validTime)
			throws AppDaoException;



	/**
	 * 功能描述：删除缓存内容。
	 *
	 * @param key String类型 键
	 * @return boolean类型 返回结果
	 * @throws AppDaoException 异常内容
	 */
	public boolean deleteCacheData(Object key) throws AppDaoException;



	/**
	 * 功能描述：使用通配符批量删除缓存内容。
	 * 
	 * @param keyPrefix String类型 键前缀
	 * @param symbol String类型 符号
	 * @return boolean类型 返回结果
	 * @throws AppDaoException 异常内容
	 */
	public boolean deleteCacheData(String keyPrefix, String symbol) throws AppDaoException;



	/**
	 * 功能描述：批量删除缓存内容。
	 * 
	 * @param key List类型 键
	 * @return boolean类型 返回结果
	 * @throws AppDaoException 异常内容
	 */
	public boolean deleteCacheData(List<String> key) throws AppDaoException;



	/**
	 * 功能描述：获取缓存内容。
	 * 
	 * @param key String类型 键
	 * @return Object类型 返回结果
	 * @throws AppDaoException 异常内容
	 */
	public Object getCacheData(String key) throws AppDaoException;



	/**
	 * 功能描述：获取缓存内容。
	 *
	 * @param <T> 泛型入参
	 * @param key String类型 键
	 * @param type Class类型 内容类型
	 * @return T类型 返回结果
	 * @throws AppDaoException 异常
	 */
	public <T> T getCacheTData(String key, Class<T> type) throws AppDaoException;



	/**
	 * 描述： redis设置Map
	 * 
	 * @param redisKey String类型 缓存键名
	 * @param args Map类型 缓存内容
	 * @return 保存结果
	 * @version 0.0.1
	 * @throws AppDaoException 异常内容
	 */
	public boolean setMap(String redisKey, final Map<String, String> args) throws AppDaoException;



	/**
	 * 描述： 从redis缓存中查询Map
	 * 
	 * @version 0.0.1
	 * @param redisKey String类型 缓存键名
	 * @return 缓存的内容
	 * @throws AppDaoException 异常内容
	 */
	public Map<String, String> getMap(String redisKey) throws AppDaoException;



	/**
	 * 描述： 获取redis中mapKey对应value
	 * 
	 * @param redisKey String类型 缓存键名
	 * @param key String类型 map内的键名
	 * @return 缓存的内容
	 * @version 0.0.1
	 * @throws AppDaoException 异常内容
	 */
	public String getFromMap(String redisKey, String key) throws AppDaoException;



	/**
	 * 描述： 向map中存放key-value
	 * 
	 * @param redisKey String类型 缓存键名
	 * @param key String类型 map内的键名
	 * @param value String类型 map内的键值
	 * @version 0.0.1
	 * @throws AppDaoException 异常内容
	 */
	public void putToMap(String redisKey, String key, String value) throws AppDaoException;



	/**
	 * 描述：清空缓存
	 * 
	 * @version 0.0.1
	 * @throws AppDaoException 异常内容
	 */
	public void clear() throws AppDaoException;

}
