/**
 * 
 */
package com.kedacom.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kedacom.demo.service.UserSecurityService;

/**
 * Spring Security配置类
 * @author lihongguang
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserSecurityService userSecurityService;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.
	 * config.annotation.web.builders.WebSecurity)
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.
	 * config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/login", "/validate/failure", "/validate/register").permitAll()
				// admin需要管理员权限
				.antMatchers("/admin/**").hasRole("admin")
				// 所有请求都需要验证
				.anyRequest().authenticated()
				// 登录设置：登录页URL，登录处理URL
				.and().formLogin().loginPage("/login").loginProcessingUrl("/validate")
				// HTTP请求用户名和密码的参数
				.usernameParameter("username").passwordParameter("password")
				// 默认验证成功URL（第二个参数为alwaysUse，默认false。为true时会在成功后总是跳转该URL，否则会跳转之前拦截的URL）
				.defaultSuccessUrl("/validate/success", true).failureUrl("/validate/failure")
				// 登出设置
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
		// .and().csrf().disable();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.
	 * config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userSecurityService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
