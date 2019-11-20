package cn.dyaoming.cache.dao;


import java.io.UnsupportedEncodingException;
import java.util.*;

import cn.dyaoming.cache.interfaces.CacheRegexInterface;
import cn.dyaoming.errors.AppDaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;


/**
 * <p>
 * 使用redis的实现类
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-05-15
 * @version V1.0
 */
public class RedisRegexImp extends RedisBaseImp implements CacheRegexInterface {

	private static final Logger	LOGGER	= LogManager.getLogger(RedisRegexImp.class);

	@Autowired
	private RedisTemplate		redisTemplate;



	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}



	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}



	/**
	 * 模糊查询仅支持通配符*、?、[]三种，其中*表示匹配任意数量字符，?表示匹配一个任意字符，[abc]表示匹配a、b、c中一个字符
	 * 
	 * @param pattern String类型 模糊查询条件
	 * @return Collection 符合条件的key值列表
	 * @throws AppDaoException dao层异常
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<String> getKeys(String pattern) throws AppDaoException {
		Set<String> rv = new HashSet<String>();

		try {
			if (!StringUtils.isEmpty(pattern)) {
				final byte[] finalKey = pattern.toString().getBytes("utf-8");

				rv = (Set<String>) redisTemplate.execute(new RedisCallback<Set<String>>(){
					@Override
					public Set<String> doInRedis(RedisConnection connection)
							throws DataAccessException {
						Set<String> rawKeys = new HashSet<String>();

						connection.keys(finalKey).stream().forEach(f -> {
							try {
								rawKeys.add(new String(f, "utf-8"));
							} catch(UnsupportedEncodingException e) {
								// e.printStackTrace();
							}
						});
						return rawKeys;
					}
				});
			}
		} catch(Exception e) {
			LOGGER.error("异常：deleteCacheData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
			throw new AppDaoException("删除缓存内容出现异常！", e);
		}

		return rv;
	}



	/**
	 * 删除缓存内容（使用正则）
	 * 
	 * @param pattern String类型 模糊条件
	 * @return boolean 删除结果
	 * @throws AppDaoException dao层异常
	 */
	@Override
	public boolean deleteRegexCacheData(String pattern) throws AppDaoException {
		boolean rv = false;

		try {
			if (!StringUtils.isEmpty(pattern)) {
				final byte[] finalKey = pattern.toString().getBytes("utf-8");
				redisTemplate.execute(new RedisCallback<Long>(){
					@Override
					public Long doInRedis(RedisConnection connection)
							throws DataAccessException {
						connection.keys(finalKey).stream().forEach(f -> {
							connection.del(f);
						});
						return 0L;
					}
				});

				rv = true;
			}
		} catch(Exception e) {
			LOGGER.error("异常：deleteCacheData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
			throw new AppDaoException("删除缓存内容出现异常！", e);
		}

		return rv;
	}

}
