/**
 * 
 */
package cs544.project.share2care.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cs544.project.share2care.domain.Circle;
import cs544.project.share2care.domain.Member;
import cs544.project.share2care.service.ICircleService;
import cs544.project.share2care.service.IMemberService;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private ICircleService circleService;
	
	@Autowired
	private IMemberService memberService;

	@RequestMapping(value = "/advance", method = RequestMethod.POST)
	public String searchProject(@RequestParam("searchText") String searchText, Model model, RedirectAttributes redirectAttributes) {
		List<Member> memberlist = new ArrayList<Member>();
		List<Circle> circlelist = new ArrayList<Circle>();
		memberlist = memberService.searchMembersByKeyword(searchText);
		circlelist = circleService.searchCirclesByKeywords(searchText);
		
		//String viewPath = "/users/user/searchresult";
		//ModelAndView modelAndView = new ModelAndView(viewPath);
		//modelAndView.addObject("circlelist", circlelist);
		//modelAndView.addObject("memberlist", memberlist);
		model.addAttribute("circlelist", circlelist);
		model.addAttribute("memberlist", memberlist);
		
		return "/users/user/searchresult";
	}
}
