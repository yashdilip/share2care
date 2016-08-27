/**
 * 
 */
package cs544.project.share2care.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Dilip
 *
 */
@Entity
public class Member {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int memberId;
	
	
}
