/**
 * 
 */
package cs544.project.share2care.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * @author Dilip
 *
 */
@Entity
public class Member {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int memberId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy="owner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Event> events;

	@OneToMany(mappedBy="member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Resource> resources;
	
	@Lob
	private byte[] profilePictures;
	
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
}
