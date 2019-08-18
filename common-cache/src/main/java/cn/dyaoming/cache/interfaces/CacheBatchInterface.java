package cn.dyaoming.cache.interfaces;


import cn.dyaoming.errors.AppDaoException;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 批量缓存操作接口类
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-08-18
 * @version V1.0
 */
public interface CacheBatchInterface {

	/**
	 * 加密标识头
	 */
	public final static byte[] HEAD = { -27, -73, -78, -27, -118, -96, -27, -81, -122, 58 };



	/**
	 * 功能描述：批量删除缓存内容。
	 *
	 * @param key List类型 键
	 * @return boolean类型 返回结果
	 * @throws AppDaoException 异常内容
	 */
	public boolean deleteCacheData(List<String> key) throws AppDaoException;



	/**
	 * 描述： redis设置Map
	 *
	 * @param redisKey String类型 缓存key
	 * @param args Map类型 缓存内容
	 * @return 保存成功标志
	 * @version 0.0.1
	 * @throws AppDaoException 异常内容
	 */
	public boolean setMap(String redisKey, final Map<String, String> args) throws AppDaoException;



	/**
	 * 描述： 从redis缓存中查询Map
	 *
	 * @version 0.0.1
	 * @param redisKey String类型 缓存key
	 * @return 缓存map内容
	 * @throws AppDaoException 异常内容
	 */
	public Map<String, String> getMap(String redisKey) throws AppDaoException;



	/**
	 * <p>
	 * 描述： 获取redis中mapKey对应value
	 * </p>
	 * 
	 * @param redisKey String类型 缓存key
	 * @param mapKey String类型 map内键值
	 * @return String类型 缓存值
	 * @throws AppDaoException dao层异常类
	 */
	public String getFromMap(String redisKey, String mapKey) throws AppDaoException;



	/**
	 * 描述： 向map中存放key-value
	 * 
	 * @param redisKey String类型 缓存key
	 * @param key String类型 map内键名
	 * @param value String类型 map内键值
	 * @version 0.0.1
	 * @throws AppDaoException 异常内容
	 */
	public void putToMap(String redisKey, String key, String value) throws AppDaoException;

}
