/**
 * 
 */
package cs544.project.share2care.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * @author Dilip
 *
 */
@Entity
public class Circle implements Comparable<Circle> {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int circleId;
	
	@NotNull(message="circle name required")
	private String circleName;
	
	@OneToMany(mappedBy="circle")
	private List<MemberCircle> members = new ArrayList<MemberCircle>();

	@OneToOne
	@JoinColumn(name="ownerId")
	private Member owner;
	
	public Circle() {
	}

	public int getCircleId() {
		return circleId;
	}

	public void setCircleId(int circleId) {
		this.circleId = circleId;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public Member getOwner() {
		return owner;
	}

	public void setOwner(Member owner) {
		this.owner = owner;
	}

	public List<MemberCircle> getMembers() {
		return members;
	}

	public void setMembers(List<MemberCircle> members) {
		this.members = members;
	}


	@Override
	public int compareTo(Circle o) {
		return this.getCircleId()-o.getCircleId();
	}
		
}
