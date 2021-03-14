package ca.mcgill.ecse321.repairsystem.dto;

public class CustomerDto {
	private Sring creditHash; 
	private String debitHash; 
	private String address;
	private List<Appointment> appointments;
	private List<Car> cars;
	private Calendar lastActive;

	public CustomerDto(String aName,  int id, String aPassword, int aPhone, String aEmail, Calendar lastDate, RepairSystem aRepairSystem, String credit, String debit, String add) {
		super(aName, id, aPassword, aPhone, aEmail, aRepairSystem);
		creditHash = credit;
		debitHash = debit;
		address = add;
		lastActive = lastDate;
	}
	
	public CustomerDto() {
	}
	
	public String getCreditHash() {
		return creditHash;
	}
	
	public String getDebitHash() {
		return debitHash;
	}
	public String getAddress() {
		return address;
	}
	  
	public List<Appointment> getAppointments() {
	    return appointments;
	}
	
	public List<Car> getCars() {
		return cars;
	}
	
	public Calendar getLastActive() {
		return lastActive;
	}

}
