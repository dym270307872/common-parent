package cn.dyaoming.cache.jedispool;


import cn.dyaoming.cache.interfaces.CacheBaseInterface;
import cn.dyaoming.utils.AesUtil;
import cn.dyaoming.utils.SerializeUtil;
import cn.dyaoming.utils.StringUtil;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;


/**
 * <p>
 * 使用redis的实现类
 * </p>
 *
 * @author DYAOMING
 * @version 0.0.5
 * @since 2019/05/15
 */
public abstract class AbstractRedisBaseImp implements CacheBaseInterface {

	/**
	 * 日志常量声明
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRedisBaseImp.class);

	protected Integer dbIndex = null;

	/**
	 * Jedis连接池
	 */
	private JedisPool jedisPool;



	@Autowired
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}



	@Override
	public void init(String dbIndex) {
		if (StringUtil.isNotEmpty(dbIndex)) {
			this.dbIndex = Integer.valueOf(dbIndex);
		} else {
			this.dbIndex = null;
		}
	}



	/**
	 * jedis连接获取方法
	 *
	 * @return jedis连接
	 */
	protected Jedis getResource() {
		return jedisPool.getResource();
	}



	/**
	 * <p>
	 * jedis链接切换数据库下标
	 * </p>
	 *
	 * @param jedis jedis连接
	 */
	protected void selectDb(Jedis jedis) {
		if (this.dbIndex != null) {
			jedis.select(dbIndex);
		}
	}



	/**
	 * jedis连接关闭方法
	 *
	 * @param jedis jedis连接
	 */
	protected void closeResource(Jedis jedis) {
		try {
			if (jedis != null) {
				jedis.close();
			}
		} catch(Exception e) {
			LOGGER.warn("关闭jedis连接池异常", e);
		}
	}



	/**
	 * <p>
	 * 功能描述：判断是否存在键值。
	 * </p>
	 *
	 * @param cacheKey String类型 键
	 * @return boolean类型 返回结果
	 */
	@Override
	public boolean exists(Object cacheKey) {
		Jedis jedis = null;
		boolean rv = false;
		try {
			if (StringUtil.isNotEmpty(cacheKey)) {
				jedis = getResource();
				selectDb(jedis);
				rv = jedis.exists(SafeEncoder.encode(cacheKey.toString()));
			}
		} finally {
			closeResource(jedis);
		}
		return rv;
	}



	@Override
	public boolean setCacheObjectData(Object cacheKey, Object value, long validTime,
			boolean secret) {
		boolean rv = false;
		Jedis jedis = null;
		try {
			if (StringUtil.isNotEmpty(cacheKey)) {
				final byte[] finalKey = SafeEncoder.encode(cacheKey.toString());
				byte[] valueByte = SerializeUtil.serialize(value);
				if (secret) {
					valueByte = AesUtil.encrypt(valueByte);
					int lengthByte = DEFALUTHEAD.length + valueByte.length;
					byte[] allByte = new byte[lengthByte];

					System.arraycopy(DEFALUTHEAD, 0, allByte, 0,
							DEFALUTHEAD.length);
					System.arraycopy(valueByte, 0, allByte, DEFALUTHEAD.length,
							valueByte.length);
					valueByte = allByte;
				}
				final byte[] finalValue = valueByte;
				jedis = getResource();
				selectDb(jedis);
				if (validTime > 0L) {
					int expireTime = new Long(validTime).intValue();
					jedis.setex(finalKey, expireTime, finalValue);
				} else {
					jedis.set(finalKey, finalValue);

				}
				rv = true;
			}
		} catch(Exception e) {
			LOGGER.warn("保存缓存信息出现异常 ", e);
			rv = false;
		} finally {
			closeResource(jedis);
		}

		return rv;
	}



	/**
	 * <p>
	 * 功能描述：删除缓存内容。
	 * </p>
	 *
	 * @param cacheKey String类型 键
	 * @return boolean类型 返回结果
	 */
	@Override
	public boolean deleteCacheData(Object cacheKey) {
		boolean rv = false;
		Jedis jedis = null;
		try {
			if (StringUtil.isNotEmpty(cacheKey)) {
				final byte[] finalKey = SafeEncoder.encode(cacheKey.toString());
				jedis = getResource();
				selectDb(jedis);
				jedis.del(finalKey);
				rv = true;
			}
		} catch(Exception e) {
			LOGGER.warn("删除缓存内容出现异常", e);
			rv = false;
		} finally {
			closeResource(jedis);
		}

		return rv;
	}



	/**
	 * <p>
	 * 功能描述：获取缓存内容。
	 * </p>
	 *
	 * @param cacheKey String类型 键
	 * @return Object类型 返回结果
	 */
	@Override
	public Object getCacheData(Object cacheKey) {
		Object rv = null;
		Jedis jedis = null;
		try {
			if (StringUtil.isNotEmpty(cacheKey)) {
				final byte[] finalKey = SafeEncoder.encode(cacheKey.toString());
				jedis = getResource();
				selectDb(jedis);
				byte[] value = jedis.get(finalKey);

				if (StringUtil.isNotEmpty(value)) {
					byte[] head = new byte[DEFALUTHEAD.length];
					System.arraycopy(value, 0, head, 0, DEFALUTHEAD.length);
					if (Arrays.equals(head, DEFALUTHEAD)) {
						byte[] body = new byte[value.length - DEFALUTHEAD.length];
						System.arraycopy(value, DEFALUTHEAD.length, body, 0,
								value.length - DEFALUTHEAD.length);
						body = AesUtil.decrypt(body);
						rv = SerializeUtil.unSerialize(body);
					} else {
						rv = SerializeUtil.unSerialize(value);
					}
				}
			}

		} catch(Exception e) {
			LOGGER.warn("获取缓存内容出现异常！", e);
			rv = false;
		} finally {
			closeResource(jedis);
		}
		return rv;
	}



	/**
	 * <p>
	 * 功能描述：获取缓存内容。
	 * </p>
	 *
	 * @param cacheKey Object类型 键
	 * @param type     Class类型 内容类型
	 * @return T类型 返回结果
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCacheData(Object cacheKey, Class<T> type) {
		T rv = null;
		Jedis jedis = null;
		try {
			if (StringUtil.isNotEmpty(cacheKey) && type != null) {
				final byte[] finalKey = SafeEncoder.encode(cacheKey.toString());
				jedis = getResource();
				selectDb(jedis);
				byte[] value = jedis.get(finalKey);
				if (StringUtil.isNotEmpty(value)) {
					byte[] head = new byte[DEFALUTHEAD.length];
					System.arraycopy(value, 0, head, 0, DEFALUTHEAD.length);
					Object rb = null;
					if (Arrays.equals(head, DEFALUTHEAD)) {
						byte[] body = new byte[value.length - DEFALUTHEAD.length];
						System.arraycopy(value, DEFALUTHEAD.length, body, 0,
								value.length - DEFALUTHEAD.length);
						body = AesUtil.decrypt(body);
						rb = SerializeUtil.unSerialize(body);
					} else {
						rb = SerializeUtil.unSerialize(value);
					}

					if (type.isInstance(rb) && null != rb) {
						rv = (T) rb;
					}
				}
			}
		} catch(Exception e) {
			LOGGER.warn("获取缓存内容出现异常！", e);
		} finally {
			closeResource(jedis);
		}
		return rv;
	}



	/**
	 * <p>
	 * 描述：清空缓存
	 * </p>
	 */
	@Override
	public void clear() {
		Jedis jedis = null;
		try {
			jedis = getResource();
			selectDb(jedis);
			jedis.flushDB();
		} finally {
			closeResource(jedis);
		}
	}



	/**
	 * <p>
	 * 尝试获取同步锁（不重试）
	 * </p>
	 *
	 * @param lockKey 同步锁名
	 * @param serial  同步钥匙内容
	 * @param expire  同步锁时长
	 * @return 是否获取到同步锁
	 */
	@Override
	public boolean tryLock(Object lockKey, Object serial, long expire) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			selectDb(jedis);
			Object rv = jedis.eval(SET_LOCK_LUA_CODE, 1, lockKey.toString(), serial.toString(),
					String.valueOf(expire));
			return "OK".equals(rv);
		} finally {
			closeResource(jedis);
		}
	}



	/**
	 * <p>
	 * 释放同步锁
	 * </p>
	 *
	 * @param lockKey 同步锁名
	 * @param serial  同步钥匙内容
	 * @return 释放锁结果
	 */
	@Override
	public boolean releaseLock(Object lockKey, Object serial) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			selectDb(jedis);
			Object rv = jedis.eval(REMOVE_LOCK_LUA_CODE, 1, lockKey.toString(), serial.toString());
			return "OK".equals(rv);
		} finally {
			closeResource(jedis);
		}
	}

}
