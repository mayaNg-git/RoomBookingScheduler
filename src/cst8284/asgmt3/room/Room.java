package cst8284.asgmt3.room;
/**
 * 
 * Room is a super class that holds information of the rooms.
 * 
 * @author Nguyen Ngoc Han Ny, based on code supplied by Prof. Dave Houtman
 * @version 1.01
 * @since 25-03-2020
 *
 */
public abstract class Room {
	
	public static final long serialVersionUID=1L;
	private static final String DEFAULT_ROOM = "unknown room number";
	private String roomNumber;
	
	/**
	 * Default constructor
	 * Set the room number
	 */
	protected Room() {this(DEFAULT_ROOM);}
	/**
	 * Initial constructor.
	 * @param roomNum - room number.
	 */
	protected Room(String roomNum) {setRoomNumber(roomNum);}
	
	/**
	 * Set the room number, used in initial constructor.
	 * @param roomNum - room number.
	 */
	public void setRoomNumber(String roomNum) {roomNumber = roomNum;}
	/**
	 * Get the room number so it may be used to compare objects.
	 * @return String contains room number.
	 */
	public String getRoomNumber() {return roomNumber;}
	
	/**
	 * Abstract method to be overrided my sub-classes.
	 * Return the number of seats in the room so it may be used to compare objects.
	 * @return number of seats.
	 */
    protected abstract int getSeats();
    /**
	 * Abstract method to be overrided my sub-classes.
	 * Return the type of the room.
	 * @return String contains the type of the room.
	 */
    protected abstract String getRoomType();
    /**
	 * Abstract method to be overrided my sub-classes.
	 * Return the details of the room.
	 * @return Stirng contail the details of the room
	 */
	protected abstract String getDetails();
	
	public String toString( ){return getRoomNumber() + " is a " +
		getRoomType() + " with " + getSeats() + " seats; " + getDetails();}
}
