package cn.dyaoming.cache.jediscluster;


import cn.dyaoming.cache.interfaces.CacheBaseInterface;
import cn.dyaoming.errors.AppDaoException;
import cn.dyaoming.utils.AesUtil;
import cn.dyaoming.utils.SerializeUtil;
import cn.dyaoming.utils.StringUtil;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisCluster;


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

    /**
     * jedisCluster连接池
     */
    protected JedisCluster jedisCluster;



    @Autowired
    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }



    @Override
    public void init(String dbIndex) {
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

        boolean rv = false;
        if (StringUtil.isNotEmpty(key)) {
            rv = jedisCluster.exists(key.toString());
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
                if (validTime > 0L) {
                    int expireTime = new Long(validTime).intValue();
                    jedisCluster.setex(finalKey, expireTime, finalValue);
                } else {
                    jedisCluster.set(finalKey, finalValue);
                }
                rv = true;
            }
        } catch (Exception e) {
            LOGGER.warn("保存缓存信息出现异常 ", e);
//             throw new AppDaoException("缓存对象类型内容出现异常！", e);
            rv = false;
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
        
        try {
            if (StringUtil.isNotEmpty(key)) {
                final byte[] finalKey = key.toString().getBytes("utf-8");
                jedisCluster.del(finalKey);
                rv = true;
            }
        } catch (Exception e) {
            LOGGER.warn("删除缓存内容出现异常", e);
            // throw new AppDaoException("删除缓存内容出现异常！", e);
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
        
        try {
            if (StringUtil.isNotEmpty(key)) {
                final byte[] finalKey = key.toString().getBytes("utf-8");
                byte[] value = jedisCluster.get(finalKey);

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
//            throw new AppDaoException("获取缓存内容出现异常！", e);
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
        
        try {
            if (StringUtil.isNotEmpty(key) && type != null) {
                final byte[] finalKey = key.getBytes("utf-8");
                
                byte[] value = jedisCluster.get(finalKey);
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
     * <p>
     * 描述：清空缓存
     * </p>
     */
    @Override
    public void clear() throws AppDaoException {
        jedisCluster.flushAll();        
    }

}
