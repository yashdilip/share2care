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
import cs544.project.share2care.domain.EventVisibility;
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
	//Upcoming events
    List<Event> findByParticipantsParticipantAndStartDateTimeBefore(Member participant, Date date);
    
    //Past events
    List<Event> findByParticipantsParticipantAndEndDateTimeAfter(Member participant, Date date);
    
    //Searching events by name
    List<Event> findByNameLike(String word);
    List<Event> findByVisibilityAndNameContainingIgnoreCase(EventVisibility visibility, String word);
    
    //Discover events I'm not going for yet or events that are not mine
    List<Event> findByParticipantsParticipantNotAndEndDateTimeAfter(Member mem, Date date);
    @Query("select e from Event e left join e.participants ep where e.owner <> :mem and ep.participant <> :mem and e.startDateTime > :date")
    List<Event> findNewEvents(@Param("mem") Member member, @Param("date") Date date);
    //All future time events (date is current date here)
    List<Event> findByVisibilityAndStartDateTimeBefore(EventVisibility visibility, Date date);
    //Find all events member owns or future and past events he is participant of
    List<Event> findByOwnerOrParticipantsParticipant(Member owner, Member mem);
	/* remove event(s) */
	Long deleteByOwnerMemberId(Integer memberId);
	Long deleteById(Integer memberId);
	
	//List participants of an event
	
}
