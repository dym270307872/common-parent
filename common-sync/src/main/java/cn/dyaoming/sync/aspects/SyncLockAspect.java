/**
 * <p>悲观锁注解切片类</p>
 * @author DYAOMING
 */
package cn.dyaoming.sync.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import cn.dyaoming.sync.annotations.SyncLock;

/**
 * 围绕切片创建悲观锁
 * @author DYAOMING
 */
@Aspect
@Component
public class SyncLockAspect {

	@Around("@annotation(syncLock)")
	public Object around(ProceedingJoinPoint point, SyncLock syncLock) {
		String strClassName = point.getTarget().getClass().getName();
		String strMethodName = point.getSignature().getName();

		// 发送异步日志事件
		Long startTime = System.currentTimeMillis();
		Object obj;
        try {
            System.out.println("锁内操作："+startTime);
            return point.proceed();
            
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            
        }
		return null;
	}

}
