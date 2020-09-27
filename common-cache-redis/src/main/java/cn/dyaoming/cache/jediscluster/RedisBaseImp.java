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
import org.springframework.util.StringUtils;

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
    private static final Logger log = LoggerFactory.getLogger(RedisBaseImp.class);

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
    public boolean exists(Object key) {

        boolean rv = false;
        if (StringUtil.isNotEmpty(key)) {
            try {
                rv = jedisCluster.exists(key.toString());
            } catch (Exception e) {
                log.warn("判断是否存在键值异常", e);
            }
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
    public boolean setCacheObjectData(Object key, Object value) {
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
    public boolean setCacheObjectData(Object key, Object value, final long validTime) {
        return this.setCacheObjectData(key, value, validTime, DEFALUTSECRET);
    }



    @Override
    public boolean setCacheObjectData(Object key, Object value, boolean secret) {
        return this.setCacheObjectData(key, value, DEFALUTTIME, secret);
    }



    @Override
    public boolean setCacheObjectData(Object key, Object value, long validTime,
            boolean secret) {
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
            log.warn("保存缓存信息出现异常 ", e);
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
    public boolean deleteCacheData(Object key) {
        boolean rv = false;

        try {
            if (StringUtil.isNotEmpty(key)) {
                final byte[] finalKey = key.toString().getBytes("utf-8");
                jedisCluster.del(finalKey);
                rv = true;
            }
        } catch (Exception e) {
            log.warn("删除缓存内容出现异常", e);
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
    public Object getCacheData(Object key) {
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
                    }else {
                        rv = SerializeUtil.unSerialize(value);
                    }
                }
            }

        } catch (Exception e) {
            log.warn("获取缓存内容出现异常！", e);
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
    public <T> T getCacheData(String key, Class<T> type) {
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
            log.warn("获取缓存内容出现异常！", e);
            rv = null;
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



    @Override
    public boolean tryLock(String key, String serial, long expire) {
        Object rv = jedisCluster.set(key, serial, "NX", "EX", expire);
        if ("OK".equals(rv)) {
            return true;
        } else {
            String value = jedisCluster.get(key);
            if (!StringUtils.isEmpty(value)) { return value.equals(serial); }
            return false;
        }
    }



    @Override
    public boolean getLock(String key, String serial, long expire, long waittime) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() <= startTime + waittime * 1000) {
            if (tryLock(key, serial, expire)) {
                return true;
            } else {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    log.warn("线程休眠异常",e);
                }
            }
        }
        return false;
    }



    @Override
    public boolean releaseLock(String key, String serial) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object rv = jedisCluster.eval(script, Collections.singletonList(key), Collections.singletonList(serial));

        return "OK".equals(rv);
    }

}
