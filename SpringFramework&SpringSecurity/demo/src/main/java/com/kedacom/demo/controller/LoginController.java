/**
 * 
 */
package com.kedacom.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kedacom.demo.service.UserService;
import com.kedacom.demo.vo.UserOperationVO;

/**
 * 登录控制器
 * @author lihongguang
 */
@RestController
@RequestMapping("validate")
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping("success")
	public UserOperationVO success() {
		return new UserOperationVO(1, "Success");
	}

	@RequestMapping("failure")
	public UserOperationVO failure(HttpServletRequest request) {
		AuthenticationException ae = (AuthenticationException) request.getSession()
				.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		return new UserOperationVO(0, ae.getMessage());
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public UserOperationVO register(String username, String password) {
		if (this.userService.checkName(username)) {
			if (this.userService.saveUser(username, password) == 1) {
				return new UserOperationVO(1, "Success");
			} else {
				return new UserOperationVO(0, "Failure");
			}
		} else {
			return new UserOperationVO(0, "Failure");
		}
	}

}
