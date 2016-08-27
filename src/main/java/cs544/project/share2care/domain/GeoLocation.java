/**
 * 
 */
package cs544.project.share2care.domain;

import javax.persistence.Embeddable;

/**
 * @author Solomon Kassahun
 *
 */

@Embeddable
public class GeoLocation {
	private double latitude;
	private double longitude;
	
	/* constructor definition */
	public GeoLocation(){}
	public GeoLocation (double lat, double lng){
		this.latitude = lat;
		this.longitude = lng;
	}

	/* getters and setters */
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	

	

}
