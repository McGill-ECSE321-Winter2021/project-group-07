package ca.mcgill.ecse321.dto;

import java.util.*;

public class CustomerDto extends PersonDto{
	private String creditHash; 
	private String debitHash; 
	private String address;
	private List<AppointmentDto> appointments;
	private List<CarDto> cars;
	private Calendar lastActive;

	public CustomerDto(String aName,  int id, String aPassword, int aPhone, String aEmail, Calendar lastDate, String credit, String debit, String add) {
		super(aName, id, aPassword, aPhone, aEmail);
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
	  
	public List<AppointmentDto> getAppointments() {
	    return appointments;
	}
	
	public List<CarDto> getCars() {
		return cars;
	}
	
	public Calendar getLastActive() {
		return lastActive;
	}

}
