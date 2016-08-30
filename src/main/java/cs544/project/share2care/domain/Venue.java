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
	
	public Venue(){}
	
	/* setters and getters */

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

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	

}
