/**
 * 
 */
package com.kedacom.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kedacom.demo.model.User;
import com.kedacom.demo.service.UserService;
import com.kedacom.demo.util.UserNameUtil;
import com.kedacom.demo.vo.UserOperationVO;
import com.kedacom.demo.vo.UserVO;

/**
 * @author lihongguang
 */
@RestController
@RequestMapping("content")
public class ContentController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "getUserList", method = RequestMethod.GET)
	public List<UserVO> getUserList() {
		List<User> userList = this.userService.listAllUser();
		List<UserVO> returnList = new ArrayList<UserVO>();
		for (int i = 0; i < userList.size(); i++) {
			User currentUser = userList.get(i);
			returnList
					.add(new UserVO(currentUser.getId().toString(), currentUser.getUsername(), currentUser.getRole()));
		}
		return returnList;
	}

	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public UserOperationVO changePassword(String origin, String password) {
		Integer result = this.userService.editPassword(UserNameUtil.getCurrentName(), origin, password);
		if (result == 1) {
			return new UserOperationVO(1, "Success");
		} else if (result == -1) {
			return new UserOperationVO(-1, "Authentication Failed");
		} else {
			return new UserOperationVO(0, "Failure");
		}
	}
}
