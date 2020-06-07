package cst8284.asgmt3.roomScheduler;

import java.util.Comparator;

/**
 * 
 * SortRoomBookingByCalendar is used to compare object of type RoomBooking by Calendar
 *  
 * @author Nguyen Ngoc Han Ny, based on code supplied by Prof. Dave Houtman
 * @version 1.01
 * @since 25-03-2020
 *
 */
public class SortRoomBookingsByCalendar implements Comparator<RoomBooking> {

	/**
	 * 
	 * Compares its two arguments for RoomBooking. Returns a negative integer, zero, or a positive integer as the first argument is 
	 * less than, equal to, or greater than the second
	 *
	 */
	@Override
	public int compare(RoomBooking o1, RoomBooking o2) {
		// TODO Auto-generated method stub
		return o1.getTimeBlock().getStartTime().compareTo(o2.getTimeBlock().getStartTime());
	}

}
	