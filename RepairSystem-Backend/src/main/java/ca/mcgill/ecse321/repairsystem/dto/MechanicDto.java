package ca.mcgill.ecse321.repairsystem.dto;

import java.util.*;

public class MechanicDto extends PersonDto{

	private List<TimeSlotDto> timeSlots;
	private List<ServiceDto> services;
	private List<AppointmentDto> appointments;

	public MechanicDto(String aName, int id, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem, List<Service> allCapabilities) {
		super(aName, id, aPassword, aPhone, aEmail, aRepairSystem);    
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
