package cn.dyaoming.cache.redistemplate;


import cn.dyaoming.cache.interfaces.CacheBaseInterface;
import cn.dyaoming.errors.AppDaoException;
import cn.dyaoming.utils.AesUtil;
import cn.dyaoming.utils.SerializeUtil;
import cn.dyaoming.utils.StringUtil;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
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
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class RedisBaseImp implements CacheBaseInterface {

    /**
     * 日志常量声明
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisBaseImp.class);

    protected Integer dbIndex = null;

    protected RedisTemplate redisTemplate;


    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }



    @Override
    public void init(String dbIndex) {
        if (StringUtil.isNotEmpty(dbIndex)) {
            this.dbIndex = Integer.valueOf(dbIndex);
        }else {
            this.dbIndex = null;
        }
    }



    protected void selectDb(RedisConnection connection) {
        if (this.dbIndex != null) {
            connection.select(dbIndex);
        }
    }



    /**
     * 功能描述：判断是否存在键值。
     * 
     * @param key String类型 键
     * @return boolean类型 返回结果
     */
    @Override
    public boolean exists(Object key) throws AppDaoException {
        boolean rv = false;

        try {
            if (StringUtil.isNotEmpty(key)) {
                final byte[] finalKey = key.toString().getBytes("utf-8");

                Object obj = redisTemplate.execute(new RedisCallback<Boolean>() {
                    @Override
                    public Boolean doInRedis(RedisConnection connection)
                            throws DataAccessException {
                        selectDb(connection);
                        return connection.exists(finalKey);
                    }
                });
                rv = (Boolean) obj;
            }

        } catch (Exception e) {
            LOGGER.warn("异常：exists()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("判断缓存内容是否存在异常！", e);
        }

        return rv;
    }



    /**
     * 功能描述：设置缓存对象类型内容。
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
     * 功能描述：设置缓存对象类型内容。
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
        try {
            if (StringUtil.isNotEmpty(key)) {
                final byte[] finalKey = key.toString().getBytes("utf-8");
                byte[] valueByte = SerializeUtil.serialize(value);
                if (secret) {
                    valueByte = AesUtil.encrypt(valueByte);
                    int length_byte = DEFALUTHEAD.length + valueByte.length;
                    byte[] all_byte = new byte[length_byte];

                    System.arraycopy(DEFALUTHEAD, 0, all_byte, 0,
                            DEFALUTHEAD.length);
                    System.arraycopy(valueByte, 0, all_byte, DEFALUTHEAD.length,
                            valueByte.length);
                    valueByte = all_byte;
                }
                final byte[] finalValue = valueByte;
                Object obj = redisTemplate.execute(new RedisCallback<Boolean>() {
                    @Override
                    public Boolean doInRedis(RedisConnection connection) {
                        selectDb(connection);
                        connection.set(finalKey, finalValue);
                        // 设置超时间
                        if (validTime > 0L) {
                            connection.expire(finalKey, validTime);
                        }
                        return true;
                    }
                });
                rv = (Boolean) obj;
            }
        } catch (Exception e) {
            LOGGER.warn("异常：setCacheObjectData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("设置缓存内容出现异常！", e);
        }

        return rv;
    }



    /**
     * 功能描述：删除缓存内容。
     * 
     * @param key String类型 键
     * @return boolean类型 返回结果
     */
    @Override
    public boolean deleteCacheData(Object key) throws AppDaoException {
        boolean rv = false;

        try {
            if (StringUtil.isNotEmpty(key)) {
                final byte[] finalKey = key.toString().getBytes("utf-8");
                redisTemplate.execute(new RedisCallback<Long>() {
                    @Override
                    public Long doInRedis(RedisConnection connection)
                            throws DataAccessException {
                        selectDb(connection);
                        return connection.del(finalKey);
                    }
                });

                rv = true;
            }
        } catch (Exception e) {
            LOGGER.warn("异常：deleteCacheData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("删除缓存内容出现异常！", e);
        }

        return rv;
    }



    /**
     * 功能描述：获取缓存内容。
     * 
     * @param key String类型 键
     * @return Object类型 返回结果
     */
    @Override
    public Object getCacheData(Object key) throws AppDaoException {
        Object rv = null;

        try {

            if (StringUtil.isNotEmpty(key)) {
                final byte[] finalKey = key.toString().getBytes("utf-8");
                final byte[] value = (byte[]) redisTemplate.execute(new RedisCallback<byte[]>() {
                    @Override
                    public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                        selectDb(connection);
                        return connection.get(finalKey);
                    }
                });
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
            LOGGER.warn("异常：getCacheData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("获取缓存内容出现异常！", e);
        }

        return rv;
    }



    /**
     * 功能描述：获取缓存内容。
     * 
     * @param key String类型 键
     * @param type Class类型 内容类型
     * @return T类型 返回结果
     */
    @Override
    public <T> T getCacheTData(String key, Class<T> type) throws AppDaoException {
        T rv = null;
        try {
            if (StringUtil.isNotEmpty(key) && type != null) {
                final byte[] finalKey = key.toString().getBytes("utf-8");
                final byte[] value = (byte[]) redisTemplate.execute(new RedisCallback<byte[]>() {
                    @Override
                    public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                        selectDb(connection);
                        return connection.get(finalKey);
                    }
                });
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
//            throw new AppDaoException("获取缓存内容出现异常！", e);
        }
        return rv;
    }



    /**
     * 描述：清空缓存
     */
    @Override
    public void clear() throws AppDaoException {
        try {
            redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    selectDb(connection);
                    connection.flushDb();
                    return 0L;
                }
            });
        } catch (Exception e) {
            LOGGER.warn("清空缓存出现异常！", e);
//            throw new AppDaoException("清空缓存出现异常！", e);
        }
    }
    
    
    @Override
    public boolean tryLock(String key, String serial, long expire) {
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {

            Boolean acquire = connection.setNX(key.getBytes(), serial.getBytes());

            if (acquire) {
                connection.expire(key.getBytes(), expire);
                return true;
            } else {
                byte[] value = connection.get(key.getBytes());

                if (Objects.nonNull(value) && value.length > 0) { return Arrays.equals(value,serial.getBytes()); }
            }
            return false;
        });
    }



    @Override
    public boolean getLock(String key, String serial, long expire, long waittime) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() <= startTime + waittime*1000) {
            if (tryLock(key, serial, expire)) {
                return true;
            } else {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return false;
    }



    @Override
    public boolean releaseLock(String key, String serial) {
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            byte[] value = connection.get(key.getBytes());
            if (Objects.nonNull(value) && value.length > 0) {
                if (Arrays.equals(value,serial.getBytes())) {
                    connection.del(key.getBytes());
                    return true;
                }
            }
            return false;
        });
    }

}
