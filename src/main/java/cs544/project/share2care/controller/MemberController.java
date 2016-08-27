/**
 * 
 */
package cs544.project.share2care.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs544.project.share2care.service.impl.UserServiceImpl;

/**
 * @author Dilip
 *
 */
@Controller
@RequestMapping("/user")
public class MemberController {

	@Autowired
	private UserServiceImpl userService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String showMemberDashboard(Model model) {
		return "/users/user/user_dash";
	}

	@RequestMapping(value = "/friend/list", method = RequestMethod.GET)
	public String showFriendLists(Model model) {
		return "friends";
	}

	@RequestMapping(value = "/verify/{userId}", method = RequestMethod.GET)
	public String confirmEmailAddress(@PathVariable("userId") Long userId, Model model) {
		System.out.println("i am here");
		String message = userService.verifyUserByEmail(userId);
		if (!message.isEmpty()) {
			model.addAttribute("msg", message);
			return "/users/user/thankyou";
		}
		model.addAttribute("message", "User not found!!");
		return "/users/user/error";
	}
}
