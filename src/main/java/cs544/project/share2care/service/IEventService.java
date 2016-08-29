/**
 * 
 */
package cs544.project.share2care.service;

import java.util.Date;
import java.util.List;

import cs544.project.share2care.domain.Event;
import cs544.project.share2care.domain.Member;

/**
 * @author Solomon Kassahun
 *
 */
public interface IEventService {
	void save(Event event);
	void delete(Event event);
	//Delete all events of a specific member
	Long deleteByOwnerMemberId(int memberId);
	//Upcoming events which member is going to attend
	List<Event> findUpcomingEvents(Member owner);
	List<Event> findPastEvents(Member owner);
	//Include all upcoming events within the member's circle
//	List<Event> findUpcomingEventsWithinCircle(Member owner);
//	List<Event> findEventsWithinCircleByCity(Member owner, String city);
//	List<Event> findEventsWithinCircleByDate(Member owner, Date date);
//	List<Event> discoverEventsWithinCircleByCityAndDate(Member owner, String city, Date date);
	//Upcoming events in a given city
	List<Event> findEventsByCity(String city);
	//Upcoming events on a given date
	List<Event> findEventsByStartDate(Date date);
	//Upcoming events in a given city and on a given date
	List<Event> findEventsByCityAndStartDate(String city, Date date);
}