/**
 * 
 */
package cs544.project.share2care.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Dilip
 *
 */
@Entity
public class User {
	@Id @GeneratedValue
	private int id;
	private String name;
	
	public User() {
	}
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
	
}
