/**
 * 
 */
package cs544.project.share2care.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Solomon Kassahun
 *
 */
@Entity
@Table(name="events")
public class Event {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="ownerId")
	private Member owner;
	
	private String name;
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDateTime;
	
	@Enumerated(EnumType.STRING)
	private EventStatus status;
	
	@OneToMany(mappedBy="event")
	private List<EventParticipant> participants;
	
	@OneToMany(mappedBy="event")
	private List<Resource> resources;
	
	@ManyToOne
	@JoinColumn(name="venueId")
	private Venue venue;
	
	@Enumerated(EnumType.STRING)
	private EventCategory category;
	
	@Lob
	private byte[] eventPictures;

	public Member getOwner() {
		return owner;
	}

	public void setOwner(Member owner) {
		this.owner = owner;
	}
	
	/* end of instance variable declaration */
	
	/* constructor definition goes here */
	
	/* Setters and getters */
	
	
	
	

}
