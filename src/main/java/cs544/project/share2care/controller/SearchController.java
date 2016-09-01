/**
 * 
 */
package cs544.project.share2care.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public ModelAndView searchProject(@RequestParam("searchText") String searchText, RedirectAttributes redirectAttributes) {
		List<Member> memberlist = memberService.searchMembersByKeyword(searchText);
		List<Circle> circlelist = circleService.searchCirclesByKeywords(searchText);
		
		String viewPath = "/users/user/searchresult";
		ModelAndView modelAndView = new ModelAndView(viewPath);
		modelAndView.addObject("memberlist", memberlist);
		modelAndView.addObject("circlelist", circlelist);
		return modelAndView;
	}
}
