package cst8284.asgmt3.roomScheduler;
/**
 * 
 * RoomBooking contains all information of a Booking.
 * 
 * @author Nguyen Ngoc Han Ny, based on code supplied by Prof. Dave Houtman
 * @version 1.01
 * @since 25-03-2020
 *
 */
public class RoomBooking implements java.io.Serializable {
	public static final long serialVersionUID = 1L;
	private ContactInfo contactInfo;
	private Activity activity;
	private TimeBlock timeBlock;
	/**
	 * Initial constructor
	 * @param contactInfo - ContactInfo object, contains the information of the customer.
	 * @param activity - Activity object, contains the event's description and category.
	 * @param timeBlock - TimeBlock object, contains the event's start time and end time.
	 */
	public RoomBooking(ContactInfo contactInfo, Activity activity, TimeBlock timeBlock) {
		setContactInfo(contactInfo); setActivity(activity); setTimeBlock(timeBlock);
	}
	/**
	 * Default constructor
	 */
	public RoomBooking() {}
	/**
	 * Set the Contact Info for the Booking, used in Initial constructor.
	 * @param contactInfo - ContactInfo object, contains the information of the customer.
	 */
	public void setContactInfo(ContactInfo contactInfo) {this.contactInfo = contactInfo;}
	/**
	 * Return the ContactInfo object so it may be used to conpare Booking objects.
	 * @return ContactInfo object, contains the information of the customer.
	 */
	public ContactInfo getContactInfo() {return contactInfo;}
	
	/**
	 * Set the Activity for the Booking, used in Initial constructor.
	 * @param activity - Activity object, contains the event's description and category.
	 */
	public void setActivity(Activity activity) {this.activity = activity;}
	/**
	 * Return the Activity object so it may be used to conpare Booking objects.
	 * @return Activity object, contains the event's description and category.
	 */
	public Activity getActivity() {return activity;}
	
	/**
	 * Set the Time Block for the Booking, used in Initial constructor.
	 * @param timeBlock - TimeBlock object, contains the event's start time and end time.
	 */
	public void setTimeBlock(TimeBlock timeBlock) {this.timeBlock = timeBlock;}
	/**
	 * Return the TimeBlock object so it may be used to conpare Booking objects.
	 * @return TimeBlock object, contains the event's start time and end time.
	 */
	public TimeBlock getTimeBlock() {return timeBlock;}

	@Override public String toString() {
		return (getTimeBlock().toString() + getActivity().toString() +  getContactInfo().toString());
	}

}
