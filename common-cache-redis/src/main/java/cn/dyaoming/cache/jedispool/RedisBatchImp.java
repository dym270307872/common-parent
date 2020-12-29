//package cn.dyaoming.cache.jedispool;
//
//
//import java.util.*;
//
//import cn.dyaoming.cache.interfaces.CacheBaseInterface;
//import cn.dyaoming.cache.interfaces.CacheBatchInterface;
//import cn.dyaoming.errors.AppDaoException;
//import cn.dyaoming.utils.AesUtil;
//import cn.dyaoming.utils.SerializeUtil;
//import cn.dyaoming.utils.StringUtil;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.util.StringUtils;
//
//
///**
// * <p>
// * 批量操作使用jedisPool的实现类
// * </p>
// * 
// * @author DYAOMING
// * @since 2019-05-15
// * @version V1.0
// */
//public abstract class AbstractRedisBatchImp extends AbstractRedisBaseImp implements CacheBatchInterface {
//
//    private static final Logger log = LoggerFactory.getLogger(AbstractRedisBatchImp.class);
//
//   
//
//    @Override
//    public boolean deleteCacheData(List<String> key){
//        boolean rv = false;
//        Jedis jedis = null;
//        try {
//            if (StringUtil.isNotEmpty(key)) {
//                jedis = getResource();
//                String[] keys = key.toArray(new String[key.size()]);
//                jedis.del(keys);
//                rv = true;
//            }
//        } finally {
//            closeResource(jedis);
//        }
//        return rv;        
//    }
//
//    
//    @Override
//    public boolean deleteCacheData(String[] key){
//        boolean rv = false;
//        Jedis jedis = null;
//        try {
//            if (StringUtil.isNotEmpty(key)) {
//                jedis = getResource();
//                jedis.del(key);
//                rv = true;
//            }
//        } finally {
//            closeResource(jedis);
//        }
//        return rv;        
//    }
//
//
//    /**
//     * 描述： redis设置List
//     *
//     * @param redisKey String类型 缓存key
//     * @param args List类型 缓存内容
//     * @return 保存成功标志
//     * @throws AppDaoException 异常内容
//     * @version 0.0.2
//     */
//    @Override
//    public boolean setList(String redisKey, List args){
//        boolean rv = false;
//        Jedis jedis = null;
//        try {
//            if(StringUtil.isNotEmpty(redisKey)) {
//                jedis = getResource();
//                jedis.rpushx(redisKey, args);
//            }
//            redisTemplate.opsForList().rightPushAll(redisKey, args);
//            rv = true;
//        } catch (Exception e) {
//            log.error("异常：setList()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("redis设置List内容出现异常！", e);
//        }
//
//        return rv;
//    }
//
//
//
//    /**
//     * 描述： redis设置List,数据Object[]/Object...
//     *
//     * @param redisKey String类型 缓存key
//     * @param args Object[]/Object...类型 缓存内容
//     * @return 保存成功标志
//     * @throws AppDaoException 异常内容
//     * @version 0.0.2
//     */
//    @Override
//    public boolean setList(String redisKey, Object... args){
//        boolean rv = false;
//
//        try {
//            redisTemplate.opsForList().rightPushAll(redisKey, args);
//            rv = true;
//        } catch (Exception e) {
//            log.error("异常：setList()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("redis设置List内容出现异常！", e);
//        }
//        return rv;
//    }
//
//
//
//    @Override
//    public boolean setList(String redisKey, Integer index, Object args){
//        boolean rv = false;
//        try {
//            redisTemplate.opsForList().set(redisKey, index, args);
//            rv = true;
//        } catch (Exception e) {
//            log.error("异常：setList()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("redis设置List内容出现异常！", e);
//        }
//        return rv;
//    }
//
//
//
//    @Override
//    public boolean insertBefourIndex(String redisKey, Object index, Object value)
//           {
//        boolean rv = false;
//        try {
//            redisTemplate.opsForList().leftPush(redisKey, index, value);
//            rv = true;
//        } catch (Exception e) {
//            log.error("异常：setList()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("redis设置List内容出现异常！", e);
//        }
//        return rv;
//    }
//
//
//
//    @Override
//    public boolean insertAfterIndex(String redisKey, Object index, Object value)
//           {
//        boolean rv = false;
//        try {
//            redisTemplate.opsForList().rightPush(redisKey, index, value);
//            rv = true;
//        } catch (Exception e) {
//            log.error("异常：setList()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("redis设置List内容出现异常！", e);
//        }
//        return rv;
//    }
//
//
//
//    /**
//     * 描述： list获取栈信息
//     *
//     * @param redisKey String类型 缓存key
//     * @return 保存成功标志
//     * @throws AppDaoException 异常内容
//     * @version 0.0.2
//     */
//    public Object getStack(String redisKey){
//        Object rv = null;
//
//        try {
//            rv = redisTemplate.opsForList().leftPop(redisKey);
//        } catch (Exception e) {
//            log.error("异常：getStack()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("list获取栈信息内容出现异常！", e);
//        }
//        return rv;
//    }
//
//
//
//    /**
//     * 描述： redis获取堆信息
//     *
//     * @param redisKey String类型 缓存key
//     * @return 保存成功标志
//     * @throws AppDaoException 异常内容
//     * @version 0.0.2
//     */
//    public Object getHeap(String redisKey){
//        Object rv = null;
//
//        try {
//            rv = redisTemplate.opsForList().rightPop(redisKey);
//        } catch (Exception e) {
//            log.error("异常：getHeap()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("list获取堆信息内容出现异常！", e);
//        }
//        return rv;
//    }
//
//
//
//    @Override
//    public Long getSize(String redisKey){
//        Long rv = null;
//
//        try {
//            rv = redisTemplate.opsForList().size(redisKey);
//        } catch (Exception e) {
//            log.error("异常：getSize()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("list获取内容数量出现异常！", e);
//        }
//        return rv;
//    }
//
//
//
//    @Override
//    public List getList(String redisKey){
//        List rv = null;
//
//        try {
//            rv = redisTemplate.opsForList().range(redisKey, 0,
//                    redisTemplate.opsForList().size(redisKey));
//        } catch (Exception e) {
//            log.error("异常：getSize()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("list获取内容数量出现异常！", e);
//        }
//        return rv;
//    }
//
//
//
//    @Override
//    public Object getListValue(String redisKey, Integer index){
//        Object rv = null;
//
//        try {
//            rv = redisTemplate.opsForList().index(redisKey, index);
//        } catch (Exception e) {
//            log.error("异常：getListValue()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("list获取指定下标内容出现异常！", e);
//        }
//        return rv;
//    }
//
//
//
//    @Override
//    public List getList(String redisKey, Integer start, Integer end){
//        List rv = null;
//
//        try {
//            rv = redisTemplate.opsForList().range(redisKey, start, end);
//        } catch (Exception e) {
//            log.error("异常：getList()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("list获取指定范围内容出现异常！", e);
//        }
//        return rv;
//    }
//
//
//
//    @Override
//    public boolean remove(String redisKey, Object value){
//        boolean rv = false;
//
//        try {
//            redisTemplate.opsForList().remove(redisKey, 0, value);
//            rv = true;
//        } catch (Exception e) {
//            log.error("异常：remove()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("list删除指定内容出现异常！", e);
//        }
//        return rv;
//    }
//
//
//
//    @Override
//    public boolean setMap(String redisKey, Map<String, String> args){
//        boolean rv = false;
//
//        try {
//            if (StringUtils.isEmpty(redisKey)) {
//                return false;
//            } else {
//                final String finalKey;
//                if (redisKey instanceof String) {
//                    finalKey = redisKey;
//                } else {
//                    finalKey = redisKey.toString();
//                }
//                HashOperations<String, String, String> hash = redisTemplate.opsForHash();
//                hash.putAll(finalKey, args);
//            }
//
//        } catch (Exception e) {
//            log.error("异常：setMap()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("redis设置Map出现异常！", e);
//        }
//
//        return rv;
//    }
//
//
//
//    @Override
//    public Map<String, String> getMap(String redisKey){
//        Map<String, String> result = null;
//        try {
//            if (StringUtils.isEmpty(redisKey)) {
//                return null;
//            } else {
//
//                HashOperations<String, String, String> hash = redisTemplate.opsForHash();
//                result = hash.entries(redisKey);
//            }
//        } catch (Exception e) {
//            log.error("异常：getMap()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("从redis缓存中查询Map出现异常！", e);
//        }
//        return result;
//    }
//
//
//
//    @Override
//    public String getFromMap(String redisKey, String mapKey){
//        String result = null;
//        try {
//            if (StringUtils.isEmpty(redisKey)) {
//                return null;
//            } else {
//
//                HashOperations<String, String, String> hash = redisTemplate.opsForHash();
//                result = hash.get(redisKey, mapKey);
//            }
//        } catch (Exception e) {
//            log.error("异常：getFromMap()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("获取redis中mapKey对应value出现异常！", e);
//        }
//        return result;
//    }
//
//
//
//    @Override
//    public void putToMap(String redisKey, String key, String value){
//        try {
//            if (!StringUtils.isEmpty(redisKey)) {
//                HashOperations<String, String, String> hash = redisTemplate.opsForHash();
//                hash.put(redisKey, key, value);
//            }
//        } catch (Exception e) {
//            log.error("异常：putToMap()方法出现异常，异常详细信息：" + e.getMessage() + "。");
//            throw new AppDaoException("向map中存放key-value内容出现异常！", e);
//        }
//    }
//
//}
