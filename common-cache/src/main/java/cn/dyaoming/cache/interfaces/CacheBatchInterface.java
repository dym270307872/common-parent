package cn.dyaoming.cache.interfaces;


import cn.dyaoming.errors.AppDaoException;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 功能描述：振辉缓存接口类。
 * <p/>
 * 创建时间：2018-07-13
 * <p/>
 * 创建人： DYM
 * <p/>
 * 修改人：无
 * <p/>
 * 修改时间：无
 * <p/>
 * 修改备注：无
 */
public interface CacheBatchInterface {

	/**
	 * 加密标识头
	 */
	public final static byte[] head = { -27, -73, -78, -27, -118, -96, -27, -81, -122, 58 };



	/**
	 * 功能描述：获取 keys。
	 * <p/>
	 * 创建时间：2018-07-13
	 * <p/>
	 * 创建人： DYM
	 * <p/>
	 * 修改人：无
	 * <p/>
	 * 修改时间：无
	 * <p/>
	 * 修改备注：无
	 *
	 * @param pattern String类型 key的表达式，也可以使用通配符(*)
	 * @return Collection<String>类型 返回结果
	 */
	public Collection<String> getKeys(String pattern) throws AppDaoException;



	/**
	 * 功能描述：使用通配符批量删除缓存内容。
	 * <p/>
	 * 创建时间：2018-07-13
	 * <p/>
	 * 创建人： DYM
	 * <p/>
	 * 修改人：无
	 * <p/>
	 * 修改时间：无
	 * <p/>
	 * 修改备注：无
	 *
	 * @param keyPrefix String类型 键前缀
	 * @param symbol String类型 符号
	 * @return boolean类型 返回结果
	 */
	public boolean deleteCacheData(String keyPrefix, String symbol) throws AppDaoException;



	/**
	 * 功能描述：批量删除缓存内容。
	 * <p/>
	 * 创建时间：2018-07-13
	 * <p/>
	 * 创建人： DYM
	 * <p/>
	 * 修改人：无
	 * <p/>
	 * 修改时间：无
	 * <p/>
	 * 修改备注：无
	 *
	 * @param key List<String>类型 键
	 * @return boolean类型 返回结果
	 */
	public boolean deleteCacheData(List<String> key) throws AppDaoException;



	/**
	 * 功能描述：获取缓存内容。
	 * <p/>
	 * 创建时间：2018-07-13
	 * <p/>
	 * 创建人： DYM
	 * <p/>
	 * 修改人：无
	 * <p/>
	 * 修改时间：无
	 * <p/>
	 * 修改备注：无
	 *
	 * @param key String类型 键
	 * @param type Class<T>类型 内容类型
	 * @return T类型 返回结果
	 */
	public <T> T getCacheTData(String key, Class<T> type) throws AppDaoException;



	/**
	 * 描述： redis设置Map
	 * <p/>
	 * 创建时间：2017-03-23
	 * <p/>
	 * 创建人： 董耀明
	 * <p/>
	 * 修改人：无
	 * <p/>
	 * 修改时间：无
	 * <p/>
	 * 修改备注：无
	 * <p/>
	 * 版本：V1.0
	 */
	public boolean setMap(String redisKey, final Map<String, String> args) throws AppDaoException;



	/**
	 * 描述： 从redis缓存中查询Map
	 * <p/>
	 * 创建时间：2017-03-23
	 * <p/>
	 * 创建人： 董耀明
	 * <p/>
	 * 修改人：无
	 * <p/>
	 * 修改时间：无
	 * <p/>
	 * 修改备注：无
	 * <p/>
	 * 版本：V1.0
	 */
	public Map<String, String> getMap(String redisKey) throws AppDaoException;



	
	/**
	 * <p>描述： 获取redis中mapKey对应value</p>
	 * @param redisKey String类型 缓存key
	 * @param mapKey String类型 map内键值
	 * @return String类型 
	 * @throws AppDaoException dao层异常类
	 */
	public String getFromMap(String redisKey, String mapKey) throws AppDaoException;



	/**
	 * 描述： 向map中存放key-value
	 * <p/>
	 * 创建时间：2017-03-23
	 * <p/>
	 * 创建人： 董耀明
	 * <p/>
	 * 修改人：无
	 * <p/>
	 * 修改时间：无
	 * <p/>
	 * 修改备注：无
	 * <p/>
	 * 版本：V1.0
	 */
	public void putToMap(String redisKey, String key, String value) throws AppDaoException;

}
