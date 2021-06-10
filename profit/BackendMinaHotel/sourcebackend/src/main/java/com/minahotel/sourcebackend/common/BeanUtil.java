package com.minahotel.sourcebackend.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * BeanUtil is class to get one @Bean in class normal not management by Spring
 * in use in class normal JwtUtilsCustomize
 * @author Peter
 *
 */
@Service
public class BeanUtil implements ApplicationContextAware {
	
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}