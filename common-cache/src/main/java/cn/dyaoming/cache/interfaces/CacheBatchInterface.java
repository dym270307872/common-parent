package cn.dyaoming.cache.interfaces;


import cn.dyaoming.errors.AppDaoException;

import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 
 * <p>批量缓存操作接口类</p>
 * 
 * @author DYAOMING
 * @since 2019-08-18
 * @version V1.0
 */
public interface CacheBatchInterface {

	/**
	 * 加密标识头
	 */
	public final static byte[] head = { -27, -73, -78, -27, -118, -96, -27, -81, -122, 58 };



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
