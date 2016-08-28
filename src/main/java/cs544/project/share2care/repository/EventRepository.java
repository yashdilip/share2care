package cs544.project.share2care.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cs544.project.share2care.domain.Event;
import cs544.project.share2care.domain.EventStatus;


public interface EventRepository extends JpaRepository<Event, Integer>{
	/* find event(s) */
	Event findOne(int eventId);
	List<Event> findAll();
	Page<Event> findAll(Pageable pageble);
	List<Event> findByOwnerMemberIdOrderByStartDateTimeAsc(int memberId);
	List<Event> findByStartDateTime(Date date);
	List<Event> findByStatus(EventStatus status);
	List<Event> findByVenueAddressCityIgnoreCase(String city);
	List<Event> findByVenueAddressCityIgnoreCaseAndStatus(String city, EventStatus status);
	List<Event> findByVenueAddressZip(String zip);
	List<Event> findByVenueAddressZipAndStatus(String zip, EventStatus status);
	
	
	/* remove event(s) */
	Long deleteByOwnerMemberId(int memberId);
	
}
