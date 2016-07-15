package com.lucien.spirit.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring上下文工具类，获取spring容器，以访问容器中定义的其他bean.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:45:55
 * <p>Version: 1.0
 */
public class WebContextUtil implements ApplicationContextAware {
    
    /**
     * Spring应用上下文环境.
     */
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境.
     * @param applicationContext
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        WebContextUtil.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据名称获取Sping管理的对象.
     * @param name
     * @return Object 获取注册bean的实例
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 根据class获取Sping管理的对象.
     * @param clazz
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return applicationContext.getBean(clazz);
    }
}
