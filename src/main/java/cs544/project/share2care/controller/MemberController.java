/**
 * 
 */
package cs544.project.share2care.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs544.project.share2care.domain.Member;
import cs544.project.share2care.domain.User;
import cs544.project.share2care.service.impl.MemberServiceImpl;
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
	
	@Autowired
	private MemberServiceImpl memberService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String showMemberDashboard(Model model) {
		return "/users/user/user_dash";
	}

	@RequestMapping(value = "/friend/search", method = RequestMethod.GET)
	public String showFriendLists(Model model) {
		List<Member> members = memberService.findAllMembers();
		model.addAttribute("members", members);
		return "showmembers";
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
	
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public String showProfile(Model model, Principal principal){
		String name = principal.getName();
		Member member = memberService.getLoggedInMemeberByMemberName(name);
		model.addAttribute("member", member);
		return "/users/user/memberprofile";
	}
	@RequestMapping(value="/profile", method = RequestMethod.POST)
	public String editProfile(Member member, Principal principal){
		if(principal.getName().equalsIgnoreCase(member.getFirstName())){
			memberService.saveMember(member);
		}else{
			User user = userService.getUserByUsername(principal.getName());
			user.setUsername(member.getFirstName().toLowerCase());
			userService.saveNewUser(user);
			memberService.saveMember(member);
		}
		return "redirect:/user/dashboard";
	}
}
