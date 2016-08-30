/**
 * 
 */
package cs544.project.share2care.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cs544.project.share2care.domain.Member;
import cs544.project.share2care.domain.User;
import cs544.project.share2care.domain.UserRole;
import cs544.project.share2care.service.IMemberService;
import cs544.project.share2care.service.IUserService;
import cs544.project.share2care.service.impl.UserServiceImpl;

/**
 * @author Dilip
 *
 */
@Controller
@RequestMapping("/login")
public class UserController {
	Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	IUserService userService;

	@Autowired
	IMemberService memberService;	
	
	@RequestMapping("/handleLogin")
	public String handleLogin() {
		String view = "";
		if (userHasAuthority("ROLE_ADMIN")) {
			view = "/admin/dashboard";
			logger.info("admin logged into system");
		} else if (userHasAuthority("ROLE_USER")) {
			view = "/user/dashboard";
			logger.info("user logged into system");
		}
		return "redirect:" + view;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "/users/user/signup";
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signupProcess(User user, RedirectAttributes redirectAttrs, Model model){
		if(user!=null){
			User usr = new User();
			usr = userService.getUserByUsername(user.getUsername());
			if(usr!=null){
				model.addAttribute("msg", "User Already Exists");
				return "/users/user/error";
			}
		}
		user.setRole(UserRole.ROLE_USER);
		userService.saveNewUser(user);
		
		Member member = new Member();
		member.setFirstName(user.getUsername());
		member.setEmail(user.getEmail());
		memberService.saveMember(member);
		
		return "/users/user/thankyou";
	}
	
	public boolean userHasAuthority(String authority) {
		List<GrantedAuthority> authorities = getUserAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (authority.equals(grantedAuthority.getAuthority())) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<GrantedAuthority> getUserAuthorities() {
		return (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}
}
