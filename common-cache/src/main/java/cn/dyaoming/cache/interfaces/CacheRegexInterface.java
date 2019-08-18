package cn.dyaoming.cache.interfaces;


import cn.dyaoming.errors.AppDaoException;

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
	 * @return Collection<String>类型 返回结果
	 * @throws AppDaoException Dao层异常
	 */
	public Collection<String> getKeys(String pattern) throws AppDaoException;



	/**
	 * <p>
	 * 删除缓存内容
	 * </p>
	 * 
	 * @param pattern String类型 key的表达式，也可以使用通配符(*)
	 * @return boolean类型 返回结果
	 * @throws AppDaoException Dao层异常
	 */
	public boolean deleteRegexCacheData(String pattern) throws AppDaoException;

}
