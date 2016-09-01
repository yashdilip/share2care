package cs544.project.share2care.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@RequestMapping(value = "/addresource/{eventId}", method = RequestMethod.GET)
	public String addResource(@PathVariable("eventId") Integer eventId, Model model) {
		Resource resource = new Resource();
		model.addAttribute("resource", resource);
		model.addAttribute("eventId", eventId);
		return "/resource/addresource";
	}

	@RequestMapping(value = "/addresource/{eventId}", method = RequestMethod.POST)
	public String addResourceFormProcess(@PathVariable("eventId") Integer eventId, Model model, Resource resource,
			RedirectAttributes redirectAttributes) {
		Event event = eventService.findById(eventId);
		redirectAttributes.addFlashAttribute("event", event);
		resource.setEvent(event);
		resource.setStatus(ResourceStatus.NOTRECEIVED);
		resourceService.saveOrUpdateResource(resource);

		return "redirect:/resource/getAllResourcesByEventId/" + eventId;
	}

	@RequestMapping(value = "/getAllResourcesByEventId/{eventId}", method = RequestMethod.GET)
	public String getAllResourcesByEventId(@PathVariable("eventId") Integer eventId, Model model, HttpSession session) {
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

	@RequestMapping(value = "/editResource/{resourceId}/{eventId}", method = RequestMethod.GET)
	public String editResource(@PathVariable("resourceId") Integer resourceId, @PathVariable("eventId") Integer eventId,
			Model model, RedirectAttributes redirectAttributes) {
		Resource curResource = resourceService.getResourceById(resourceId);
		model.addAttribute("resource", curResource);
		model.addAttribute("eventId", eventId);
		return "/resource/resourceedit";
	}

	@RequestMapping(value = "/editResource/{resourceId}/{eventId}", method = RequestMethod.POST)
	public String editResourceFormProcess(@PathVariable("resourceId") Integer resourceId,
			@PathVariable("eventId") Integer eventId, Model model, Resource resource, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		Resource curResource = resourceService.getResourceById(resourceId);
		curResource.setName(resource.getName());
		curResource.setDescription(resource.getDescription());
		curResource.setImportance(resource.getImportance());
		curResource.setQuantity(resource.getQuantity());
		resourceService.saveOrUpdateResource(curResource);
		return "redirect:/resource/getAllResourcesByEventId/" + eventId;
	}

	@RequestMapping(value = "/offerResource/{resourceId}/{eventId}", method = RequestMethod.GET)
	public String offerResource(@PathVariable("resourceId") Integer resourceId,
			@PathVariable("eventId") Integer eventId, Model model, HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		Resource curResource = resourceService.getResourceById(resourceId);
		curResource.setStatus(ResourceStatus.RECEIVED);
		curResource.setMember(member);
		resourceService.saveOrUpdateResource(curResource);
		return "redirect:/resource/getAllResourcesByEventId/" + eventId;
	}

	@RequestMapping(value = "/delete/{resourceId}/{eventId}", method = RequestMethod.GET)
	public String deleteResource(@PathVariable("resourceId") Integer resourceId,
			@PathVariable("eventId") Integer eventId, Model model) {
		resourceService.deleteResource(resourceId);
		return "redirect:/resource/getAllResourcesByEventId/" + eventId;
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
