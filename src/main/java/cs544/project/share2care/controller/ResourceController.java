package cs544.project.share2care.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import cs544.project.share2care.domain.Resource;
import cs544.project.share2care.service.IEventService;
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

	@RequestMapping(value = "addresource/{eventId}", method = RequestMethod.GET)
	public String addResource(@PathVariable("eventId") int eventId, Model model) {
		Resource resource = new Resource();
		model.addAttribute("resource", resource);
		model.addAttribute("eventId", eventId);
		return "/resource/addresource";
	}

	@RequestMapping(value = "/addresource", method = RequestMethod.POST)
	public String addResourceFormProcess(Model model, Resource resource, HttpServletRequest request) {
		String eventIdStr = request.getParameter("eventId");
		Event event = eventService.findById(Integer.valueOf(eventIdStr));
		resource.setEvent(event);
		resourceService.saveOrUpdateResource(resource);
		return "redirect:/resource/resourcelist";
	}

	@RequestMapping(value = "/resourcelist", method = RequestMethod.GET)
	public String getAllResources(Model model) {
		List<Resource> resourceList = resourceService.findAllResources();
		model.addAttribute("resources", resourceList);
		return "/resource/resourcelist";
	}

	@RequestMapping(value = "/{resourceId}", method = RequestMethod.GET)
	public String showResource(@PathVariable("resourceId") int resourceId, Model model) {
		model.addAttribute("resource", resourceService.getResourceById(resourceId));
		return "/resource/resourcedetail";
	}

	@RequestMapping(value = "/edit/{resourceId}", method = RequestMethod.GET)
	public String editResource(@PathVariable("resourceId") int resourceId, Model model) {
		model.addAttribute("resource", resourceService.getResourceById(resourceId));
		return "/resource/resourceedit";
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
