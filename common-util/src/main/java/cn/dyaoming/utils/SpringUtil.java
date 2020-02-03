package cn.dyaoming.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;


/**
 * <p>
 * spring工具类
 * </p>
 * 
 * @author DYAOMING
 * @since 2019-04-21
 * @version 0.0.3
 */
@Component("dymSpringUtil")
public class SpringUtil implements ApplicationContextAware {
    
    private static ApplicationContext applicationContext;

    private static Environment environment;



    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }



    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }



    public static Environment getEnvironment() {
        return environment;
    }



    public void setEnvironment(Environment environment) {
        SpringUtil.environment = environment;
    }



    /**
     * 获取bean
     * 
     * @param <T> bean对应类型
     * @param name bean名称
     * @return bean实例
     * @throws BeansException 异常类
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);
    }



    /**
     * 注册bean
     * 
     * @param <T> bean对应类型
     * @param name bean名称
     * @return bean实例
     * @throws BeansException 异常类
     */
    @SuppressWarnings("unchecked")
    public static void setBean(String name) throws BeansException {
        ConfigurableApplicationContext cac = (ConfigurableApplicationContext) applicationContext;
//TODO 注册bean方法未实现。
        //        applicationContext.getBean(name);
    }


    /**
     *<p>查询系统变量</p>
     * 
     * @param propertyName String类型 变量名
     * @return String类型 变量值
     */
    public static String getProperty(String propertyName) {
        return environment.getProperty(propertyName);
    }


    /**
     * <p>查询系统变量</p>
     * 
     * @param propertyName String类型 变量名
     * @param defaultValue String类型 缺省变量值
     * @return String类型 变量值
     */
    public static String getProperty(String propertyName, String defaultValue) {
        return environment.getProperty(propertyName, defaultValue);
    }


    /**
     * <p>添加系统变量</p>
     * 
     * @param propertyName String类型 变量名
     * @param propertyValue String类型 变量值
     */
    public static void setProperty(String propertyName, String propertyValue) {

        ConfigurableEnvironment ce = (ConfigurableEnvironment) environment;
        MutablePropertySources mps = ce.getPropertySources();
        // TODO 添加系统变量未实现 
//        mps.addFirst(new PropertySource());
    }
}