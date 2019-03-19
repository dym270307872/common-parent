package cn.dyaoming.listeners;


import org.apache.logging.log4j.core.config.Configurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;


/**
 * <p>
 * 动态日志工具配置监听器
 * </p>
 *
 * @author DYAOMING
 * @version V1.0
 * @since 2019-3-13
 */
@WebListener // 在此注明以下类是监听器
public class Log4j2ConfigListener implements ServletContextListener {

	private static HashMap<String, String> paramMap = new HashMap<String, String>();

	static {
		paramMap.put("Log4j2", "classpath:config/log4j2.xml");
		paramMap.put("isLog4jAutoInitializationDisabled", "false");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}



	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		for(String key : paramMap.keySet()) {
			Configurator.initialize(key, getContextParam(arg0, key));
		}
	}



	@SuppressWarnings("unchecked")
	private String getContextParam(ServletContextEvent event, String paramName) {
		String value = null;
		try {

			value = event.getServletContext().getInitParameter(paramName);

		} catch(Exception e) {
			e.printStackTrace();
		}
		if (value == null) {
			value = paramMap.get(paramName);
		}
		if ("Log4j2".equals(paramName)) {
			value = trimValue(value);
		}

		return value;
	}



	@SuppressWarnings("unchecked")
	private String trimValue(String value) {
		value = "classpath:" + value;
		return value.replaceAll("classpath:classpath:", "classpath:");
	}

}