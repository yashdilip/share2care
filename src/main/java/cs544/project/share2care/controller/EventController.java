/**
 * 
 */
package cs544.project.share2care.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

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
		model.addAttribute("view", "users/user/events");
		return "fragments/memberMasterPage";				
	}
	
	@RequestMapping(value="/myEvents", method=RequestMethod.GET)
	public String getMyEvents(Model model, Principal principal){
		Member member = memberService.getLoggedInMemeberByMemberName(principal.getName());
		List<Event> events = eventService.findOwnEvents(member.getMemberId());
		for(Event e : events) {
			if(e.getEventPicture() != null){
				System.out.println("------------------"+e.getName()+" image "+e.getEventPicture().length);				
			}
			
		}
		model.addAttribute("events", events);
		model.addAttribute("view", "users/user/ownEvents");
		return "fragments/memberMasterPage";			
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public String getEventDetail(Model model, @PathVariable int id){
		Event event = eventService.findById(id);
		model.addAttribute("event", event);		
		model.addAttribute("view", "users/user/event");
		return "fragments/memberMasterPage";				
	}
	
	@RequestMapping(value="/past", method=RequestMethod.GET)
	public String getPastEvents(Model model, Principal principal){
		Member owner = memberService.getLoggedInMemeberByMemberName(principal.getName());
		List<Event> events= eventService.findPastEvents(owner);
		model.addAttribute("events", events);		
		model.addAttribute("view", "users/user/events");
		return "fragments/memberMasterPage";				
	}
	

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String getCreateNewEventForm(Model model, Principal principal) {
		Member owner = memberService.getLoggedInMemeberByMemberName(principal.getName());
		Event event = new Event();
		Venue venue = new Venue();
		event.setOwner(owner);
		event.setVenue(venue);
		owner.getEvents().add(event);
		model.addAttribute("event", event);
		model.addAttribute("visibilities", EventVisibility.values());
		model.addAttribute("categories", EventCategory.values());
		model.addAttribute("view", "users/user/createNewEvent");
		return "fragments/memberMasterPage";

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String processEventCreate(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpServletRequest request, Principal principal) throws IOException {
		String view;
		if (!result.hasErrors()) {
			Member owner = memberService.getLoggedInMemeberByMemberName(principal.getName());
			event.setOwner(owner);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		    MultipartFile file = multipartRequest.getFile("eventPicture");
		    event.setEventPicture(file.getBytes());
			memberService.saveMember(owner);
			eventService.save(event);
			view = "redirect:/event/"+event.getId();
		} else {
			view = "users/user/createNewEvent";
		}
		return view;
	}
	@RequestMapping(value = "/create/addVenue/{id}", method=RequestMethod.POST)
	public String processAddEventVenue(@Valid @ModelAttribute("venue") Venue venue, BindingResult result, @PathVariable int id){
		Event event;
		if (!result.hasErrors()) {
			event = eventService.findById(id);
			event.setVenue(venue);
			eventService.save(event);
			return "users/user/uploadEventImage";
		} else {
			return "users/user/addEventVenue";
		}
		
	}
	@RequestMapping(value = "/create/uploadEventPicture", method=RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.OK)
	public void processImageUpload(@RequestParam("eventPicture") MultipartFile eventPicture, HttpSession session) throws IOException{
		Event event = (Event)session.getAttribute("event");
		event.setEventPicture(eventPicture.getBytes());		
		eventService.save(event);
		return;
	}
	
	
	 @RequestMapping(value = "/image/{eventId}", produces = MediaType.IMAGE_PNG_VALUE)
	    public ResponseEntity<byte[]> getImage(@PathVariable("eventId") int eventId) throws IOException {
	    	Event event = eventService.findById(eventId);
	        byte[] imageContent = event.getEventPicture();
	        final HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_PNG);
	        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	    }
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//		dateFormat.setLenient(true);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy h:mm:ss");
		dateFormat.setLenient(true);
		
		binder.registerCustomEditor(Date.class, "event.startDateTime", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Date.class, "event.endDateTime", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
	

}
