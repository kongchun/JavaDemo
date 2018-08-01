package com.kedacom.springamqp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring DispatcherServlet初始化
 * @author lihongguang
 * @date 2018年7月30日
 */

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()
	 */

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {
				RootConfig.class
		};
	}

	/**
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
	 */

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {
				ServletConfig.class
		};
	}

	/**
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 */

	@Override
	protected String[] getServletMappings() {
		return new String[] {
				"/"
		};
	}

}
