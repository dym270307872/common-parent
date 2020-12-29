package cn.dyaoming.sync.interfaces;

import cn.dyaoming.errors.AppBusyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 同步锁方法定义接口类
 * </p>
 *
 * @author dym
 * @version 0.0.5
 * @since 2020/12/23
 */
public interface SyncLockInterface {

    Logger LOGGER = LoggerFactory.getLogger(SyncLockInterface.class);

    /**
     * 设置并发锁lua脚本
     */
    String SET_LOCK_LUA_CODE = "if (redis.call('exists', KEYS[1]) == 0) then redis.call('hset', KEYS[1], ARGV[1], 1); redis.call('pexpire', KEYS[1], ARGV[2]); return 'OK'; end;"
            + " if (redis.call('hexists', KEYS[1], ARGV[1]) == 1) then "
            + "local ttl = redis.call('pttl', KEYS[1])"
            + "redis.call('hincrby', KEYS[1], ARGV[1], 1); redis.call('pexpire', KEYS[1], ARGV[2] + ttl); return 'OK'; end;"
            + " return 'ERROR';";

    /**
     * 接触并发锁lua脚本
     */
    String REMOVE_LOCK_LUA_CODE = "if (redis.call('exists', KEYS[1]) == 0) then return 'OK'; end;"
            + "if (redis.call('hexists', KEYS[1], ARGV[1]) == 0) then return 'OK';end; "
            + "local counter = redis.call('hincrby', KEYS[1], ARGV[1], -1); "
            + "if (counter > 0) then return 'OK'; else redis.call('del', KEYS[1]);  return 'OK'; end;"
            + " return 'ERROR';";

    /**
     * <p>
     * 尝试获取同步锁（不重试）
     * </p>
     *
     * @param lockKey 同步锁名
     * @param serial  同步钥匙内容
     * @param expire  同步锁时长
     * @return 是否获取到同步锁
     */
    boolean tryLock(Object lockKey, Object serial, long expire);

    /**
     * <p>
     * 获取同步锁（重试）
     * </p>
     *
     * @param lockKey  同步锁名
     * @param serial   同步钥匙内容
     * @param expire   同步锁时长
     * @param waittime 等待时长
     * @return 是否获取到同步锁
     */
    default void getLock(Object lockKey, Object serial, long expire, long waittime) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() <= startTime + waittime * 1000) {
            if (tryLock(lockKey, serial, expire)) {
                return;
            } else {
                try {
                	//计算休眠时长，避免活锁
                	long sleepTime = 100L + ((Double)(Math.random() * 50)).longValue();
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    LOGGER.warn("线程休眠异常");
                }
            }
        }
        throw AppBusyException.create("获取资源锁超时！");
    }

    /**
     * <p>
     * 释放同步锁
     * </p>
     *
     * @param lockKey 同步锁名
     * @param serial  同步钥匙内容
     * @return 释放锁结果
     */
    boolean releaseLock(Object lockKey, Object serial);
}
