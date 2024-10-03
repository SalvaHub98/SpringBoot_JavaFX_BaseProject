package com.base.core.application;

import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationData {

	public static ConfigurableApplicationContext springContext;
	
	public static ConfigurableApplicationContext getSpringContext() {
		return springContext;
	}
	public static void setSpringContext(ConfigurableApplicationContext springContext) {
		ApplicationData.springContext = springContext;
	}
	
}
