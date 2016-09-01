/**
 * 
 */
package cs544.project.share2care.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cs544.project.share2care.domain.Circle;
import cs544.project.share2care.domain.Member;
import cs544.project.share2care.service.ICircleService;
import cs544.project.share2care.service.IMemberService;

/**
 * @author Dilip
 *
 */
@Controller
@RequestMapping(value="/circle")
public class CircleController {
	@Autowired
	private ICircleService circleService;
	
	@Autowired
	private IMemberService memberService;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String createNewCircle(Model model){
		model.addAttribute("circle", new Circle());
		return "/users/user/newcircle";
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST)
	public String createNewCircleProcess(Circle circle, Model model, HttpSession session){
		Integer memberId = Integer.valueOf(((Member)session.getAttribute("member")).getMemberId());
		Member member = memberService.getMemberByMemberId(memberId);
		
		List<Circle> circles = circleService.findAllCircles(memberId);
		for(Circle c: circles){
			if(circle.getCircleName().equals(c.getCircleName())){
				model.addAttribute("msg", "Circle Name Already exists");
				return "redirect:/circle/create";
			}
		}		
		circleService.saveNewCircle(circle, member);
		//memberService.addMemberIntoCircle(member, circle);
		return "redirect:/user/dashboard";
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String viewCircle(Model model, HttpSession session){
		Integer memberId = Integer.valueOf(((Member)session.getAttribute("member")).getMemberId());
		List<Circle> circlelist = circleService.findAllCircles(memberId);
		model.addAttribute("circles", circlelist);
		model.addAttribute("msg", "You Have!");
		return "/users/user/circlelist";
	}
	
	@RequestMapping(value="/view/{circleId}", method=RequestMethod.GET)
	public String viewCircleDetails(@PathVariable("circleId") Integer circleId, Model model, HttpSession session){
		Circle circle = circleService.findOneCircleByCircleId(circleId);
		model.addAttribute("circle", circle);
		return "/users/user/circledetails";
	}
	
	@RequestMapping(value="/edit/{circleId}", method=RequestMethod.GET)
	public String editCircle(@PathVariable("circleId") Integer circleId, Model model, HttpSession session){
		Circle circle = circleService.findOneCircleByCircleId(circleId);
		model.addAttribute("circle", circle);
		return "/users/user/ceditdetails";
	}
	@RequestMapping(value="/edit/{circleId}", method=RequestMethod.POST)
	public String editCircleProcess(@PathVariable("circleId") String circleId, Circle circle, Model model, HttpSession session){
		Member member = (Member)session.getAttribute("member");
		circle.setOwner(member);
		circleService.saveUpdateCircle(circle, member);
		return "redirect:/user/dashboard";
	}
	
	@RequestMapping(value="/discover", method=RequestMethod.GET)
	public String viewAllCircle(Model model, HttpSession session){
		Integer memberId = ((Member)session.getAttribute("member")).getMemberId();
		List<Circle> circlelist = circleService.findAllCirclesOfAppNotOwnedMyMember(memberId);
		model.addAttribute("circles", circlelist);
		model.addAttribute("msg", "of the Network");
		return "/users/user/circlelistofnetwork";
	}
	
	@RequestMapping(value="/join/{circleId}", method=RequestMethod.GET)
	public String getCircleDetails(@PathVariable("circleId") Integer circleId, Model model){
		model.addAttribute("circle", circleService.findOneCircleByCircleId(circleId));
		return "/users/user/circledetails2join";
	}
	
	@RequestMapping(value="/join/{circleId}", method=RequestMethod.POST)
	public String joinCircle(@PathVariable("circleId") String circleId, @Valid Circle circle, BindingResult result, Model model, HttpSession session, Errors errors){
		if(result.hasErrors()){
			errors.reject("join", "already joined");
			return "redirect:/circle/join/"+circle.getCircleId();
		}
		Integer memberId = Integer.valueOf(((Member) session.getAttribute("member")).getMemberId());
		String msg = circleService.joinCircle(Integer.valueOf(circleId), memberId);
		model.addAttribute("msg", msg);
		if(msg.contains("duplicate")){
			return "redirect:/circle/join/"+circle.getCircleId();
		}
		return "redirect:/user/dashboard";
	}
}
