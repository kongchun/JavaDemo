/**
 * 
 */
package com.kedacom.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kedacom.demo.dao.UserDao;
import com.kedacom.demo.model.CustomUserDetails;
import com.kedacom.demo.model.User;

/**
 * Spring Security用户服务实现类
 * @author lihongguang
 */
@Service
public class UserSecurityService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userDao.getUser(username);
		return new CustomUserDetails(user);
	}

}
