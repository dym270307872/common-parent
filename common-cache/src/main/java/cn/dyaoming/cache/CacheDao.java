package cn.dyaoming.cache;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.dyaoming.errors.AppDaoException;



/**
 * 
 * 功能描述：缓存接口类。
 * <P/>
 * 创建时间：2018-07-13
 * <P/>
 * 创建人： DYM
 * <P/>
 * 修改人：无
 * <P/>
 * 修改时间：无
 * <P/>
 * 修改备注：无
 * 
 */
public interface CacheDao {

	
	
	/**
	 * 
	 * 功能描述：判断是否存在键值。
	 * <P/>
	 * 创建时间：2018-07-13
	 * <P/>
	 * 创建人： DYM
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param key String类型  键
	 * 
	 * @return boolean类型  返回结果
	 * 
	 */
	public boolean exists(String key) throws AppDaoException;
	
	
	
	/**
	 * 
	 * 功能描述：获取 keys。
	 * <P/>
	 * 创建时间：2018-07-13
	 * <P/>
	 * 创建人： DYM
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param pattern String类型  key的表达式，也可以使用通配符(*)
	 * 
	 * @return Collection<String>类型  返回结果
	 * 
	 */
	public Collection<String> getKeys(String pattern) throws AppDaoException;
	
	
	
	/**
	 * 
	 * 功能描述：设置缓存字符串类型数据。
	 * <P/>
	 * 创建时间：2018-07-13
	 * <P/>
	 * 创建人： DYM
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param key String类型  键
	 * 
	 * @param value String类型  内容
	 * 
	 * @return boolean类型  返回结果
	 * 
	 */
	public boolean setCacheStringData(String key, String value) throws AppDaoException;

	
	
	/**
	 * 
	 * 功能描述：设置缓存字符串类型数据。
	 * <P/>
	 * 创建时间：2018-07-13
	 * <P/>
	 * 创建人： DYM
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param key String类型  键
	 * 
	 * @param value String类型  内容
	 * 位：毫
	 * @param validTime long类型  有效时间（单位：秒）
	 * 
	 * @return boolean类型  返回结果
	 * 
	 */
	public boolean setCacheStringData(String key, String value, final long validTime) throws AppDaoException;
	
	
	
	/**
	 * 
	 * 功能描述：设置缓存对象类型内容。
	 * <P/>
	 * 创建时间：2018-07-13
	 * <P/>
	 * 创建人： DYM
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param key String类型  键
	 * 
	 * @param value Object类型  内容
	 * 
	 * @return boolean类型  返回结果
	 * 
	 */
	public boolean setCacheObjectData(String key, Object value) throws AppDaoException;
	
	
	
	/**
	 * 
	 * 功能描述：设置缓存对象类型内容。
	 * <P/>
	 * 创建时间：2018-07-13
	 * <P/>
	 * 创建人： DYM
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param key String类型  键
	 * 
	 * @param value Object类型  内容
	 * 
	 * @param validTime long类型  有效时间（单位：秒）
	 * 
	 * @return boolean类型  返回结果
	 * 
	 */
	public boolean setCacheObjectData(String key, Object value, final long validTime) throws AppDaoException;
	
	
	
	/**
	 * 
	 * 功能描述：删除缓存内容。
	 * <P/>
	 * 创建时间：2018-07-13
	 * <P/>
	 * 创建人： DYM
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param key String类型  键
	 * 
	 * @return boolean类型  返回结果
	 * 
	 */
	public boolean deleteCacheData(String key) throws AppDaoException;
	
	
	
	/**
	 * 
	 * 功能描述：使用通配符批量删除缓存内容。
	 * <P/>
	 * 创建时间：2018-07-13
	 * <P/>
	 * 创建人： DYM
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param keyPrefix String类型  键前缀
	 * 
	 * @param symbol String类型  符号
	 * 
	 * @return boolean类型  返回结果
	 * 
	 */
	public boolean deleteCacheData(String keyPrefix, String symbol) throws AppDaoException;
	
	
	
	/**
	 * 
	 * 功能描述：批量删除缓存内容。
	 * <P/>
	 * 创建时间：2018-07-13
	 * <P/>
	 * 创建人： DYM
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param key List<String>类型  键
	 * 
	 * @return boolean类型  返回结果
	 * 
	 */
	public boolean deleteCacheData(List<String> key) throws AppDaoException;
	
	
	
	/**
	 * 
	 * 功能描述：获取缓存内容。
	 * <P/>
	 * 创建时间：2018-07-13
	 * <P/>
	 * 创建人： DYM
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param key String类型  键
	 * 
	 * @return Object类型  返回结果
	 * 
	 */
	public Object getCacheData(String key) throws AppDaoException;
	
	
	
	/**
	 * 
	 * 功能描述：获取缓存内容。
	 * <P/>
	 * 创建时间：2018-07-13
	 * <P/>
	 * 创建人： DYM
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param key String类型  键
	 * 
	 * @param type Class<T>类型  内容类型
	 * 
	 * @return T类型  返回结果
	 * 
	 */
	public <T> T getCacheTData(String key, Class<T> type) throws AppDaoException;
	
	
	
	/**
	 * 描述： redis设置Map
	 * <P/>
	 * 创建时间：2017-03-23
	 * <P/>
	 * 创建人： 董耀明
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * <P/>
	 * 版本：V1.0
	 * 
	 */
	public boolean setMap(String redisKey, final Map<String, String> args) throws AppDaoException;
	
	
	
	/**
	 * 描述： 从redis缓存中查询Map
	 * <P/>
	 * 创建时间：2017-03-23
	 * <P/>
	 * 创建人： 董耀明
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * <P/>
	 * 版本：V1.0
	 * 
	 */
	public Map<String,String> getMap(String redisKey) throws AppDaoException;
	
	
	
	/**
	 * 描述： 获取redis中mapKey对应value
	 * <P/>
	 * 创建时间：2017-03-23
	 * <P/>
	 * 创建人： 董耀明
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * <P/>
	 * 版本：V1.0
	 * 
	 */
	public String getFromMap(String redisKey, String mapKey) throws AppDaoException;
	
	
	
	/**
	 * 描述： 向map中存放key-value
	 * <P/>
	 * 创建时间：2017-03-23
	 * <P/>
	 * 创建人： 董耀明
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * <P/>
	 * 版本：V1.0
	 * 
	 */
	public void putToMap(String redisKey, String key, String value) throws AppDaoException;
	
	
	
	/**
	 * 描述：清空缓存
	 * <P/>
	 * 创建时间：2017-03-23
	 * <P/>
	 * 创建人： 董耀明
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * <P/>
	 * 版本：V1.0
	 * 
	 */
	public void clear() throws AppDaoException;
	
	
	
}
