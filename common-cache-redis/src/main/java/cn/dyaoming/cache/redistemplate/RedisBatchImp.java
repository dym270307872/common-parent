package cn.dyaoming.cache.redistemplate;


import java.util.*;

import cn.dyaoming.cache.interfaces.CacheBatchInterface;
import cn.dyaoming.errors.AppDaoException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
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
@SuppressWarnings("unchecked")
public abstract class RedisBatchImp extends RedisBaseImp implements CacheBatchInterface {

    private static final Logger log = LoggerFactory.getLogger(RedisBatchImp.class);

    
    @Override
    public boolean deleteCacheData(List<String> key) {
        boolean rv = false;
        try {
            redisTemplate.delete(key);
            rv = true;
        } catch (Exception e) {
            log.error("异常：deleteCacheData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
            rv = false;
        }
        return rv;
    }



    /**
     * 描述： redis设置List
     *
     * @param redisKey String类型 缓存key
     * @param args List类型 缓存内容
     * @return 保存成功标志
     * @throws AppDaoException 异常内容
     * @version 0.0.2
     */
    @Override
    public boolean setList(String redisKey, List args) {
        boolean rv = false;

        try {
            redisTemplate.opsForList().rightPushAll(redisKey, args);
            rv = true;
        } catch (Exception e) {
            log.error("异常：setList()方法出现异常，异常详细信息：" + e.getMessage() + "。");
            rv = false;
        }

        return rv;
    }



    /**
     * 描述： redis设置List,数据Object[]/Object...
     *
     * @param redisKey String类型 缓存key
     * @param args Object[]/Object...类型 缓存内容
     * @return 保存成功标志
     * @version 0.0.2
     */
    @Override
    public boolean setList(String redisKey, Object... args) {
        boolean rv = false;

        try {
            redisTemplate.opsForList().rightPushAll(redisKey, args);
            rv = true;
        } catch (Exception e) {
            log.error("异常：setList()方法出现异常，异常详细信息：" + e.getMessage() + "。");
            rv = false;
        }
        return rv;
    }



    @Override
    public boolean setList(String redisKey, Integer index, Object args) {
        boolean rv = false;
        try {
            redisTemplate.opsForList().set(redisKey, index, args);
            rv = true;
        } catch (Exception e) {
            log.error("异常：setList()方法出现异常，异常详细信息：" + e.getMessage() + "。");
            rv = false;
        }
        return rv;
    }



    @Override
    public boolean insertBefourIndex(String redisKey, Object index, Object value) {
        boolean rv = false;
        try {
            redisTemplate.opsForList().leftPush(redisKey, index, value);
            rv = true;
        } catch (Exception e) {
            log.error("异常：setList()方法出现异常，异常详细信息：" + e.getMessage() + "。");
            rv = false;
        }
        return rv;
    }



    @Override
    public boolean insertAfterIndex(String redisKey, Object index, Object value) {
        boolean rv = false;
        try {
            redisTemplate.opsForList().rightPush(redisKey, index, value);
            rv = true;
        } catch (Exception e) {
            log.error("异常：setList()方法出现异常，异常详细信息：" + e.getMessage() + "。");
            rv = false;
        }
        return rv;
    }



    /**
     * 描述： list获取栈信息
     *
     * @param redisKey String类型 缓存key
     * @return 保存成功标志
     * @version 0.0.2
     */
    @Override
    public Object getStack(String redisKey) {
        Object rv = null;

        try {
            rv = redisTemplate.opsForList().leftPop(redisKey);
        } catch (Exception e) {
            log.error("异常：getStack()方法出现异常，异常详细信息：" + e.getMessage() + "。");
            rv = null;
        }
        return rv;
    }



    /**
     * 描述： redis获取堆信息
     *
     * @param redisKey String类型 缓存key
     * @return 保存成功标志
     * @version 0.0.2
     */
    @Override
    public Object getHeap(String redisKey) {
        Object rv = null;

        try {
            rv = redisTemplate.opsForList().rightPop(redisKey);
        } catch (Exception e) {
            log.error("异常：getHeap()方法出现异常，异常详细信息：" + e.getMessage() + "。");
            rv = null;
        }
        return rv;
    }



    @Override
    public Long getSize(String redisKey) {
        Long rv = null;

        try {
            rv = redisTemplate.opsForList().size(redisKey);
        } catch (Exception e) {
            log.error("异常：getSize()方法出现异常，异常详细信息：" + e.getMessage() + "。");
            rv = null;
        }
        return rv;
    }



    @Override
    public List getList(String redisKey) {
        List rv = null;

        try {
            rv = redisTemplate.opsForList().range(redisKey, 0,
                    redisTemplate.opsForList().size(redisKey));
        } catch (Exception e) {
            log.error("异常：getSize()方法出现异常，异常详细信息：" + e.getMessage() + "。");
        }
        return rv;
    }



    @Override
    public Object getListValue(String redisKey, Integer index) {
        Object rv = null;

        try {
            rv = redisTemplate.opsForList().index(redisKey, index);
        } catch (Exception e) {
            log.error("异常：getListValue()方法出现异常，异常详细信息：" + e.getMessage() + "。");
        }
        return rv;
    }



    @Override
    public List getList(String redisKey, Integer start, Integer end) {
        List rv = null;

        try {
            rv = redisTemplate.opsForList().range(redisKey, start, end);
        } catch (Exception e) {
            log.error("异常：getList()方法出现异常，异常详细信息：" + e.getMessage() + "。");
        }
        return rv;
    }



    @Override
    public boolean remove(String redisKey, Object value) {
        boolean rv = false;

        try {
            redisTemplate.opsForList().remove(redisKey, 0, value);
            rv = true;
        } catch (Exception e) {
            log.error("异常：remove()方法出现异常，异常详细信息：" + e.getMessage() + "。");
        }
        return rv;
    }



    @Override
    public boolean setMap(String redisKey, Map<String, String> args) {
        boolean rv = false;

        try {
            if (StringUtils.isEmpty(redisKey)) {
                return false;
            } else {
                final String finalKey;
                if (redisKey instanceof String) {
                    finalKey = redisKey;
                } else {
                    finalKey = redisKey.toString();
                }
                HashOperations<String, String, String> hash = redisTemplate.opsForHash();
                hash.putAll(finalKey, args);
            }

        } catch (Exception e) {
            log.error("异常：setMap()方法出现异常，异常详细信息：" + e.getMessage() + "。");
        }

        return rv;
    }



    @Override
    public Map<String, String> getMap(String redisKey) {
        Map<String, String> result = null;
        try {
            if (StringUtils.isEmpty(redisKey)) {
                return null;
            } else {

                HashOperations<String, String, String> hash = redisTemplate.opsForHash();
                result = hash.entries(redisKey);
            }
        } catch (Exception e) {
            log.error("异常：getMap()方法出现异常，异常详细信息：" + e.getMessage() + "。");
        }
        return result;
    }



    @Override
    public String getFromMap(String redisKey, String mapKey) {
        String result = null;
        try {
            if (StringUtils.isEmpty(redisKey)) {
                return null;
            } else {

                HashOperations<String, String, String> hash = redisTemplate.opsForHash();
                result = hash.get(redisKey, mapKey);
            }
        } catch (Exception e) {
            log.error("异常：getFromMap()方法出现异常，异常详细信息：" + e.getMessage() + "。");
        }
        return result;
    }



    @Override
    public void putToMap(String redisKey, String key, String value) {
        try {
            if (!StringUtils.isEmpty(redisKey)) {
                HashOperations<String, String, String> hash = redisTemplate.opsForHash();
                hash.put(redisKey, key, value);
            }
        } catch (Exception e) {
            log.error("异常：putToMap()方法出现异常，异常详细信息：" + e.getMessage() + "。");
        }
    }

}
