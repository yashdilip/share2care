/**
 * 
 */
package cs544.project.share2care.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import cs544.project.share2care.domain.Member;
import cs544.project.share2care.domain.User;
import cs544.project.share2care.service.ICircleService;
import cs544.project.share2care.service.IMemberService;
import cs544.project.share2care.service.IUserService;

/**
 * @author Dilip
 *
 */
@Controller
@RequestMapping("/user")
public class MemberController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IMemberService memberService;

	@Autowired
	private ICircleService circleService;
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String showMemberDashboard(Model model, Principal principal, HttpSession session) {
//		model.addAttribute("firstname", principal.getName());
		session.setAttribute("member", memberService.getLoggedInMemeberByMemberName(principal.getName()));
		return "/users/user/user_dash";
	}

	@RequestMapping(value = "/friend/search", method = RequestMethod.GET)
	public String showFriendLists(Model model, Principal principal) {
		Member member = memberService.getLoggedInMemeberByMemberName(principal.getName());
		Integer memberId = Integer.valueOf(member.getMemberId());
		List<Member> members = memberService.findAllMembersNotMe(memberId);
		model.addAttribute("members", members);
		return "friendlist";
	}
	
/*	@RequestMapping(value="/friend/add/{memberId}", method = RequestMethod.POST)
	public String addFriend(Member member, Principal principal){
		circleService.addMember(member);
		return "redirect:/user/friend/search";
	}*/
	
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
	public String showProfile(Model model, Principal principal, HttpSession session){
		String name = principal.getName();
		Member member = memberService.getLoggedInMemeberByMemberName(((Member)session.getAttribute("member")).getFirstName());
		model.addAttribute("member", member);
		return "/users/user/memberprofile";
	}
	
	@RequestMapping(value="/profile", method = RequestMethod.POST)
	public String editProfile(Member member, Principal principal, HttpSession session) throws IOException{

		Member m = memberService.getLoggedInMemeberByMemberName(((Member)session.getAttribute("member")).getFirstName());
		
		//m.setProfilePictures(myFile.getBytes());
		m.setFirstName(member.getFirstName());
		m.setLastName(member.getLastName());
		m.setEmail(member.getEmail());
		m.setPhoneNumber(member.getPhoneNumber());
		m.setProfilePictures(member.getProfilePictures());
		//m.setImageLocation(fileurl);
		m.setAddress(member.getAddress());
		m.setCircles(member.getCircles());
		m.setEvents(member.getEvents());
		m.setResources(member.getResources());
		
		session.setAttribute("member", m);
		memberService.saveMember(m);

		if(principal.getName().equalsIgnoreCase(member.getFirstName())){

		}else{
			User user = userService.getUserByUsername(principal.getName());
			user.setUsername(member.getFirstName().toLowerCase());
			userService.saveNewUser(user);
			
			
		}
		return "redirect:/user/dashboard";
	}
	
	
	@RequestMapping(value="/imageupload", method=RequestMethod.GET)
	public String uploadImage(Model model){
		return "";
	}
	
	@RequestMapping(value="/imageupload", method=RequestMethod.POST)
	public String uploadImageProcess(@RequestParam("myFile") MultipartFile myFile, Principal principal) throws IOException{
		Member member = memberService.getLoggedInMemeberByMemberName(principal.getName());
		member.setProfilePictures(myFile.getBytes());
		memberService.saveMember(member);
		return "redirect:/user/dashboard";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	
}
