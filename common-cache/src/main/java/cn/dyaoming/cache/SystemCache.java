package cn.dyaoming.cache;


import cn.dyaoming.cache.interfaces.CacheInterface;
import cn.dyaoming.utils.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.util.StringUtils;

import java.util.concurrent.Callable;


/**
 * 系统缓存设置类
 *
 * @author DYM
 */
public class SystemCache implements Cache {

	@Autowired
	private CacheInterface	cacheDao;

	/**
	 * 缓存名称
	 */
	private String			name;

	/**
	 * 超时时间
	 */
	private long			timeout;

	/**
	 * 加密标识
	 */
	private boolean			secret;

	/**
	 * 数据库下标
	 */
	private String			database;



	/**
	 * 默认构造函数
	 */
	public SystemCache() {

	}



	/**
	 * 快速构造函数
	 * 
	 * @param name String类型 缓存名称
	 * @param timeout long类型 缓存时长
	 * @param secret boolean类型 加密标识
	 */
	public SystemCache(String name, long timeout, boolean secret) {
		setName(name);
		setTimeout(timeout);
		this.secret = secret;
		setCacheDao((CacheInterface) SpringUtil.getBean("cacheDao"));
	}



	/**
	 * 快速构造函数
	 * 
	 * @param name String类型 缓存名称
	 * @param timeout long类型 缓存时长
	 * @param secret boolean类型 加密标识
	 * @param database String类型 数据库下标
	 */
	public SystemCache(String name, long timeout, boolean secret, String database) {
		setName(name);
		setTimeout(timeout);
		this.secret = secret;
		setDatabase(database);
		setCacheDao((CacheInterface) SpringUtil.getBean("cacheDao"));
	}



	@Override
	public Object getNativeCache() {
		this.cacheDao.init(database);
		return this.cacheDao;
	}



	@Override
	public ValueWrapper get(Object key) {
		if (StringUtils.isEmpty(key)) {
			return null;
		} else {
			final String finalKey;
			if (key instanceof String) {
				finalKey = (String) key;
			} else {
				finalKey = key.toString();
			}
			this.cacheDao.init(database);
			Object object = this.cacheDao.getCacheData("cache:" + getName() + ":" + finalKey);

			return(object != null ? new SimpleValueWrapper(object) : null);
		}
	}



	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object key, Class<T> type) {
		if (StringUtils.isEmpty(key) || null == type) {
			return null;
		} else {
			this.cacheDao.init(database);
			Object object = this.cacheDao.getCacheData("cache:" + getName() + ":" + key);
			if (type != null && type.isInstance(object) && null != object) {
				return (T) object;
			} else {
				return null;
			}
		}
	}



	/**
	 * (non-Javadoc)
	 * 
	 * @param <T> 泛型类型
	 * @param key Object类型 缓存对象的键对象
	 * @param valueLoader Callable类型 序列化类
	 * @return 返还对象
	 */
	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		ValueWrapper vw = get(key);
		if (vw == null) { return null; }
		return (T) vw.get();
	}



	@Override
	public void put(final Object key, final Object value) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value) || timeout == 0L) {
			return;
		} else {
			this.cacheDao.init(database);
			this.cacheDao.setCacheObjectData("cache:" + getName() + ":" + key, value, timeout,
					secret);
		}
	}



	/*
	 * 根据Key 删除缓存
	 */
	@Override
	public void evict(Object key) {
		if (!StringUtils.isEmpty(key)) {
			this.cacheDao.init(database);
			this.cacheDao.deleteRegexCacheData("cache:" + getName() + ":" + key);
		}
	}



	/*
	 * 清楚系统缓存
	 */
	@Override
	public void clear() {
		this.cacheDao.init(database);
		this.cacheDao.clear();
	}



	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value) || timeout == 0L) {
			return null;
		}
		this.cacheDao.init(database);
		if (this.cacheDao.exists(key)) {
			return get(key);
		} else {
			this.cacheDao.setCacheObjectData("cache:" + getName() + ":" + key, value, timeout,
					secret);
			return new SimpleValueWrapper(value);
		}
	}



	@Override
	public String getName() {
		return this.name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}



	/**
	 * @param secret 加密标识
	 */
	public void setSecret(String secret) {

		if ("true".equalsIgnoreCase(secret)) {
			this.secret = true;
		} else {
			this.secret = false;
		}
	}



	public void setDatabase(String database) {
		this.database = database;
	}



	public void setCacheDao(CacheInterface cacheDao) {
		this.cacheDao = cacheDao;
	}

}
