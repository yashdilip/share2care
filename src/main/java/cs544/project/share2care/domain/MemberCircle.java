/**
 * 
 */
package cs544.project.share2care.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Dilip
 *
 */
@Entity
public class MemberCircle {
	
	@javax.persistence.Id
	@GeneratedValue
	private int Id;
	
	@ManyToOne
	@JoinColumn(name="memberId")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name="circleId")
	private Circle circle;

	public MemberCircle() {
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Circle getCircle() {
		return circle;
	}

	public void setCircle(Circle circle) {
		this.circle = circle;
	}
	
	
}
