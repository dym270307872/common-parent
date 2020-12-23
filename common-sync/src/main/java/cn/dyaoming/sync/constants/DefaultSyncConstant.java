package cn.dyaoming.sync.constants;

/**
 * <p>同步默认常量类</p>
 * 
 * @author dym
 * @since 2020/12/23
 * @version 0.0.1
 */
public class DefaultSyncConstant {

    /**
     * 是否需要加密比对值
     */
    public final static String NEED_SECRET_CODE = "true";
    
    /**
     * 同步锁时长
     */
	public final static long SYNC_LOCK_TIME = 10L;
	
	/**
	 * 重试锁等待时长
	 */
	public final static long RETRY_LOCK_WAIT_TIME = 10L;
}
