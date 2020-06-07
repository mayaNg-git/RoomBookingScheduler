package cst8284.asgmt3.roomScheduler;

import java.util.Calendar;
/**
 * 
 * TimeBlock contains the start time and end time of RoomBooking objects
 * 
 * @author Nguyen Ngoc Han Ny, based on code supplied by Prof. Dave Houtman
 * @version 1.01
 * @since 25-03-2020
 *
 */
public class TimeBlock implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Calendar startTime, endTime;

	/**
	 * Default constructor
	 * Set default time from 8 to 24
	 */
	public TimeBlock() {
		this(new Calendar.Builder().set(Calendar.HOUR_OF_DAY, 8).build(),
				new Calendar.Builder().set(Calendar.HOUR_OF_DAY, 24).build());
	}

	/**
	 * Initial constructor
	 * @param start - the start time of the bookings.
	 * @param end - the end time of the bookings.
	 */
	public TimeBlock(Calendar start, Calendar end) {
		setStartTime(start); setEndTime(end);
	}

	@Override
	public String toString() {
		return getStartTime().get(Calendar.HOUR_OF_DAY) + ":00 - " + getEndTime().get(Calendar.HOUR_OF_DAY) + ":00\n";
	}
	/**
	 * Set the start time for TimeBlock objects, used in default and initial constructor.
	 * @param startTime - the start time of the bookings.
	 */
	public void setStartTime(Calendar startTime) {this.startTime = startTime;}
	/**
	 * Return the start time of a booking so it may be used to compare objects.
	 * @return Calendar object contains start time of a booking.
	 */
	public Calendar getStartTime() {return startTime;}
	/**
	 * Set the end tfor TimeBlock objects, used in default and initial constructor.
	 * @param endTime - the start time of the bookings.
	 */
	public void setEndTime(Calendar endTime) {this.endTime = endTime;}
	/**
	 * Return the end time of a booking so it may be used to compare objects.
	 * @return Calendar object contains end time of a booking.
	 */
	public Calendar getEndTime() {return endTime;}
	/**
	 * Calculate how long the booking last by the subtraction of start time and end time.
	 * @return Duration of a booking.
	 */
	public int duration() {
		return (getEndTime().get(Calendar.HOUR_OF_DAY) - getStartTime().get(Calendar.HOUR_OF_DAY));
	}

	/**
	 * Check if the new Booking is overlapping on any other Bookings by comparing its start and end time
	 * @param tb - TimeBlock object that needs to be checked.
	 * @return <code>true</code> if the time is overlapped, <code>false</code> otherwise. 
	 */
	public boolean overlaps(TimeBlock tb) {
		if ((tb.getStartTime().get(Calendar.DAY_OF_YEAR) != this.getStartTime().get(Calendar.DAY_OF_YEAR))
				|| (tb.getStartTime().get(Calendar.YEAR) != this.getStartTime().get(Calendar.YEAR))) 
			return false;  // can't overlap; not on same date
		return ((tb.getStartTime().get(Calendar.HOUR_OF_DAY) < getEndTime().get(Calendar.HOUR_OF_DAY))
				&& (tb.getEndTime().get(Calendar.HOUR_OF_DAY) > getStartTime().get(Calendar.HOUR_OF_DAY))) ;
		// same date, but do the two timeblocks overlap?
	}

}
