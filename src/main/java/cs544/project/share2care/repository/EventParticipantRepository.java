package cs544.project.share2care.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cs544.project.share2care.domain.Event;
import cs544.project.share2care.domain.EventParticipant;
import cs544.project.share2care.domain.Member;

public interface EventParticipantRepository extends JpaRepository<EventParticipant, Integer> {
	//Find event participant by member id
	List<EventParticipant> findByParticipant(Member member);

}
