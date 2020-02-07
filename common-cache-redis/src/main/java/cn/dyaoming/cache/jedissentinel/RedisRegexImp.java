package cn.dyaoming.cache.jedissentinel;

import java.util.*;

import cn.dyaoming.cache.interfaces.CacheRegexInterface;
import cn.dyaoming.errors.AppDaoException;
import cn.dyaoming.utils.StringUtil;
import redis.clients.jedis.Jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * <p>
 * 使用redis的实现类
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-05-15
 * @version V1.0
 */
public abstract class RedisRegexImp extends RedisBaseImp implements CacheRegexInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRegexImp.class);


    /**
     * 模糊查询仅支持通配符*、?、[]三种，其中*表示匹配任意数量字符，?表示匹配一个任意字符，[abc]表示匹配a、b、c中一个字符
     * 
     * @param pattern String类型 模糊查询条件
     * @return Collection 符合条件的key值列表
     * @throws AppDaoException dao层异常
     */
    @Override
    public Collection<String> getKeys(String pattern) throws AppDaoException {
        Set<String> rv = new HashSet<String>();
        Jedis jedis = null;
        try {
            if (StringUtil.isNotEmpty(pattern)) {
                jedis = getResource();
                selectDb(jedis);
                rv = jedis.keys(pattern);
            }
        } catch(Exception e) {
            LOGGER.warn("异常：getKeys()方法出现异常，异常详细信息：" + e.getMessage() + "。");
            throw new AppDaoException("模糊查询keys出现异常！", e);
        }finally {
            closeResource(jedis);
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
        Jedis jedis = null;
        try {
            if (StringUtil.isNotEmpty(pattern)) {
                jedis = getResource();
                selectDb(jedis);
                Collection<String> keys = getKeys(pattern);
                String[] stringKeys = new String[keys.size()];
                int i = 0;
                for(String key : keys) {
                    stringKeys[i++] = key;
                }
                jedis.del(stringKeys);
                rv = true;
            }
        } catch(Exception e) {
            LOGGER.warn("异常：deleteRegexCacheData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
            throw new AppDaoException("删除缓存内容出现异常！", e);
        }finally {
            closeResource(jedis);
        }

        return rv;
    }

}
