package cn.dyaoming.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>线程工具类</p>
 * 
 * @author dym
 * @since 2021/01/21
 * @version 0.0.1
 */
public class ThreadUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThreadUtil.class);
	
	/**
	 * <p>等待子线程结束</p>
	 *
	 */
	public static void wait4ChildThread() {
		Thread thisThread = Thread.currentThread();
		Thread[] childrenThreads = new Thread[Thread.activeCount()];
		Thread.enumerate(childrenThreads);
		try {
			for (Thread childThread : childrenThreads) {
				if (thisThread != childThread) {
					childThread.join();
				}
			}
		} catch (InterruptedException e) {
			LOGGER.warn("等待线程结束出现异常！");
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 100; i++) {
			// 构造一个线程并启动，取名为"my-thread"
//			Thread.sleep(i * 2);
			new Thread(() -> {
				try {
					Thread t = Thread.currentThread();
					System.out.println(t.getName() + "线程ID为:" + t.getId());
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}, "my-thread" + i).start();
		}

		// 拿到当前线程下所有子线程，找出名称为"my-thread"的线程并输出该线程的ID
		// 这串代码用到了activeCount和enumerate方法，在API的介绍中有详细说明
		Thread[] ts = new Thread[Thread.activeCount()];
		Thread.enumerate(ts);
		for (Thread t : ts) {
			System.out.println("从主线程中找到子线程名称为：" + t.getName() + ", 状态为: " + t.getState());
		}
	}
}
