package com.muntu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.muntu.service.UserModelService;

@Controller("adminController")
public class AdminController {

	@Autowired
	private UserModelService usermodelService;

	// @RequestMapping("/welcome")
	// public String welcome() {
	// return "welcome1";
	// }
	//
	@RequestMapping("/admin")
	public String adminHome() {
		return "admin";
	}

	@RequestMapping("/addAccount")
	public String addAccount() {
		return "addAccount";
	}

}
