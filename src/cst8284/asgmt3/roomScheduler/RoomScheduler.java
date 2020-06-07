package cst8284.asgmt3.roomScheduler;
import java.util.Scanner;
import java.util.regex.Pattern;

import cst8284.asgmt3.room.Room;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
/**
 * RoomScheduler handle all events happen in the RoomBooking application.
 * 
 * @author Nguyen Ngoc Han Ny, based on code supplied by Prof. Dave Houtman
 * @version 1.01
 * @since 25-03-2020
 *
 */
public class RoomScheduler {
	private Room room;
	private static Scanner scan = new Scanner(System.in);
	private ArrayList<RoomBooking> roomBookings = new ArrayList<>(); 

	private static final int 
	DISPLAY_ROOM_INFORMATION = 1, ENTER_ROOM_BOOKING = 2, 
	DELETE_BOOKING = 3, CHANGE_BOOKING = 4, 
	DISPLAY_BOOKING = 5, DISPLAY_DAY_BOOKINGS = 6, 
	SAVE_BOOKINGS_TO_FILE = 7, LOAD_BOOKINGS_FROM_FILE = 8,
	EXIT = 0;

	/**
	 * Default constructor
	 * @param room - Room object that holds information of the rooms.
	 */
	public RoomScheduler(Room room) {setRoom(room);}
	/**
	 * Set the room information, used in initial constructor.
	 * @param room - Room object that holds information of the rooms.
	 */
	private void setRoom(Room room) {this.room = room;}
	/**
	 * Return the room information so it may be used to compare objects.
	 * @return Room object that holds information of the rooms.
	 */
	private Room getRoom() {return room;}

	/**
	 * Method that used to launch the program.
	 */
	public void launch() {
		int choice = 0;
		if (new File("CurrentRoomBookings.book").exists()) loadBookingsFromFile();
		do {
			choice = displayMenu();
			executeMenuItem(choice);
		} while (choice != EXIT);		
	}
	
	/**
	 * Helper method to display menu
	 * @return the option that the user selected.
	 */
	private int displayMenu() {
		System.out.println("Enter a selection from the following menu:");
		System.out.println(
				DISPLAY_ROOM_INFORMATION + ". Display room information\n" +
						ENTER_ROOM_BOOKING + ". Enter a room booking\n" +
						DELETE_BOOKING +". Remove a room booking\n" + 
						CHANGE_BOOKING + ". Change a room booking\n" + 
						DISPLAY_BOOKING  + ". Display a booking\n" +
						DISPLAY_DAY_BOOKINGS + ". Display room bookings for the whole day\n" +
						SAVE_BOOKINGS_TO_FILE + ". Backup current bookings to file\n" +
						LOAD_BOOKINGS_FROM_FILE + ". Load current bookings from file\n" +
						EXIT + ". Exit program");
		int ch = scan.nextInt();
		scan.nextLine();  // 'eat' the next line in the buffer
		System.out.println(); // add a space before next menu output
		return ch;
	}
	
	/**
	 * Method that executed the Menu item base on user's seletion.
	 * @param choice - the choice input by user.
	 */
	private void executeMenuItem(int choice) {
		try {
			switch (choice) {
			case DISPLAY_ROOM_INFORMATION:
				displayRoomInfo();
				break;			    
			case ENTER_ROOM_BOOKING: 
				saveRoomBooking(makeBookingFromUserInput()); 
				break;
			case DELETE_BOOKING:  
				System.out.println("Enter booking to delete");
				System.out.println(	deleteBooking(makeCalendarFromUserInput(null, true))?
						"Booking deleted": "Booking not deleted");
				break;
			case CHANGE_BOOKING:			
				System.out.println("Enter booking to change");
				if (!changeBooking(makeCalendarFromUserInput(null, true)))
					System.out.println("Cannot change room booking");
				break;
			case DISPLAY_BOOKING: 
				displayBooking(makeCalendarFromUserInput(null, true));
				break;
			case DISPLAY_DAY_BOOKINGS: 
				displayDayBookings(makeCalendarFromUserInput(null, false)); 
				break;
			case SAVE_BOOKINGS_TO_FILE:
				System.out.println(saveBookingsToFile()?
						"Current room bookings backed up to file":
						"Could not backup room bookings to file");
				break;
			case LOAD_BOOKINGS_FROM_FILE:
				System.out.println(loadBookingsFromFile() != null?
						"Current room bookings loaded from file":
						"Room bookings could not be loaded from file");
				break;
			case EXIT: 
				System.out.println("Exiting Room Booking Application\n\n"); 
				break;
			default: System.out.println("Invalid choice: try again. (Select " + EXIT + " to exit.)\n");
			}
		} catch (BadRoomBookingException ex) {
			System.out.println(ex.getHeader() + ex.getMessage());
		}
		System.out.println();  // add blank line after each output
	}

