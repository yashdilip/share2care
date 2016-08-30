/**
 * 
 */
package cs544.project.share2care.domain;

/**
 * @author Solomon Kassahun
 *
 */
public enum EventCategory {
	BIRTHDAY("birthday"), PARTY("party"), WEDDING("wedding"), OTHER("other");
	private String name;
	private EventCategory(String name){
		this.name= name;
	}
	public String getEventCategory(){
		return name;
	}

}
