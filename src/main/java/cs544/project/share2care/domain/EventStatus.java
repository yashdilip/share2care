/**
 * 
 */
package cs544.project.share2care.domain;

/**
 * @author Solomon Kassahun
 *
 */
public enum EventStatus {
	ACTIVE("Active"), CANCELLED("Cancelled"), PASSED("Passed");
	private String name;
	private EventStatus(String name){
		this.name = name;
	}
	
	public String getEventStatus(){
		return name;
	}

}
