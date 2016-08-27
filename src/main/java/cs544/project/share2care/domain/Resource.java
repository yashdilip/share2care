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
}
