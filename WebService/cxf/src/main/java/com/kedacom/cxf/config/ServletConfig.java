
/**
 * @(#)ServletConfig.java 2018年8月8日 Copyright 2018 it.kedacom.com, Inc. All
 *                        rights reserved.
 */

package com.kedacom.cxf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * (用一句话描述类的主要功能)
 * @author lihongguang
 * @date 2018年8月8日
 */

@Configuration
@EnableWebMvc
@ComponentScan("com.kedacom.cxf.controller")
public class ServletConfig implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

	/**
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#configureDefaultServletHandling(org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer)
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
		configurer.enable();
	}
}
