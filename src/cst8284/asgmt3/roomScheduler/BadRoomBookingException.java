package cst8284.asgmt3.roomScheduler;

/**
 * BadRoomBookingException class extends RuntimeException, handles exceptions thrown in the process of making a Booking.
 * 
 * @author Nguyen Ngoc Han Ny, based on code supplied by Prof. Dave Houtman
 * @version 1.01
 * @since 25-03-2020
 */
public class BadRoomBookingException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String header;
	/**
	 * Default constructor
	 * Set the default message when the exception is thrown.
	 */
	public BadRoomBookingException() {
		this("Bad room booking enter","Please try again");
	}
	/**
	 * Initial constructor.
	 * @param header - the header of the message, show what type of error.
	 * @param message - the message that prints out when the exception is thrown.
	 */
	public BadRoomBookingException(String header, String message) {
		
		super(message);
		setHeader(header);
	}
	/**
	 * Return the header of the message so it maybe used to compare objects.
	 * @return String contains the header of the message.
	 */
	public String getHeader() {
		return header;
	}
	/**
	 * Set the header of the message, used in initial constructor.
	 * @param header - the header of the message.
	 */
	public void setHeader(String header) {
		this.header = header;
	}
	
}
