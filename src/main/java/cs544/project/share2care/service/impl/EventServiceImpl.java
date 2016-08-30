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
import cs544.project.share2care.domain.Member;
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
		return eventRepository.findByParticipantsParticipantAndStartDateTimeAfter(participant, new Date());
	}

	@Override
	public List<Event> findPastEvents(Member participant) {
		return eventRepository.findByParticipantsParticipantAndEndDateTimeBefore(participant, new Date());
	}	
	
	@Override
	public List<Event> findEventsByCity(String city) {
		return eventRepository.findByVenueAddressCityIgnoreCaseAndStartDateTimeBefore(city, new Date());
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


}
