package com.bank.manager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("login")
public class LoginController {
	
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody
	public String loginPOST(@RequestParam(name="username", defaultValue="username") String username,
			@RequestParam(name="password", defaultValue="password") String password,
			@RequestParam(name="employee", defaultValue="unchecked", required=false) String employee)
	{
		return "YOU WANT TO LOGIN " + username + " " + password + " "+employee;
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String loginGET()
	{
		return "login";
	}
	
	
}
