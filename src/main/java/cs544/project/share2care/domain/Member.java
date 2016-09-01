/**
 * 
 */
package cs544.project.share2care.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Constraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/**
 * @author Dilip
 *
 */
@Entity
public class Member implements Comparable<Member> {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int memberId;
	@NotNull(message="first name required")
	private String firstName;
	private String lastName;
	@NotNull(message="email can not be empty")
	@Email(message="email format not matched")
	private String email;
	private String phoneNumber;
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy="owner")
	private List<Event> events;

	@OneToMany(mappedBy="participant")
	private List<EventParticipant> eventParticipating;
	
	@OneToMany(mappedBy="member")
	private List<Resource> resources;
	
	@OneToMany(mappedBy="member")
	private List<MemberCircle> circles = new ArrayList<MemberCircle>();
	
	private String imageLocation;
	
	@Lob byte[] profilePictures;
	
	public Member() {
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	public byte[] getProfilePictures() {
		return profilePictures;
	}
	public void setProfilePictures(byte[] profilePictures) {
		this.profilePictures = profilePictures;
	}
	public void addEvent(Event eventobj){
		events.add(eventobj);
		eventobj.setOwner(this);
	}
	public void removeEvent(Event eventobj){
		eventobj.setOwner(null);
		this.events.remove(eventobj);
	}
	public void addResource(Resource resource){
		resources.add(resource);
		resource.setMember(this);
	}
	public void removeResource(Resource resource){
		resource.setMember(null);
		this.resources.remove(resource);
	}

	public String getImageLocation() {
		return imageLocation;
	}
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	public List<EventParticipant> getEventParticipating() {
		return eventParticipating;
	}
	public void setEventParticipating(List<EventParticipant> eventParticipating) {
		this.eventParticipating = eventParticipating;
	}
	public List<MemberCircle> getCircles() {
		return circles;
	}
	public void setCircles(List<MemberCircle> circles) {
		this.circles = circles;
	}

	@Override
	public int compareTo(Member o) {
		return this.memberId-o.getMemberId();
	}
	
}
