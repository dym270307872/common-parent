package cn.dyaoming.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
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
    
    private static ApplicationContext staticApplicationContext;
    
    private static Environment staticEnvironment;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.setStaticApplicationContext(applicationContext);
    }


    private synchronized static void setStaticApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.staticApplicationContext = applicationContext;
    }
    

    public void setEnvironment(Environment environment) {
        SpringUtil.setStaticEnvironment(environment);
    }

    public synchronized static void setStaticEnvironment(Environment environment) {
        SpringUtil.staticEnvironment = environment;
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
        return (T) staticApplicationContext.getBean(name);
    }


    /**
     *<p>查询系统变量</p>
     * 
     * @param propertyName String类型 变量名
     * @return String类型 变量值
     */
    public static String getProperty(String propertyName) {
        return staticEnvironment.getProperty(propertyName);
    }


    /**
     * <p>查询系统变量</p>
     * 
     * @param propertyName String类型 变量名
     * @param defaultValue String类型 缺省变量值
     * @return String类型 变量值
     */
    public static String getProperty(String propertyName, String defaultValue) {
        return staticEnvironment.getProperty(propertyName, defaultValue);
    }


    /**
     * <p>添加系统变量</p>
     * 
     * @param propertyName String类型 变量名
     * @param propertyValue String类型 变量值
     */
    public static void setProperty(String propertyName, String propertyValue) {

        ConfigurableEnvironment ce = (ConfigurableEnvironment) staticEnvironment;
        MutablePropertySources mps = ce.getPropertySources();
        // TODO 添加系统变量未实现 
//        mps.addFirst(new PropertySource());
    }
}