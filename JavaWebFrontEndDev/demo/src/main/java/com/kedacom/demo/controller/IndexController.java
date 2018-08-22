/**
 * 
 */
package com.kedacom.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kedacom.demo.util.UserNameUtil;

/**
 * 主页
 * @author lihongguang
 */
@Controller
public class IndexController {

	@RequestMapping("login")
	public String index() {
		return "login";
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}

	@RequestMapping("content")
	public ModelAndView content() {
		ModelAndView mav = new ModelAndView("content");
		mav.addObject("name", UserNameUtil.getCurrentName());
		return mav;
	}

	@RequestMapping("admin")
	public String admin(Model model) {
		model.addAttribute("name", UserNameUtil.getCurrentName());
		return "admin";
	}

}
