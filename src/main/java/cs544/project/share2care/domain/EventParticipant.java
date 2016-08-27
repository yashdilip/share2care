/**
 * 
 */
package cs544.project.share2care.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Solomon Kassahun
 *
 */
@Entity
@Table(name="event-participant")
public class EventParticipant {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="memberId")
	private Member participant;
	
	@ManyToOne
	@JoinColumn(name="eventId")
	private Event event;
	
	/* getters and setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Member getParticipant() {
		return participant;
	}

	public void setParticipant(Member participant) {
		this.participant = participant;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	
	
	

}
