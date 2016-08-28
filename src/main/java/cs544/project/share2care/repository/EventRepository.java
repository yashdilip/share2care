package cs544.project.share2care.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cs544.project.share2care.domain.Event;
import cs544.project.share2care.domain.EventStatus;


public interface EventDAO extends JpaRepository<Event, Integer>{
	/* find event(s) */
	Event findOne(int eventId);
	List<Event> findAll();
	Page<Event> findAll(Pageable pageble);
	List<Event> findByMemberIdOrderByStartDateTimeAsc(int memberId);
	List<Event> findByStartDateTime(Date date);
	List<Event> findByStatus(EventStatus status);
	List<Event> findByAddressCityIgnoreCase(String city);
	List<Event> findByAddressCityIgnoreCaseAndStatus(String city, EventStatus status);
	List<Event> findByAddressZip(String zip);
	List<Event> findByAddressZipAndStatus(String zip, EventStatus status);
	List<Event> findByResourceNameIgnoreCase(String resName);
	
	/* remove event(s) */
	Long deleteByMemberId(int memberId);
	
}
