/**
 * <p>悲观锁注解切片类</p>
 * @author DYAOMING
 */
package cn.dyaoming.sync.aspects;


import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.dyaoming.errors.AppBusyException;
import cn.dyaoming.sync.annotations.SyncLock;
import cn.dyaoming.sync.interfaces.SyncLockInterface;
import cn.dyaoming.utils.GeneratorKeyUtil;

/**
 * 围绕切片创建悲观锁
 * @author DYAOMING
 */
@Aspect
@Component
public class SyncLockAspect {

    /**
     * 日志常量声明
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SyncLockAspect.class);
    
    
    
    @Autowired
    private SyncLockInterface syncLockInterface;
    
	@Around("@annotation(syncLock)")
    public Object around(ProceedingJoinPoint point, SyncLock syncLock) {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        String key = strClassName + strMethodName;
        // 发送异步日志事件
        String serial = GeneratorKeyUtil.getSeral();
        Object obj;
        try {
            if (syncLockInterface.getLock(key, serial, 2L, 2L)) {
                LOGGER.debug("已获得锁，进行操作！");
                try {
                    return point.proceed();
                } catch (Throwable e) {
                    // e.printStackTrace();
                }
            } else {
                LOGGER.debug("未获得锁，抛出异常！");
                throw new AppBusyException();
            }

        } finally {
            syncLockInterface.releaseLock(key, serial);
        }
        return null;
    }

}
