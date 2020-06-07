package cst8284.asgmt3.roomScheduler;
/**
 * Activity contains the event's description and category of the RoomBooking object.
 * 
 * @author Nguyen Ngoc Han Ny, based on code supplied by Prof. Dave Houtman
 * @version 1.01
 * @since 25-03-2020
 *
 */
public class Activity implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String category, description;
	
	/**
	 * Initial constructor
	 * @param category - the category of the Event.
	 * @param description - the description of the Event.
	 */
	public Activity(String category, String description) {
		setCategory(category); setDescription(description);	
	}
	
	/**
	 * Return the description of the event so it may be used to compare objects.
	 * @return String contains the description of the Event.
	 */
	public String getDescription() {return description;}
	/**
	 * Set the description for the Event, used in initial constructor.
	 * @param description - the description of the Event.
	 */
	public void setDescription(String description) {this.description = description;}
	
	/**
	 * Return the description of the event so it may be used to compare objects.
	 * @return String contains the category of the Event.
	 */
	public String getCategory() {return category;}
	/**
	 * Set the category for the Event, used in initial constructor.
	 * @param category - the category of the Event.
	 */
	public void setCategory(String category) {this.category = category;}
	
	@Override
	public String toString() {
		return  "Event: " + getCategory() + "\n" + 
			((getDescription()!="")?"Description: " + getDescription():"") + "\n";
	}
}
