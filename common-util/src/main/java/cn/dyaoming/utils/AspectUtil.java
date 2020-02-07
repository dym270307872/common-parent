//package cn.dyaoming.utils;
//
//
//import com.alibaba.fastjson.JSON;
//import org.aspectj.lang.JoinPoint;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.lang.reflect.Method;
//
//
///**
// * <p>
// * aop切面工具类
// * </p>
// * 
// * @author DYAOMING
// * @since 2019-04-21
// * @version 0.0.1
// */
//public class AspectUtil {
//
//	// 本地异常日志记录对象
//	private static final Logger LOGGER = LoggerFactory.getLogger(AspectUtil.class);
//
//
//
//	/**
//	 * 通过JoinPoint获取类名
//	 *
//	 * @param joinPoint 切点
//	 * @return 包名+类名
//	 * @throws Exception 异常
//	 */
//	public static String getClass(JoinPoint joinPoint) throws Exception {
//		// 获取类名
//		return joinPoint.getTarget().getClass().getName();
//	}
//
//
//
//	/**
//	 * 通过JoinPoint获取方法
//	 *
//	 * @param joinPoint 切点
//	 * @return 方法名
//	 * @throws Exception 异常
//	 */
//	public static String getMethod(JoinPoint joinPoint) throws Exception {
//		return joinPoint.getSignature().getName();
//	}
//
//
//
//	/**
//	 * 通过JoinPoint获取参数
//	 *
//	 * @param joinPoint 切点
//	 * @return 方法描述
//	 * @throws Exception 异常
//	 */
//	public static String getParam(JoinPoint joinPoint) throws Exception {
//		// 获取请求参数内容
//		return JSON.toJSONString(joinPoint.getArgs());
//	}
//
//
//
//	/**
//	 * 通过JoinPoint获取方法描述
//	 *
//	 * @param joinPoint 切点
//	 * @return 方法描述
//	 * @throws Exception 异常
//	 */
//	public static String getMethodDescription(JoinPoint joinPoint) throws Exception {
//		// 获取类的权限定名
//		String targetName = joinPoint.getTarget().getClass().getName();
//
//		String methodName = joinPoint.getSignature().getName();
//
//		Object[] arguments = joinPoint.getArgs();
//		Class targetClass = Class.forName(targetName);
//		Method[] methods = targetClass.getMethods();
//		String description = "";
//		for(Method method : methods) {
//			if (method.getName().equals(methodName)) {
//				Class[] clazzs = method.getParameterTypes();
//				if (clazzs.length == arguments.length) {
//					// description =
//					// method.getAnnotation(MyBusinessLog.class).description();
//					break;
//				}
//			}
//		}
//		return description;
//	}
//
//
//
//	/**
//	 * 通过JoinPoint获取注释内容
//	 *
//	 * @param joinPoint 切点
//	 * @return 方法描述
//	 * @throws Exception 异常
//	 */
//	public static String getAnnotation(JoinPoint joinPoint) throws Exception {
//		// 获取类的权限定名
//		String targetName = joinPoint.getTarget().getClass().getName();
//
//		String methodName = joinPoint.getSignature().getName();
//
//		Object[] arguments = joinPoint.getArgs();
//		Class targetClass = Class.forName(targetName);
//		Method[] methods = targetClass.getMethods();
//		String description = "";
//		for(Method method : methods) {
//			if (method.getName().equals(methodName)) {
//				Class[] clazzs = method.getParameterTypes();
//				if (clazzs.length == arguments.length) {
//					// description =
//					// method.getAnnotation(MyBusinessLog.class).description();
//					break;
//				}
//			}
//		}
//		return description;
//	}
//
//}