	/**
	 * Method works as a Scanner, return input String.
	 * @param s - String that got entered in from System.
	 * @return String entered as a parameter.
	 */
	private static String getResponseTo(String s) {
		System.out.print(s);
		return(scan.nextLine());
	}

	/**
	 * Receive information entered by the user to create a Booking.
	 * @return RoomBooking objects that successfully create by the user. 
	 */
	private static RoomBooking makeBookingFromUserInput() { 
		String[] fullName ;
		try {
			fullName = getResponseTo("Enter Client Name (as FirstName LastName): ").split(" ");

			if((fullName[0].length() + fullName[1].length())>30)
				throw new BadRoomBookingException("Name length exceeded\n","The first or last name exceeds the 30 character maximum allowed.");
			for(String i: fullName) 
				if(!Pattern.matches("^[a-zA-Z\\s]*$", i))
					throw new BadRoomBookingException("Name contains illegal characters\n","A name cannot include characters other than alphabetic characters, the dash (-), the period (.), and the apostrophe(').");

		} catch (IndexOutOfBoundsException e) {
			throw new BadRoomBookingException("Missing value\n","Missing an input value");

		}
		String phoneNumber = getResponseTo("Phone Number (e.g. 613-555-1212): ");

		if(!Pattern.matches("^\\d{3}-\\d{3}-\\d{4}$",phoneNumber))
			throw new BadRoomBookingException("Bad telephone number\n","The telephone number must be a 10-digit number,separated by '-' in the form, e.g. 613-555-1212");

		String organization = getResponseTo("Organization (optional): ");

		String category = getResponseTo("Enter event category: ");
		String description = getResponseTo("Enter detailed description of event: ");

		if(category.isEmpty() || description.isEmpty())
			throw new BadRoomBookingException("Missing value\n","Missing an input value");
		if(category == null || description == null)
			throw new BadRoomBookingException("Null value entered\n","An attempt was made to pass a null value to a variable");

		Calendar startCal = makeCalendarFromUserInput(null, true);
		Calendar endCal = makeCalendarFromUserInput(startCal, true);

		if (startCal.get(Calendar.HOUR_OF_DAY) > endCal.get(Calendar.HOUR_OF_DAY)) 
			throw new BadRoomBookingException("Endtime precedes start time\n","The room booking start time must occur before the end time ofthe room booking");
		else if (startCal.get(Calendar.HOUR_OF_DAY) == endCal.get(Calendar.HOUR_OF_DAY)) 
			throw new BadRoomBookingException("Zero time room booking\n","Start and end time of the room booking are the same.  The minimum time for a room booking is one hour");


		ContactInfo contactInfo = new ContactInfo(fullName[0], fullName[1], phoneNumber, organization);
		Activity activity = new Activity(category, description);
		TimeBlock timeBlock = new TimeBlock(startCal, endCal);

		return (new RoomBooking(contactInfo, activity, timeBlock));
	}
	
