/**
 * 
 */
package cs544.project.share2care.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import cs544.project.share2care.domain.Circle;
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
	private Environment env;

	@Autowired
	private IUserService userService;

	@Autowired
	private IMemberService memberService;

	@Autowired
	private ICircleService circleService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String showMemberDashboard(Model model, Principal principal, HttpSession session) {
		// model.addAttribute("firstname", principal.getName());
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

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String showProfile(Model model, Principal principal, HttpSession session) {
		String name = principal.getName();
		Member member = memberService
				.getLoggedInMemeberByMemberName(((Member) session.getAttribute("member")).getFirstName());
		model.addAttribute("member", member);
		return "/users/user/memberprofile";
	}

	@RequestMapping(value = "/profile/{memberId}", method = RequestMethod.GET)
	public String showProfileOfMember(@PathVariable("memberId") Integer memberId, Model model, HttpSession session) {
		Member member = memberService.getMemberByMemberId(memberId);
		model.addAttribute("member", member);
		return "/users/user/memberprofiledetail";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String editProfile(Member member, Principal principal, HttpSession session) throws IOException {

		Member m = memberService
				.getLoggedInMemeberByMemberName(((Member) session.getAttribute("member")).getFirstName());

		// m.setProfilePictures(myFile.getBytes());
		m.setFirstName(member.getFirstName());
		m.setLastName(member.getLastName());
		m.setEmail(member.getEmail());
		m.setPhoneNumber(member.getPhoneNumber());
		m.setProfilePictures(member.getProfilePictures());
		// m.setImageLocation(fileurl);
		m.setAddress(member.getAddress());
		m.setCircles(member.getCircles());
		m.setEvents(member.getEvents());
		m.setResources(member.getResources());

		session.setAttribute("member", m);
		memberService.saveMember(m);

		if (principal.getName().equalsIgnoreCase(member.getFirstName())) {

		} else {
			User user = userService.getUserByUsername(principal.getName());
			user.setUsername(member.getFirstName().toLowerCase());
			userService.saveNewUser(user);

		}
		return "redirect:/user/dashboard";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
	public String uploadImage(Model model) {
		return "/users/user/fileupload";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)

	public String uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile, HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("member");
		String msg = "";
		try {
			System.out.println(uploadfile.getSize());
			// Get the filename and build the local file path
			String filename = uploadfile.getOriginalFilename();
			String directory = env.getProperty("netgloo.paths.uploadedFiles");
			String filepath = Paths.get(directory, filename).toString();

			member.setImageLocation(filepath);
			member.setProfilePictures(uploadfile.getBytes());
			memberService.saveMember(member);
			System.out.println(filepath + "-------> " + directory + " -------->" + filename);
			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		model.addAttribute("msg", msg);
		return "redirect:/user/dashboard";
		// return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/discover", method = RequestMethod.GET)
	public String viewAllMembersofApp(Model model, HttpSession session) {
		Integer memberId = ((Member) session.getAttribute("member")).getMemberId();
		List<Member> members = memberService.findAllMembersNotMe(memberId);
		model.addAttribute("members", members);
		return "/users/user/memberlists";
	}

	@RequestMapping(value = "/discover/{memberId}", method = RequestMethod.GET)
	public String viewMembersDetail(@PathVariable("memberId") Integer memberId, Model model, HttpSession session) {
		Member member = memberService.getMemberByMemberId(memberId);
		model.addAttribute("member", member);
		return "/users/user/memberprofile2add";
	}

	@RequestMapping(value = "/discover/addfriendtocircle/{memberId}", method = RequestMethod.GET)
	public String addToCircle(@PathVariable("memberId") int memberId, Model model, HttpSession session) {
		System.out.println("be friend is here");
		String msg = "";
		Member m = (Member) session.getAttribute("member");
		Circle circle = circleService.findAllCircles(m.getMemberId()).get(0);
		Member mem = memberService.getMemberByMemberId(memberId);
		if (circle != null) {
			msg = memberService.addMemberIntoCircle(mem, circle);
		}
		if (msg.contains("not saved")) {

		}
		model.addAttribute("msg", msg);
		return "redirect:/user/discover";

	}

	@RequestMapping(value = "/discover/circle/{circleId}", method = RequestMethod.GET)
	public String discoverOwnFriends(@PathVariable("circleId") Integer circleId, Model model) {
		List<Member> memberlist = memberService.getAllMemberOfACircle(circleId);
		model.addAttribute("memberlist", memberlist);

		return "/users/user/friendlist";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/discover/friends", method = RequestMethod.GET)
	public String showMyFriend(Model model, HttpSession session) {
		Integer memberId = ((Member) session.getAttribute("member")).getMemberId();
		List<Member> memberlist = memberService.allFriends(memberId);
		model.addAttribute("memberlist", memberlist);
		return "/users/user/allfriendlist";
	}

	@RequestMapping(value = "/image/{memberId}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable("memberId") Integer memberId) throws IOException {
		Member member = memberService.getMemberByMemberId(memberId);
		byte[] image = member.getProfilePictures();

		// Event event = eventService.findById(eventId);
		// byte[] imageContent = event.getEventPicture();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(image, headers, HttpStatus.OK);
	}

}
