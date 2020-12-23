package cn.dyaoming.sync.interfaces;

/**
 * <p>
 * 同步锁方法定义接口类
 * </p>
 * 
 * @author dym
 * @since 2020/12/23
 * @version 0.0.1
 */
public interface SyncLockInterface {

	/**
	 * <p>
	 * 尝试获取同步锁（不重试）
	 * </p>
	 *
	 * @param key    同步锁名
	 * @param serial 同步钥匙内容
	 * @param expire 同步锁时长
	 * @return 是否获取到同步锁
	 */
	boolean tryLock(String key, String serial, long expire);

	/**
	 * <p>
	 * 获取同步锁（重试）
	 * </p>
	 *
	 * @param key      同步锁名
	 * @param serial   同步钥匙内容
	 * @param expire   同步锁时长
	 * @param waittime 等待时长
	 * @return 是否获取到同步锁
	 */
	boolean getLock(String key, String serial, long expire, long waittime);

	/**
	 * <p>
	 * 释放同步锁
	 * </p>
	 *
	 * @param key    同步锁名
	 * @param serial 同步钥匙内容
	 * @return 释放锁结果
	 */
	boolean releaseLock(String key, String serial);
}
