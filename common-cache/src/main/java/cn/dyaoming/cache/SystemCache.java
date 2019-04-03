package cn.dyaoming.cache;


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
    private CacheDao cacheDao;


    /**
     * 缓存名称
     */
    private String name;

    /**
     * 超时时间
     */
    private long timeout;

    private boolean secret = false;


    public SystemCache() {

    }


    public SystemCache(String name, long timeout, boolean secret) {
        setName(name);
        setTimeout(timeout);
        setSecret(secret);
        setCacheDao((CacheDao) SpringUtil.getBean("cacheDao"));
    }


    @Override
    public Object getNativeCache() {
        // TODO Auto-generated method stub
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
            Object object = cacheDao.getCacheData("cache:" + getName() + ":" + finalKey);

            return (object != null ? new SimpleValueWrapper(object) : null);
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Object key, Class<T> type) {
        if (StringUtils.isEmpty(key) || null == type) {
            return null;
        } else {
            final String finalKey;
            final Class<T> finalType = type;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            Object object = cacheDao.getCacheData("cache:" + getName() + ":" + finalKey);
            if (finalType != null && finalType.isInstance(object) && null != object) {
                return (T) object;
            } else {
                return null;
            }
        }
    }

    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }


    @Override
    public void put(final Object key, final Object value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value) || timeout <= 1L) {
            return;
        } else {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            if (!StringUtils.isEmpty(finalKey)) {
                final Object finalValue = value;
                cacheDao.setCacheObjectData("cache:" + getName() + ":" + finalKey, finalValue, timeout);
            }
        }
    }

    /*
     * 根据Key 删除缓存
     */
    @Override
    public void evict(Object key) {
        if (null != key) {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            if (!StringUtils.isEmpty(finalKey)) {
                cacheDao.deleteCacheData("cache:" + getName() + ":" + finalKey);
            }
        }
    }

    /*
     * 清楚系统缓存
     */
    @Override
    public void clear() {
        cacheDao.clear();
    }


    @Override
    public ValueWrapper putIfAbsent(Object arg0, Object arg1) {
        // TODO Auto-generated method stub
        return null;
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

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public void setCacheDao(CacheDao cacheDao) {
        this.cacheDao = cacheDao;
    }


}
