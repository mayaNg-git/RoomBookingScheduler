package cst8284.asgmt3.room;
/**
 * Classroom is a sub-class of Room class, which contains information about Computer Lab room
 * 
 * @author Nguyen Ngoc Han Ny, based on code supplied by Prof. Dave Houtman
 * @version 1.01
 * @since 25-03-2020	 
 */
public final class Classroom extends Room{

	private static final int DEFAULT_SEATS=120;
	private int seats;
	/**
	 * Default constructor
	 * Set default seats to 120;
	 */
	public Classroom(){seats = DEFAULT_SEATS;}
	
	/**
	 * Return the number of seats in the room so it may be used to compare objects.
	 * @return number of seats.
	 */
	protected int getSeats(){return seats;}
	/**
	 * Return the type of the room.
	 * @return String contains the type of the room.
	 */
	protected String getRoomType(){return "classroom";}
	/**
	 * Return the details of the room.
	 * @return Stirng contail the details of the room
	 */
	protected String getDetails(){return "contains overhead projector";}
}
