package cn.dyaoming.cache.jedispool;


import cn.dyaoming.cache.interfaces.CacheBaseInterface;
import cn.dyaoming.errors.AppDaoException;
import cn.dyaoming.utils.AesUtil;
import cn.dyaoming.utils.SerializeUtil;
import cn.dyaoming.utils.StringUtil;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * <p>
 * 使用redis的实现类
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-05-15
 * @version V1.0
 */
public abstract class RedisBaseImp implements CacheBaseInterface {

    /**
     * 日志常量声明
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisBaseImp.class);

    protected Integer dbIndex = null;

    /**
     * Jedis连接池
     */
    private JedisPool jedisPool;



    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }



    @Override
    public void init(String dbIndex) {
        if (StringUtil.isNotEmpty(dbIndex)) {
            this.dbIndex = Integer.valueOf(dbIndex);
        }
        this.dbIndex = null;
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
        } catch (Exception e) {
            // e.printStackTrace();
            LOGGER.warn("关闭jedis连接池异常", e);
        }
    }



    /**
     * <p>
     * 功能描述：判断是否存在键值。
     * </p>
     * 
     * @param key String类型 键
     * @return boolean类型 返回结果
     */
    @Override
    public boolean exists(Object key) throws AppDaoException {
        Jedis jedis = null;
        boolean rv = false;
        try {
            if (StringUtil.isNotEmpty(key)) {
                jedis = getResource();
                selectDb(jedis);
                rv = jedis.exists(key.toString());
            }
        } finally {
            closeResource(jedis);
        }
        return rv;
    }



    /**
     * <p>
     * 功能描述：设置缓存对象类型内容。
     * </p>
     * 
     * @param key String类型 键
     * @param value Object类型 内容
     * @return boolean类型 返回结果
     */
    @Override
    public boolean setCacheObjectData(Object key, Object value) throws AppDaoException {
        return this.setCacheObjectData(key, value, DEFALUTTIME, DEFALUTSECRET);
    }



    /**
     * <p>
     * 功能描述：设置缓存对象类型内容。
     * </p>
     *
     * @param key String类型 键
     * @param value Object类型 内容
     * @param validTime long类型 有效时间（单位：秒）
     * @return boolean类型 返回结果
     */
    @Override
    public boolean setCacheObjectData(Object key, Object value, final long validTime)
            throws AppDaoException {
        return this.setCacheObjectData(key, value, validTime, DEFALUTSECRET);
    }



    @Override
    public boolean setCacheObjectData(Object key, Object value, boolean secret)
            throws AppDaoException {
        return this.setCacheObjectData(key, value, DEFALUTTIME, secret);
    }



    @Override
    public boolean setCacheObjectData(Object key, Object value, long validTime,
            boolean secret) throws AppDaoException {
        boolean rv = false;
        Jedis jedis = null;
        try {
            if (StringUtil.isNotEmpty(key)) {
                final byte[] finalKey = key.toString().getBytes("utf-8");
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
        } catch (Exception e) {
            LOGGER.warn("保存缓存信息出现异常 ", e);
            // throw new AppDaoException("缓存对象类型内容出现异常！", e);
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
     * @param key String类型 键
     * @return boolean类型 返回结果
     */
    @Override
    public boolean deleteCacheData(Object key) throws AppDaoException {
        boolean rv = false;
        Jedis jedis = null;
        try {
            if (StringUtil.isNotEmpty(key)) {
                final byte[] finalKey = key.toString().getBytes("utf-8");
                jedis = getResource();
                selectDb(jedis);
                jedis.del(finalKey);
                rv = true;
            }
        } catch (Exception e) {
            LOGGER.warn("删除缓存内容出现异常", e);
            // throw new AppDaoException("删除缓存内容出现异常！", e);
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
     * @param key String类型 键
     * @return Object类型 返回结果
     */
    @Override
    public Object getCacheData(Object key) throws AppDaoException {
        Object rv = null;
        Jedis jedis = null;
        try {
            if (StringUtil.isNotEmpty(key)) {
                final byte[] finalKey = key.toString().getBytes("utf-8");
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
                    }
                    rv = SerializeUtil.unSerialize(value);
                }
            }

        } catch (Exception e) {
            LOGGER.warn("获取缓存内容出现异常！", e);
            throw new AppDaoException("获取缓存内容出现异常！", e);
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
     * @param key String类型 键
     * @param type Class类型 内容类型
     * @return T类型 返回结果
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCacheTData(String key, Class<T> type) throws AppDaoException {
        T rv = null;
        Jedis jedis = null;
        try {
            if (StringUtil.isNotEmpty(key) && type != null) {
                final byte[] finalKey = key.getBytes("utf-8");
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
        } catch (Exception e) {
            LOGGER.warn("获取缓存内容出现异常！", e);
            throw new AppDaoException("获取缓存内容出现异常！", e);
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
    public void clear() throws AppDaoException {
        Jedis jedis = null;
        try {
            jedis = getResource();
            selectDb(jedis);
            jedis.flushDB();
        } finally {
            closeResource(jedis);
        }
    }

}
