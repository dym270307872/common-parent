/**
 * <p>悲观锁注解切片类</p>
 * @author DYAOMING
 */
package cn.dyaoming.sync.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.dyaoming.sync.annotations.SyncLock;
import cn.dyaoming.sync.interfaces.SyncLockInterface;

/**
 * 围绕切片创建悲观锁
 * @author DYAOMING
 */
@Aspect
@Component
public class SyncLockAspect {

    @Autowired
    private SyncLockInterface syncLockInterface;
    
	@Around("@annotation(syncLock)")
	public Object around(ProceedingJoinPoint point, SyncLock syncLock) {
		String strClassName = point.getTarget().getClass().getName();
		String strMethodName = point.getSignature().getName();
		String key = "";
		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Object obj;
        try {
            syncLockInterface.getLock(key,String.valueOf(startTime));
            System.out.println("已获得锁，进行操作："+startTime);
            return point.proceed();
            
        } catch (Throwable e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
        }finally {
            syncLockInterface.releaseLock(key,String.valueOf(startTime));
        }
		return null;
	}

}
