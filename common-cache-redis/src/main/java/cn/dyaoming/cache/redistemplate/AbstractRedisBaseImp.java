package cn.dyaoming.cache.redistemplate;


import cn.dyaoming.cache.interfaces.CacheBaseInterface;
import cn.dyaoming.utils.AesUtil;
import cn.dyaoming.utils.SerializeUtil;
import cn.dyaoming.utils.StringUtil;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.util.SafeEncoder;


/**
 * <p>
 * 使用redis的实现类
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-05-15
 * @version V1.0
 */
public abstract class AbstractRedisBaseImp implements CacheBaseInterface {

    /**
     * 日志常量声明
     */
    private static final Logger log = LoggerFactory.getLogger(AbstractRedisBaseImp.class);


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
     * @param cacheKey String类型 键
     * @return boolean类型 返回结果
     */
    @Override
    public boolean exists(Object cacheKey) {
        boolean rv = false;
        try {
            if (StringUtil.isNotEmpty(cacheKey)) {
                final byte[] finalKey = SafeEncoder.encode(cacheKey.toString());
                Object obj = redisTemplate.execute((RedisCallback) connection -> {
                        selectDb(connection);
                        return connection.exists(finalKey);
                });
                rv = (boolean) obj;
            }
        } catch (Exception e) {
            log.warn("异常：exists()方法出现异常，异常详细信息：" + e.getMessage() + "。");
        }

        return rv;
    }


    /**
     * 功能描述：设置缓存对象类型内容。
     *
     * @param cacheKey  Object类型 键
     * @param value     Object类型 内容
     * @param validTime long类型 有效时间（单位：秒）
     * @param secret    boolean类型 加密标识
     * @return boolean类型 返回结果
     * @version 0.0.1
     */
    @Override
    public boolean setCacheObjectData(Object cacheKey, Object value, long validTime,
            boolean secret) {
        boolean rv = false;
        try {
            if (StringUtil.isNotEmpty(cacheKey)) {
                final byte[] finalKey = SafeEncoder.encode(cacheKey.toString());
                byte[] valueByte = SerializeUtil.serialize(value);
                if (secret) {
                    valueByte = AesUtil.encrypt(valueByte);
                    int byteLength = DEFALUTHEAD.length + valueByte.length;
                    byte[] allByte = new byte[byteLength];

                    System.arraycopy(DEFALUTHEAD, 0, allByte, 0,
                            DEFALUTHEAD.length);
                    System.arraycopy(valueByte, 0, allByte, DEFALUTHEAD.length,
                            valueByte.length);
                    valueByte = allByte;
                }
                final byte[] finalValue = valueByte;
                Object obj = redisTemplate.execute((RedisCallback) connection -> {
                        selectDb(connection);
                        connection.set(finalKey, finalValue);
                        // 设置超时间
                        if (validTime > 0L) {
                            connection.expire(finalKey, validTime);
                        }
                        return true;
                });
                rv = (Boolean) obj;
            }
        } catch (Exception e) {
            log.warn("异常：setCacheObjectData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
        }

        return rv;
    }



    /**
     * 功能描述：删除缓存内容。
     * 
     * @param cacheKey Object类型 键
     * @return boolean类型 返回结果
     */
    @Override
    public boolean deleteCacheData(Object cacheKey) {
        boolean rv = false;
        try {
            if (StringUtil.isNotEmpty(cacheKey)) {
                final byte[] finalKey = SafeEncoder.encode(cacheKey.toString());
                redisTemplate.execute((RedisCallback) connection -> {
                    selectDb(connection);
                    return connection.del(finalKey);
                });
                rv = true;
            }
        } catch(Exception e) {
            log.warn("异常：deleteCacheData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
        }

        return rv;
    }



    /**
     * 功能描述：获取缓存内容。
     * 
     * @param cacheKey Object类型 键
     * @return Object类型 返回结果
     */
    @Override
    public Object getCacheData(Object cacheKey) {
        Object rv = null;
        try {
            if (StringUtil.isNotEmpty(cacheKey)) {
                final byte[] finalKey = SafeEncoder.encode(cacheKey.toString());
                final byte[] value = (byte[]) redisTemplate.execute((RedisCallback) connection -> {
                    selectDb(connection);
                    return connection.get(finalKey);
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
        } catch(Exception e) {
            log.warn("异常：getCacheData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
        }

        return rv;
    }



    /**
     * 功能描述：获取缓存内容。
     * 
     * @param cacheKey Object类型 键
     * @param type Class类型 内容类型
     * @return T类型 返回结果
     */
    @Override
    public <T> T getCacheData(Object cacheKey, Class<T> type) {
        T rv = null;
        try {
            if (StringUtil.isNotEmpty(cacheKey) && type != null) {
                final byte[] finalKey = SafeEncoder.encode(cacheKey.toString());
                final byte[] value = (byte[]) redisTemplate.execute((RedisCallback) connection -> {
                        selectDb(connection);
                        return connection.get(finalKey);
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
            log.warn("获取缓存内容出现异常！", e);
        }
        return rv;
    }



    /**
     * 描述：清空缓存
     */
    @Override
    public void clear() {
        try {
            redisTemplate.execute((RedisCallback) connection -> {
                selectDb(connection);
                connection.flushDb();
                return 0L;
            });
        } catch(Exception e) {
            log.warn("清空缓存出现异常！", e);
        }
    }
    
    
    @Override
    public boolean tryLock(Object lockKey, Object serial, long expire) {
        final byte[] finalKey = SafeEncoder.encode(lockKey.toString());
        final byte[] finalSerial = SerializeUtil.serialize(serial);
        final byte[] finalExpire = SafeEncoder.encode(String.valueOf(expire));
        return (boolean) redisTemplate.execute((RedisCallback) connection -> {
                Object rv = connection.eval(SafeEncoder.encode(SET_LOCK_LUA_CODE), ReturnType.VALUE, 1, finalKey, finalSerial,
                        finalExpire);
                return SUCCESS_CODE.equals(rv);
        });
    }


    @Override
    public boolean releaseLock(Object lockKey, Object serial) {
        final byte[] finalKey = SafeEncoder.encode(lockKey.toString());
        final byte[] finalSerial = SerializeUtil.serialize(serial);
        return (boolean) redisTemplate.execute((RedisCallback) connection -> {
            Object rv = connection.eval(SafeEncoder.encode(REMOVE_LOCK_LUA_CODE), ReturnType.VALUE, 1, finalKey, finalSerial);
            return SUCCESS_CODE.equals(rv);
        });
    }

}
