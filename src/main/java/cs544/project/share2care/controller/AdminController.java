package cs544.project.share2care.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs544.project.share2care.domain.User;
import cs544.project.share2care.service.impl.UserServiceImpl;

/**
 * Created by Dilip on 8/22/2016.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserServiceImpl userService;
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard() {
		return "/users/admin/admin_dash";
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.GET)
	public String saveUser(Model model) {
		model.addAttribute("user", new User());
		return "/users/admin/useradd";
	}

	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public String saveUserForm(User user, Model model) {
		userService.saveNewUser(user);
		System.out.println(user.getName());
		return "redirect:/admin/dashboard";
	}
}
