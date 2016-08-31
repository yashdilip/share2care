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

import org.springframework.format.annotation.DateTimeFormat;

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
	//@DateTimeFormat(pattern="dd/MM/yyyy hh:mm:ss")
	private Date startDateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	//@DateTimeFormat(pattern="dd/MM/yyyy hh:mm:ss")
	private Date endDateTime;
	
	@Enumerated(EnumType.STRING)
	private EventStatus status;
	
	@Enumerated(EnumType.STRING)
	private EventVisibility visibility;
	
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
	private byte[] eventPicture;

	
	
	/* end of instance variable declaration */
	
	/* constructor definition goes here */
	public Event(){}


	/* Setters and getters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Member getOwner() {
		return owner;
	}

	public void setOwner(Member owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public EventStatus getStatus() {
		return status;
	}

	public void setStatus(EventStatus status) {
		this.status = status;
	}	
	

	public EventVisibility getVisibility() {
		return visibility;
	}

	public void setVisibility(EventVisibility visibility) {
		this.visibility = visibility;
	}

	public List<EventParticipant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<EventParticipant> participants) {
		this.participants = participants;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}

	public EventCategory getCategory() {
		return category;
	}

	public void setCategory(EventCategory category) {
		this.category = category;
	}

	public byte[] getEventPicture() {
		return eventPicture;
	}

	public void setEventPicture(byte[] eventPicture) {
		this.eventPicture = eventPicture;
	}
	

}
