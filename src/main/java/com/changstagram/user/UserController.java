package com.changstagram.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

	
	@RequestMapping("/sign_in_view")
	public String SignInView(Model model) {
		model.addAttribute("viewName", "user/sign_in");
		return "template/layout";
	}
	
	@RequestMapping("/sign_up_view")
	public String SignUpView(Model model)	{
		model.addAttribute("viewName", "user/sign_up");
		
		return "template/layout";
	}
	
}
