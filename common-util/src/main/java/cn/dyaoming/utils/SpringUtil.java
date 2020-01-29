//package cn.dyaoming.utils;
//
//
//
//
///**
// * <p>
// * spring工具类
// * </p>
// * 
// * @author DYAOMING
// * @since 2019-04-21
// * @version 0.0.1
// */
//@Component
//public class SpringUtil implements ApplicationContextAware {
//	private static ApplicationContext applicationContext;
//
//
//
//	public void setApplicationContext(ApplicationContext applicationContext) {
//		SpringUtil.applicationContext = applicationContext;
//	}
//
//
//
//	public static ApplicationContext getApplicationContext() {
//		return applicationContext;
//	}
//
//
//
//	/**
//	 * 获取bean
//	 * 
//	 * @param <T> bean对应类型
//	 * @param name bean名称
//	 * @return bean实例
//	 * @throws BeansException 异常类
//	 */
//	public static <T> T getBean(String name) throws BeansException {
//		return (T) applicationContext.getBean(name);
//	}
//
//}