package cn.dyaoming.cache.redistemplate;


import java.io.UnsupportedEncodingException;
import java.util.*;

import cn.dyaoming.cache.interfaces.CacheRegexInterface;
import cn.dyaoming.errors.AppDaoException;
import cn.dyaoming.utils.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
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
public abstract class AbstractRedisRegexImp extends AbstractRedisBaseImp implements CacheRegexInterface {

    private static final Logger log = LoggerFactory.getLogger(AbstractRedisRegexImp.class);



    /**
     * 模糊查询仅支持通配符*、?、[]三种，其中*表示匹配任意数量字符，?表示匹配一个任意字符，[abc]表示匹配a、b、c中一个字符
     * 
     * @param pattern String类型 模糊查询条件
     * @return Collection 符合条件的key值列表
     * @throws AppDaoException dao层异常
     */
    @Override
    public Collection<String> getKeys(String pattern) {
        Set<String> rv = new HashSet<String>();

        try {
            if (!StringUtils.isEmpty(pattern)) {
                final byte[] finalKey = pattern.toString().getBytes("utf-8");

                final Set<byte[]> keys = (Set<byte[]>) redisTemplate.execute(new RedisCallback<Set<byte[]>>() {
                    @Override
                    public Set<byte[]> doInRedis(RedisConnection connection)
                            throws DataAccessException {
                        selectDb(connection);
                        return connection.keys(finalKey);
                    }
                });
                keys.stream().forEach(f -> {
                    try {
                        rv.add(new String(f, "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        // e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            log.warn("异常：getKeys()方法出现异常，异常详细信息：" + e.getMessage() + "。");
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
    public boolean deleteRegexCacheData(String pattern) {
        boolean rv = false;

        try {
            if (StringUtil.isNotEmpty(pattern)) {
                final byte[] finalKey = pattern.toString().getBytes("utf-8");
                redisTemplate.execute(new RedisCallback<Long>() {
                    @Override
                    public Long doInRedis(RedisConnection connection)
                            throws DataAccessException {
                        selectDb(connection);
                        Set<byte[]> keys = connection.keys(finalKey);
                        final byte[][] rawKeys = new byte[keys.size()][];
                        int i = 0;
                        for(byte[] key : keys) {
                            rawKeys[i++] = key;
                        }
                        connection.del(rawKeys);
                        return 0L;
                    }
                });

                rv = true;
            }
        } catch (Exception e) {
            log.warn("异常：deleteCacheData()方法出现异常，异常详细信息：" + e.getMessage() + "。");
        }

        return rv;
    }

}
