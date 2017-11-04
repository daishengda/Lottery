package com.dsd.lottery.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * bean工具类
 * @author acer-pc
 *
 */
public class SpringBeanUtil {

	private static BeanFactory beanFactory;

	static {
		init();
	}

	public static BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public static Object getBean(String beanId) {
		return (Object) beanFactory.getBean(beanId);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanId, Class<T> type) {
		return (T) beanFactory.getBean(beanId);
	}

	public static void init() {
		String[] filePaths = { "classpath*:config/spring-mvc.xml" };
		beanFactory = new FileSystemXmlApplicationContext(filePaths);
	}
}
