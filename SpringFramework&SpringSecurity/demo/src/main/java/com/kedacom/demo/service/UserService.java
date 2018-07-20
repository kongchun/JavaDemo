/**
 * 
 */
package com.kedacom.demo.service;

import java.util.List;

import com.kedacom.demo.model.User;

/**
 * 用户账户操作接口
 * @author lihongguang
 */
public interface UserService {

	/**
	 * 检查用户名是否可用
	 * @param name
	 * @return true-可用；false-用户名已被占用
	 */
	Boolean checkName(String username);

	/**
	 * 新增用户
	 * @param name
	 * @param password
	 * @return
	 */
	Integer saveUser(String username, String password);

	/**
	 * 列出所有用户
	 * @return
	 */
	List<User> listAllUser();

	/**
	 * 修改密码
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 * @return 1-成功；0-失败；-1-原密码错误
	 */
	Integer editPassword(String username, String oldPassword, String newPassword);
}
