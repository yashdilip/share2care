package cs544.project.share2care.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * @author Chao Ping
 *
 */
@Entity
@Table(name = "resource")
public class Resource {
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private String description;
	
	@ManyToOne
	@JoinColumn(name="memberId")
	private Member member;
	
	private int quantity;
	
	@Enumerated(EnumType.STRING)
	private ResourceStatus status;
	
	@Enumerated(EnumType.STRING)
	private ResourceImportance importance;
	
	@ManyToOne
	@JoinColumn(name="eventId")
	private Event event;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ResourceStatus getStatus() {
		return status;
	}

	public void setStatus(ResourceStatus status) {
		this.status = status;
	}

	public ResourceImportance getImportance() {
		return importance;
	}

	public void setImportance(ResourceImportance importance) {
		this.importance = importance;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
