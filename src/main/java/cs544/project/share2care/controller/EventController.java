/**
 * 
 */
package cs544.project.share2care.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import cs544.project.share2care.domain.Event;
import cs544.project.share2care.domain.EventCategory;
import cs544.project.share2care.domain.EventVisibility;
import cs544.project.share2care.domain.Member;
import cs544.project.share2care.domain.Venue;
import cs544.project.share2care.service.IEventService;
import cs544.project.share2care.service.IMemberService;

/**
 * @author Solomon Kassahun
 *
 */
@Controller
@RequestMapping(value = "/event")
@SessionAttributes("event")
public class EventController {
	@Autowired
	private IEventService eventService;
	@Autowired
	private IMemberService memberService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getUpcomingEvents(Model model, Principal principal){
		Member member = memberService.getLoggedInMemeberByMemberName(principal.getName());
		List<Event> events = eventService.findUpcomingEvents(member);
		model.addAttribute("events", events);
		return "users/user/events";				
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String getEventDetail(Model model, @PathVariable int id){
		Event event = eventService.findById(id);
		model.addAttribute("event", event);		
		return "users/user/event";				
	}
	
	@RequestMapping(value="/pastEvents", method=RequestMethod.GET)
	public String getPastEvents(Model model, Principal principal){
		Member owner = memberService.getLoggedInMemeberByMemberName(principal.getName());
		List<Event> events= eventService.findPastEvents(owner);
		model.addAttribute("events", events);		
		return "users/user/events";				
	}
	

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateNewEventForm(Model model, Principal principal) {
		Member owner = memberService.getLoggedInMemeberByMemberName(principal.getName());
		Event event = new Event();
		event.setOwner(owner);
		model.addAttribute("event", event);
		model.addAttribute("visibilities", EventVisibility.values());
		model.addAttribute("categories", EventCategory.values());
		model.addAttribute("view", "users/user/createNewEvent");
		return "fragments/memberMasterPage";

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String processEventCreate(@Valid @ModelAttribute("event") Event event, BindingResult result, Model model) {
		String view;
		if (!result.hasErrors()) {
			eventService.save(event);
			Venue venue = new Venue();
			model.addAttribute("venue", venue);
			view = "users/user/addEventVenue";
		} else {
			view = "users/user/createNewEvent";
		}
		return view;
	}
	@RequestMapping(value = "/create/addVenue", method=RequestMethod.POST)
	public String processAddEventVenue(@Valid @ModelAttribute("venue") Venue venue, BindingResult result, HttpServletRequest request){
		Event event;
		if (!result.hasErrors()) {
			event = (Event)request.getSession(false).getAttribute("event");
			event.setVenue(venue);
			eventService.save(event);
			return "users/user/uploadEventImage";
		} else {
			return "users/user/addEventVenue";
		}
		
	}
	@RequestMapping(value = "/create/uploadImage", method=RequestMethod.POST)
	public String processImageUpload(@RequestParam("eventPhoto") MultipartFile eventPhoto, HttpServletRequest request) throws IOException{
		Event event = (Event)request.getSession(false).getAttribute("event");
		event.setEventPicture(eventPhoto.getBytes());
		eventService.save(event);
		return "redirect:/event/" + event.getId();
	}
	

}
