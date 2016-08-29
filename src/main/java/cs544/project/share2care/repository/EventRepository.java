package cs544.project.share2care.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cs544.project.share2care.domain.Event;
import cs544.project.share2care.domain.EventStatus;
import cs544.project.share2care.domain.Member;


public interface EventRepository extends JpaRepository<Event, Integer>{
	/* find event(s) */
	Event findOne(int eventId);
	List<Event> findAll();
	Page<Event> findAll(Pageable pageble);
	List<Event> findByOwnerMemberIdOrderByStartDateTimeAsc(int memberId);	
	List<Event> findByStartDateTimeBetween(Date date1, Date date2);
	List<Event> findByStatus(EventStatus status);
	List<Event> findByVenueAddressCityIgnoreCaseAndStartDateTimeBefore(String city, Date date);
	List<Event> findByVenueAddressCityIgnoreCaseAndStartDateTimeBetween(String city, Date date1, Date date2);
	List<Event> findByVenueAddressCityIgnoreCaseAndStatus(String city, EventStatus status);	
	@Query("select m.eventsParticipating as e from Member m where m = :participant And e.startDateTime > CURRENT_DATE")
	List<Event> findUpcomingEvents(@Param("participant") Member participant);
	@Query("select m.eventsParticipating as e from Member m where m = :participant And e.endDateTime < CURRENT_DATE")
	List<Event> findPastEvents(@Param("participant") Member participant);
	/* remove event(s) */
	Long deleteByOwnerMemberId(int memberId);
	
}
