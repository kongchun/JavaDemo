/**
 * 
 */
package com.kedacom.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kedacom.demo.dao.UserDao;
import com.kedacom.demo.model.User;

/**
 * @author lihongguang
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	private PasswordEncoder encoder = new BCryptPasswordEncoder();

	/*
	 * (non-Javadoc)
	 * @see com.kedacom.demo.service.UserService#checkName(java.lang.String)
	 */
	@Override
	public Boolean checkName(String username) {
		if (this.userDao.getUser(username) == null) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.kedacom.demo.service.UserService#saveUser(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Integer saveUser(String username, String password) {
		User user = new User();
		user.setUsername(username);
		String hashedPassword = this.encoder.encode(password);
		user.setPassword(hashedPassword);
		user.setRole("user");
		return this.userDao.insertUser(user);
	}

	/*
	 * (non-Javadoc)
	 * @see com.kedacom.demo.service.UserService#listAllUser()
	 */
	@Override
	public List<User> listAllUser() {
		return this.userDao.listUser();
	}

	/*
	 * (non-Javadoc)
	 * @see com.kedacom.demo.service.UserService#editPassword(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Integer editPassword(String username, String oldPassword, String newPassword) {
		User user = this.userDao.getUser(username);
		if (this.encoder.matches(oldPassword, user.getPassword())) {
			String hashedNewPassword = this.encoder.encode(newPassword);
			user.setPassword(hashedNewPassword);
			return this.userDao.updateUser(user);
		} else {
			return -1;
		}
	}

}
