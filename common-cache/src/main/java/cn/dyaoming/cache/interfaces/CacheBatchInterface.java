package cn.dyaoming.cache.interfaces;


import cn.dyaoming.errors.AppDaoException;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 批量缓存操作接口类
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-08-18
 * @Version 0.0.1
 */
public interface CacheBatchInterface {

	/**
	 * 加密标识头
	 */
	public final static byte[] HEAD = { -27, -73, -78, -27, -118, -96, -27, -81, -122, 58 };

	/* list类型逻辑处理-开始 */



	/**
	 * 描述： redis设置List
	 *
	 * @param redisKey String类型 缓存key
	 * @param args List类型 缓存内容
	 * @return 保存成功标志
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public boolean setList(String redisKey, List args) throws AppDaoException;



	/**
	 * 描述： redis设置List,数据Object[]/Object...
	 *
	 * @param redisKey String类型 缓存key
	 * @param args Object[]/Object...类型 缓存内容
	 * @return 保存成功标志
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public boolean setList(String redisKey, Object... args) throws AppDaoException;

	
	/**
	 * 描述： List设置指定下标位置的值
	 *
	 * @param redisKey String类型 缓存key
	 * @param index Integer类型 下标
	 * @param args Object类型 缓存内容
	 * @return 保存成功标志
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public boolean setList(String redisKey,Integer index, Object args) throws AppDaoException;
	
	
	/**
	 * 描述： List在某内容前插入
	 *
	 * @param redisKey String类型 缓存key
	 * @param index Object类型 下标位置
	 * @param value Object类型 缓存内容
	 * @return 保存成功标志
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public boolean insertBefourIndex(String redisKey,Object index, Object value) throws AppDaoException;
	
	
	/**
	 * 描述： List在某内容后插入
	 *
	 * @param redisKey String类型 缓存key
	 * @param index Object类型 下标位置
	 * @param value Object类型 缓存内容
	 * @return 保存成功标志
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public boolean insertAfterIndex(String redisKey,Object index, Object value) throws AppDaoException;


	/**
	 * 描述： list获取栈信息
	 *
	 * @param redisKey String类型 缓存key
	 * @return 栈信息
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public Object getStack(String redisKey) throws AppDaoException;



	/**
	 * 描述： list获取堆信息
	 *
	 * @param redisKey String类型 缓存key
	 * @return 堆信息
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public Object getHeap(String redisKey) throws AppDaoException;


	
	/**
	 * 描述： list获取内容数量
	 *
	 * @param redisKey String类型 缓存key
	 * @return 堆信息
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public Object getSize(String redisKey) throws AppDaoException;


	/**
	 * 描述： list获取内容数量
	 *
	 * @param redisKey String类型 缓存key
	 * @return list获取内容数量
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public List getList(String redisKey) throws AppDaoException;
	
	
	
	/**
	 * 描述： list获取指定下标内容
	 *
	 * @param redisKey String类型 缓存key
	 * @param index Integer类型 数组下标位置
	 * @return 指定下标内容
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public Object getListValue(String redisKey,Integer index) throws AppDaoException;
	
	
	
	/**
	 * 描述： list获取指定下标区间内容
	 *
	 * @param redisKey String类型 缓存key
	 * @param start Integer类型 数组开始位置下标
	 * @param end Integer类型 数组结束位置下标	 
	 * @return 指定下标区间内容
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public List getList(String redisKey,Integer start,Integer end) throws AppDaoException;
	
	
	/**
	 * 描述： 删除list中某元素
	 *
	 * @param redisKey String类型 缓存key
	 * @param value Object类型 要删除的元素内容	 
	 * @return 结果标识
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public boolean remove(String redisKey,Object value) throws AppDaoException;
	
	
	
	

	/* list类型逻辑处理-结束 */

	/**
	 * 描述： redis设置Map
	 *
	 * @param redisKey String类型 缓存key
	 * @param args Map类型 缓存内容
	 * @return 保存成功标志
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public boolean setMap(String redisKey, final Map<String, String> args) throws AppDaoException;



	/**
	 * 描述： 从redis缓存中查询Map
	 *
	 * @param redisKey String类型 缓存key
	 * @return 缓存map内容
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
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
	 * @version 0.0.2
	 */
	public String getFromMap(String redisKey, String mapKey) throws AppDaoException;



	/**
	 * 描述： 向map中存放key-value
	 * 
	 * @param redisKey String类型 缓存key
	 * @param key String类型 map内键名
	 * @param value String类型 map内键值
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public void putToMap(String redisKey, String key, String value) throws AppDaoException;



	/**
	 * 功能描述：批量删除缓存内容。
	 *
	 * @param key List类型 键
	 * @return boolean类型 返回结果
	 * @throws AppDaoException 异常内容
	 * @version 0.0.2
	 */
	public boolean deleteCacheData(List<String> key) throws AppDaoException;

}
