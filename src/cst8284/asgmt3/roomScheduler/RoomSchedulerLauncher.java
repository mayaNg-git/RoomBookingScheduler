package cst8284.asgmt3.roomScheduler;
import cst8284.asgmt3.room.*;

/**
 * 
 * Room scheduler program implements an application that save information of room bookings and schedule them.
 * 
 * @author Nguyen Ngoc Han Ny, based on code supplied by Prof. Dave Houtman
 * @version 1.01
 * @since 25-03-2020
 *
 */
public class RoomSchedulerLauncher {

	/**
	 * Main Menu
	 * 
	 * @param args - Parameters passed into the application
	 */
	public static void main(String[] args) {
		Room room = new ComputerLab();
		room.setRoomNumber("B119");
		try {
			new RoomScheduler(room).launch();
		} catch (BadRoomBookingException ex) {
			System.out.println(ex.getHeader() + ex.getMessage());
		}
	}

}