	/**
	 * Create a Calendar object from the user's input
	 * @param initCal - initial Calendar Object, can be null
	 * @param requestHour - when true, request the Start time or End time from the user.
	 * @return Calendar object that is used for make Booking.
	 */
	private static Calendar makeCalendarFromUserInput(Calendar initCal, boolean requestHour) {
		Calendar cal = Calendar.getInstance(); cal.clear();
		String date = "";
		int hour = 0;	
		boolean needCal = (initCal==null);
		try {

			if (needCal) date = getResponseTo("Event Date (entered as DDMMYYYY): ");
			int day = needCal ? Integer.parseInt(date.substring(0,2)) : initCal.get(Calendar.DAY_OF_MONTH);
			int month = needCal ? Integer.parseInt(date.substring(2,4))-1 : initCal.get(Calendar.MONTH);
			int year = needCal ? Integer.parseInt(date.substring(4,8)) : initCal.get(Calendar.YEAR);

			if (requestHour) {				
				String time = getResponseTo((needCal?"Start":"End") +" Time: ");
				hour = processTimeString(time);
			}
			cal.setLenient(false);
			cal.set(year, month, day, hour, 0);
			cal.getTime();
		} catch (Exception e) {
			throw new BadRoomBookingException("Bad Calendar form\n","Bad calendar date was entered. The correct format is DDMMYYYY");
		}
		return (cal);

	}
	
	/**
	 * Process time input by user input.
	 * @param t - Time string entered by user.
	 * @return the processed time for the Calendar.
	 */
	private static int processTimeString(String t) {
		int hour = 0;
		t = t.trim();
		if (t.contains ("pm") || (t.contains("p.m."))) hour = Integer.parseInt(t.split(" ")[0]) + 12;
		if (t.contains("am") || t.contains("a.m.")) hour = Integer.parseInt(t.split(" ")[0]);
		if (t.contains(":")) hour = Integer.parseInt(t.split(":")[0]);
		return hour;
	}

