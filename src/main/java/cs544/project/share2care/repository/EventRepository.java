package cs544.project.share2care.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cs544.project.share2care.domain.Event;
import cs544.project.share2care.domain.EventStatus;
import cs544.project.share2care.domain.Member;

public interface EventRepository extends JpaRepository<Event, Integer>{
	/* find event(s) */
	Event findById(int id);
	List<Event> findAll();
	Page<Event> findAll(Pageable pageble);
	List<Event> findByOwnerMemberIdOrderByStartDateTimeAsc(int memberId);
	List<Event> findByOwnerMemberId(Integer memberId);
	List<Event> findByStartDateTimeBetween(Date date1, Date date2);
	List<Event> findByStatus(EventStatus status);	
	List<Event> findByVenueAddressCityIgnoreCaseAndStartDateTimeBefore(String city, Date date);
	List<Event> findByVenueAddressCityIgnoreCaseAndStartDateTimeBetween(String city, Date date1, Date date2);
	List<Event> findByVenueAddressCityIgnoreCaseAndStatus(String city, EventStatus status);	
//	@Query("select e from Event e where e.participants.participant = :participant And e.startDateTime > CURRENT_DATE")
//	List<Event> findUpcomingEvents(@Param("participant") Member participant);
//    @Query("select e from Event e where e.participants.participant = :participant And e.endDateTime < CURRENT_DATE")
//    List<Event> findPastEvents(@Param("participant") Member participant);
    List<Event> findByParticipantsParticipantAndStartDateTimeAfter(Member participant, Date date);
    List<Event> findByParticipantsParticipantAndEndDateTimeBefore(Member participant, Date date);
	/* remove event(s) */
	Long deleteByOwnerMemberId(int memberId);
	
}
