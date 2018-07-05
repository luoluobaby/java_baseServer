package com.service.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {
	
	public static ApplicationContext static_content = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		static_content = applicationContext;
	}
	
	/**
	 * 获取对象
	 * @return
	 */
	public static ApplicationContext getContext(){  
        return static_content;  
	}  

}
