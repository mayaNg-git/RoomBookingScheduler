package cst8284.asgmt3.roomScheduler;
	/**
	 * 
	 * Contact info contains the information of the customer of RoomBooking objects
	 * 
	 * @author Nguyen Ngoc Han Ny, based on code supplied by Prof. Dave Houtman
	 * @version 1.01
	 * @since 25-03-2020
	 *
	 */
public class ContactInfo implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName, lastName, phoneNumber, organization;
	
	/**
	 * 
	 * Initial constructor that does not require organization.
	 * 
	 * @param firstName - first name of the customer.
	 * @param lastName - last name of the customer.
	 * @param phoneNumber - phone number of the customer.
	 */
	public ContactInfo(String firstName, String lastName, String phoneNumber) {
		this(firstName, lastName, phoneNumber, "Algonquin College");
	}	
	
	/**
	 * 
	 * Initial constructor that require organization
	 * 
	 * @param firstName - first name of the customer.
	 * @param lastName - last name of the customer.
	 * @param phoneNumber - phone number of the customer.
	 * @param organization - name of the organization.
	 */
	public ContactInfo(String firstName, String lastName, String phoneNumber, String organization) {
		setFirstName(firstName); setLastName(lastName); 
		setPhoneNumber(phoneNumber); setOrganization(organization);
	}	
	/**
	 * Set the first name for Contact object, used on both constructors. 
	 * @param firstName - first name of the customer.
	 */
	public void setFirstName(String firstName) {this.firstName = firstName;}
	/**
	 * Return the first name of the customer so it may be used compare objects.
	 * @return String contains customer's first name.
	 */
	public String getFirstName() {return firstName;}
	
	/**
	 * Set the last name for Contact object, used on both constructors. 
	 * @param lastName - last name of the customer.
	 */
	public void setLastName(String lastName) {this.lastName = lastName;}
	/**
	 * Return the last name of the customer so it may be used compare objects.
	 * @return String contains customer's last name.
	 */
	public String getLastName() {return lastName;}
	
	/**
	 * Set the phone number for Contact object, used on both constructors. 
	 * @param phoneNumber - phone number of the customer.
	 */
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
	/**
	 * Return the phone number of the customer so it may be used compare objects.
	 * @return String contains customer's phone number.
	 */
	public String getPhoneNumber() {return phoneNumber;}

	/**
	 * Set the name for customer's organization for Contact object, used on initial constructor, optional
	 * @param organization - name of the organization.
	 */
	public void setOrganization(String organization) {this.organization = organization;}
	/**
	 * Return customer's organization name so it may be used compare objects.
	 * @return String contains customer's organization.
	 */
	public String getOrganization() {return organization;}
	
	@Override
	public String toString() {
		return "Contact Information: " +
			((getFirstName()!="")?(getFirstName() + " " + getLastName()):"") + "\n" +
			"Phone: " + getPhoneNumber() +  
			((getOrganization().equals(""))?"":("\n" +getOrganization() + "\n"));
	}
}