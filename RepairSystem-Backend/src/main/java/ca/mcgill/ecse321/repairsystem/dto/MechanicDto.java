package ca.mcgill.ecse321.repairsystem.dto;

import java.util.*;

public class MechanicDto extends PersonDto{

	private List<TimeSlotDto> timeSlots;
	private List<ServiceDto> services;
	private List<AppointmentDto> appointments;

	public MechanicDto(String aName, int id, String aPassword, int aPhone, String aEmail, List<ServiceDto> allCapabilities) {
		super(aName, id, aPassword, aPhone, aEmail);    
	}
	
	public MechanicDto() {
	}
	
	public List<TimeSlotDto> getTimeSlots() {
		return timeSlots;
	}
	
	public List<ServiceDto> getServices() {
		return services;
	}
	
	public List<AppointmentDto> getAppointments() {
	    return appointments;
	}
}
