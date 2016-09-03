/**
 * 
 */
package cs544.project.share2care.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs544.project.share2care.domain.Event;
import cs544.project.share2care.domain.EventParticipant;
import cs544.project.share2care.domain.EventVisibility;
import cs544.project.share2care.domain.Member;
import cs544.project.share2care.repository.EventParticipantRepository;
import cs544.project.share2care.repository.EventRepository;
import cs544.project.share2care.service.IEventService;

/**
 * @author Solomon Kassahun
 *
 */
@Service
public class EventServiceImpl implements IEventService {
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private EventParticipantRepository eventParticipantRepository;

	@Override
	public void save(Event event) {
		eventRepository.save(event);

	}
	

	@Override
	public void delete(Event event) {
		eventRepository.delete(event);
		
	}
	
	@Override
	public Event findById(int id) {
		return eventRepository.findById(id);
		
	}

	@Override
	public Long deleteByOwnerMemberId(int memberId) {
		return eventRepository.deleteByOwnerMemberId(memberId);
	}

	@Override
	public List<Event> findUpcomingEvents(Member participant) {
		return eventRepository.findByParticipantsParticipantAndStartDateTimeBefore(participant, new Date());
	}
	
	@Override
	public List<Event> findOwnEvents(int memberId) {
		return eventRepository.findByOwnerMemberId(memberId);
	}


	@Override
	public List<Event> findPastEvents(Member participant) {
		return eventRepository.findByParticipantsParticipantAndEndDateTimeAfter(participant, new Date());
	}	
	
	@Override
	public List<Event> findEventsByCity(String city) {
		return eventRepository.findByVenueAddressCityIgnoreCaseAndStartDateTimeBefore(city, new Date());
	}
	
	@Override
	public List<Event> discoverNewEvents(EventVisibility visibility, Member owner){
		List<Event> futureEvents =  eventRepository.findByVisibilityAndStartDateTimeBefore(visibility, new Date());
		List<Event> memEvents = eventRepository.findByOwnerOrParticipantsParticipant(owner, owner);		
		futureEvents.removeAll(memEvents);
		return futureEvents;
	}

	@Override
	public List<Event> findEventsByStartDate(Date date1) {
		 Calendar cal = Calendar.getInstance();
	     cal.setTime(date1);
	     cal.add(Calendar.DATE, 1); 
	     Date date2 = cal.getTime();
	     return eventRepository.findByStartDateTimeBetween(date1, date2);
	}

	@Override
	public List<Event> findEventsByCityAndStartDate(String city, Date date1) {
		 Calendar cal = Calendar.getInstance();
	     cal.setTime(date1);
	     cal.add(Calendar.DATE, 1); 
	     Date date2 = cal.getTime();
	     return eventRepository.findByVenueAddressCityIgnoreCaseAndStartDateTimeBetween(city, date1, date2);
	}
	
	@Override
	public List<Event> findByNameLike(String word){
		return eventRepository.findByNameLike(word);
	}
	
	@Override
	public List<Event> findByVisibilityAndNameIgnoreCaseLike(EventVisibility visibility, String word){
		return eventRepository.findByVisibilityAndNameContainingIgnoreCase(visibility, word);
	}
	
	
	
	@Override
	public Long deleteById(int eventId){
		return eventRepository.deleteById(eventId);
	}	


}
