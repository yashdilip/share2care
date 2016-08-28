/**
 * 
 */
package cs544.project.share2care.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Solomon Kassahun
 *
 */
@Entity
@Table(name="event_venues")
public class Venue {
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	@Embedded
	private GeoLocation geoLocation;
	
	@Embedded
	private Address address;
	
	/* end of instance variables declaration */
	
	/* constructor definition */
	
	/* setters and getters */

}
