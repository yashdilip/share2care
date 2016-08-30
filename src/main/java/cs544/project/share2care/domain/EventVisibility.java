/**
 * 
 */
package cs544.project.share2care.domain;

/**
 * @author Solomon Kassahun
 *
 */
public enum EventVisibility {
	PRIVATE("private"), PUBLIC("public");
	private String name;
	private EventVisibility(String name){
		this.name = name;
	}
	
	public String getEventVisibility(){
		return name;
	}

}
