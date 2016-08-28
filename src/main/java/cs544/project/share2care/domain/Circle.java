/**
 * 
 */
package cs544.project.share2care.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * @author Dilip
 *
 */
@Entity
public class Circle {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int circleId;
	
	private String circleName;
	
	@ManyToMany(mappedBy="circles")
	private List<Member> members;

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

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
		
}
