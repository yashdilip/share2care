package cs544.project.share2care.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cs544.project.share2care.domain.Event;
import cs544.project.share2care.domain.Member;
import cs544.project.share2care.domain.Resource;
import cs544.project.share2care.domain.ResourceStatus;
import cs544.project.share2care.service.IEventService;
import cs544.project.share2care.service.IMemberService;
import cs544.project.share2care.service.IResourceService;

/**
 * Created by Chao Ping
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	IResourceService resourceService;

	@Autowired
	IEventService eventService;

	@Autowired
	IMemberService memberService;

	@RequestMapping(value = "addresource/{eventId}", method = RequestMethod.GET)
	public String addResource(@PathVariable("eventId") int eventId, Model model) {
		Resource resource = new Resource();
		model.addAttribute("resource", resource);
		model.addAttribute("eventId", eventId);
		return "/resource/addresource";
	}

	@RequestMapping(value = "/addresource", method = RequestMethod.POST)
	public String addResourceFormProcess(Model model, Resource resource, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		String eventIdStr = request.getParameter("eventId");
		Event event = eventService.findById(Integer.valueOf(eventIdStr));
		redirectAttributes.addFlashAttribute("event", event);
		resource.setEvent(event);
		resource.setStatus(ResourceStatus.NOTRECEIVED);
		resourceService.saveOrUpdateResource(resource);

		return "redirect:/resource/resourcelist";
	}

	@RequestMapping(value = "/getAllResourcesByEventId/{eventId}", method = RequestMethod.GET)
	public String getAllResourcesByEventId(@PathVariable("eventId") int eventId, Model model, HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		Event event = eventService.findById(eventId);
		if (member.getMemberId() == event.getOwner().getMemberId()) {
			model.addAttribute("organizer", true);
		} else {
			model.addAttribute("organizer", false);
		}
		List<Resource> resourceList = resourceService.findAllByEventId(eventId);
		model.addAttribute("resources", resourceList);
		model.addAttribute("event", event);
		return "/resource/resourcelist";
	}

	@RequestMapping(value = "/resourcelist", method = RequestMethod.GET)
	public String getAllResources(Model model, @ModelAttribute("event") Event event, HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		if (member.getMemberId() == event.getOwner().getMemberId()) {
			model.addAttribute("organizer", true);
		} else {
			model.addAttribute("organizer", false);
		}
		List<Resource> resourceList = resourceService.findAllByEventId(event.getId());
		model.addAttribute("resources", resourceList);
		return "/resource/resourcelist";
	}

	@RequestMapping(value = "/{resourceId}", method = RequestMethod.GET)
	public String showResource(@PathVariable("resourceId") int resourceId, Model model) {
		model.addAttribute("resource", resourceService.getResourceById(resourceId));
		return "/resource/resourcedetail";
	}

	@RequestMapping(value = "/editResource/{resourceId}", method = RequestMethod.GET)
	public String editResource(@PathVariable("resourceId") int resourceId, Model model,
			RedirectAttributes redirectAttributes) {
		Resource curResource = resourceService.getResourceById(resourceId);
		Event curEvent = curResource.getEvent();
		model.addAttribute("resource", curResource);
		model.addAttribute("event", curEvent);
		return "/resource/resourceedit";
	}

	@RequestMapping(value = "/editResource", method = RequestMethod.POST)
	public String editResourceFormProcess(Model model, Resource resource, @ModelAttribute("event") Event event,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("event", resource.getEvent());
		resourceService.saveOrUpdateResource(resource);
		return "redirect:/resource/resourcelist";
	}

	@RequestMapping(value = "/offerResource/{resourceId}", method = RequestMethod.GET)
	public String offerResource(@PathVariable("resourceId") int resourceId, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {
		Member member = (Member) session.getAttribute("member");

		Resource curResource = resourceService.getResourceById(resourceId);
		curResource.setStatus(ResourceStatus.RECEIVED);
		curResource.setMember(member);
		redirectAttributes.addFlashAttribute("event", curResource.getEvent());
		resourceService.saveOrUpdateResource(curResource);
		return "redirect:/resource/resourcelist";
	}

	@RequestMapping(value = "/delete/{resourceId}", method = RequestMethod.GET)
	public String deleteResource(@PathVariable("resourceId") int resourceId, Model model) {
		resourceService.deleteResource(resourceId);
		return "redirect:/resource/resourcelist";
	}

	@RequestMapping(value = "/searchResource", method = RequestMethod.POST)
	public ModelAndView searchResource(@RequestParam("searchText") String searchText,
			RedirectAttributes redirectAttributes) {
		List<Resource> resources = resourceService.findByNameLike(searchText);
		String viewPath = "/resource/resourcelist";
		ModelAndView modelAndView = new ModelAndView(viewPath);
		modelAndView.addObject("resources", resources);
		return modelAndView;
	}

}
