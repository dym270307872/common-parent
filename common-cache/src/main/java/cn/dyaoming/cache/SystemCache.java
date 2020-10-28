package cn.dyaoming.cache;


import cn.dyaoming.cache.interfaces.CacheInterface;
import cn.dyaoming.errors.AppDaoException;
import cn.dyaoming.utils.GeneratorKeyUtil;
import cn.dyaoming.utils.SpringUtil;
import cn.dyaoming.utils.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.concurrent.Callable;


/**
 * 系统缓存设置类
 *
 * @author DYM
 */
public class SystemCache implements Cache, InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(SystemCache.class);

    @Autowired
    private CacheInterface cacheDao;

    /**
     * 缓存名称
     */
    private String name;

    /**
     * 超时时间
     */
    private long timeout;

    /**
     * 加密标识
     */
    private boolean secret;

    /**
     * 数据库下标
     */
    private String database;

    private final static String SEPARATOR = "#";



    /**
     * 默认构造函数
     */
    public SystemCache() {

    }



    /**
     * 快速构造函数
     * 
     * @param name String类型 缓存名称
     */
    public SystemCache(String name) {
        setName(name);
        if (name.indexOf(SEPARATOR) > 0) {

            String[] arg = name.split(SEPARATOR);
            int thisLength = arg.length;

            setName(arg[0]);
            if (thisLength > 1) {
                setTimeout(Long.valueOf(arg[1]));
            }
            if (thisLength > 2) {
                setSecret(arg[2]);
            }
            if (thisLength > 3) {
                setDatabase(arg[3]);
            }
        }
        setCacheDao((CacheInterface) SpringUtil.getBean("cacheDao"));
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
        this.cacheDao.init(this.database);
    }



    @Override
    public Object getNativeCache() {
        return this.cacheDao;
    }



    @Override
    public ValueWrapper get(Object key) {
        if (StringUtil.isEmpty(key)) {
            return null;
        } else {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }

            Object object = this.cacheDao.getCacheData("cache:" + getName() + ":" + finalKey);

            return(object != null ? new SimpleValueWrapper(object) : null);
        }
    }



    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Class<T> type) {
        if (StringUtil.isEmpty(key) || null == type) {
            return null;
        } else {

            Object object = this.cacheDao.getCacheData("cache:" + getName() + ":" + key);
            if (type != null && type.isInstance(object) && null != object) {
                return (T) object;
            } else {
                return null;
            }
        }
    }



    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        ValueWrapper vw = get(key);
        if (vw != null) { return (T) vw; }
        // 使用分布式锁锁住线程-获取返回值，存入缓存。
        String serial = GeneratorKeyUtil.getSeral();
        try {
            cacheDao.getLock("lock:" + key, serial, 2L, 2L);
            vw = get(key);
            if (vw != null) { return (T) vw; }
            Object value = valueLoader.call();
            put(key, value);
            return (T) vw;
        } catch (Exception e) {
            log.warn("读取缓存失败，失败原因：", e);
        } finally {
            cacheDao.releaseLock("lock:" + key, serial);
        }
        return null;
    }



    @Override
    public void put(final Object key, final Object value) {
        if (StringUtil.isEmpty(key) || StringUtil.isEmpty(value) || timeout == 0L) {
            return;
        } else {
            this.cacheDao.setCacheObjectData("cache:" + getName() + ":" + key, value, timeout,
                    secret);
        }
    }



    /*
     * 根据Key 删除缓存
     */
    @Override
    public void evict(Object key) {
        if (!StringUtil.isEmpty(key)) {
            this.cacheDao.deleteRegexCacheData("cache:" + getName() + ":" + key);
        }
    }



    /*
     * 清楚系统缓存
     */
    @Override
    public void clear() {
        try {
            this.cacheDao.clear();
        } catch (AppDaoException e) {
            log.warn("清除缓存失败，失败原因：", e);
        }
    }



    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        if (StringUtil.isEmpty(key) || StringUtil.isEmpty(value) || timeout == 0L) { return null; }

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
    public void setSecret(boolean secret) {
        this.secret = secret;
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



    @Override
    public void afterPropertiesSet() throws Exception {
        this.cacheDao.init(this.database);
    }

}