	/**
	 * Print out the RoomBooking that the user wish to find and check for the overlap of the newly entered Booking.
	 * @param cal - Calendar object which contains the Start time that the user input.
	 * @return null if the Booking is not found.
	 */
	private RoomBooking findBooking(Calendar cal) {
		Collections.sort(getRoomBookings(), new SortRoomBookingsByCalendar());

		Calendar oneHourLater = Calendar.getInstance();
		oneHourLater.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)+1);
		TimeBlock findTB = new TimeBlock(cal, oneHourLater);
		int index = Collections.binarySearch(getRoomBookings(), new RoomBooking(null,null,new TimeBlock(cal,null)), new SortRoomBookingsByCalendar());
		if (index > -1) if (getRoomBookings().get(index).getTimeBlock().overlaps(findTB)) return getRoomBookings().get(index);		
		return null;
	}
	
	/**
	 * Display information if the Room object
	 */
	private void displayRoomInfo() {
		System.out.println(getRoom().toString());
	}
	
	/**
	 * Save the Booking into an RoomBooking type Arraylist.
	 * @param roomBooking - RoomBooking object that successfully create by the user.
	 * @return <code>true</code> if the Booking is saved successfully, <code>false</code> otherwise.
	 */
	private boolean saveRoomBooking(RoomBooking roomBooking) {	
		TimeBlock tb = roomBooking.getTimeBlock();  // Check this TimeBlock to see if already booked
		Calendar cal = (Calendar)tb.getStartTime().clone(); // use its Calendar
		int hour = cal.get(Calendar.HOUR_OF_DAY);//Get first hour of block
		for (; hour < tb.getEndTime().get(Calendar.HOUR_OF_DAY); hour++){ //Loop through each hour in TimeBlock
			cal.set(Calendar.HOUR_OF_DAY, hour); // set next hour
			if (findBooking(cal)!=null) {  // TimeBlock already booked at that hour, can't add appointment
				System.out.println("Cannot save booking; that time is already booked");
				return false;
			}	
		}  // else time slot still available; continue loop to next hour
		getRoomBookings().add(roomBooking);  
		System.out.println("Booking time and date saved.");  
		return true;
	}

	/**
	 * Display information of the RoomBooking
	 * @param cal - Calendar object which contains the Start time that the user input.
	 * @return - RoomBooking object that is found.
	 */
	private RoomBooking displayBooking(Calendar cal) {  
		RoomBooking booking = findBooking(cal);
		int hr = cal.get(Calendar.HOUR_OF_DAY);
		System.out.print((booking!=null) ?
				"---------------\n"+ booking.toString()+"---------------\n": 
					"No booking scheduled between "+ hr + ":00 and " + (hr + 1) + ":00\n"
				);
		return booking;
	}

	/**
	 * Delete a Booking from the Arraylist.
	 * @param cal - Calendar object which contains the Start time that the user input.
	 * @return - <code>true</code> if the Booking is successfully deleted, <code>false</code> otherwise.
	 */
	private boolean deleteBooking(Calendar cal)	{
		RoomBooking rb = displayBooking(cal);
		if(rb != null)
			if(getResponseTo("Press 'Y' to confirm deletion, " +
					"any other key to abort: ").toUpperCase().equals("Y")) { 
				getRoomBookings().remove(rb);
				return true;	
			}
		return false;	
	}

	/**
	 * Change a Booking from the Arraylist.
	 * @param cal - Calendar object which contains the Start time that the user input.
	 * @return - <code>true</code> if the Booking is successfully changed, <code>false</code> otherwise.
	 */
	private boolean changeBooking(Calendar cal) {
		RoomBooking rb = displayBooking(cal);  // existing RoomBooking
		if (rb == null) return false;
		Calendar startCal = makeCalendarFromUserInput(cal, false);
		startCal.set(Calendar.HOUR_OF_DAY,processTimeString(getResponseTo("Enter New Start Time: ")));
		Calendar endCal = makeCalendarFromUserInput(cal, false);
		endCal.set(Calendar.HOUR_OF_DAY,processTimeString(getResponseTo("Enter New End Time: ")));
		TimeBlock tb = new TimeBlock(startCal, endCal);  // new TimeBlock
		for (RoomBooking rbook: getRoomBookings())  // check this won't collide with existing TimeBlock
			if (!rbook.equals(rb) &&   // ignore rb already in ArrayList
					(rbook.getTimeBlock().overlaps(tb))) return false;
		System.out.println("Booking has been changed to:");
		rb.setTimeBlock(tb);
		return (displayBooking(startCal) != null);
	}

	/**
	 * Display the Schedule of a whole day.
	 * @param cal - Calendar object which contains the Date that the user input.
	 */
	private void displayDayBookings(Calendar cal) {
		Collections.sort(getRoomBookings(), new SortRoomBookingsByCalendar());

		for (int hrCtr = 8; hrCtr < 24; hrCtr++) {
			cal.set(Calendar.HOUR_OF_DAY, hrCtr);
			RoomBooking rb = displayBooking(cal);	
			if (rb !=null) hrCtr = rb.getTimeBlock().getEndTime().get(Calendar.HOUR_OF_DAY) - 1;
		}
	}
	
	/**
	 * Save all Bookings from Arraylist to File
	 * @return - <code>true</code> if the Booking is successfully saved, <code>false</code> otherwise.
	 */
	private boolean saveBookingsToFile(){
		try( FileOutputStream fos=new FileOutputStream("CurrentRoomBookings.book");
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				){
			for(RoomBooking rb:getRoomBookings()) oos.writeObject(rb);
			return true;
		}
		catch(IOException ex){return false;}
	}
	
	/**
	 * Load all Bookings from a File to the Arraylist.
	 * @return Arraylist that is created from the file loading process.
	 */
	private ArrayList<RoomBooking> loadBookingsFromFile(){
		ArrayList<RoomBooking> ar = new ArrayList<>();
		try( FileInputStream fis=new FileInputStream("CurrentRoomBookings.book");
				ObjectInputStream ois =new ObjectInputStream(fis);
				){
			while(true)
				ar.add((RoomBooking)(ois.readObject()));
		} 
		catch (EOFException ex) {return ar;}
		catch (IOException | ClassNotFoundException e){return null;} 
	}

	private ArrayList<RoomBooking> getRoomBookings() {return roomBookings;}

}
