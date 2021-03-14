package ca.mcgill.ecse321.repairsystem.dto;

public class MechanicDto {

	private List<TimeSlot> timeSlots;
	private List<Service> services;
	private List<Appointment> appointments;

	public MechanicDto(String aName, int id, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem, List<Service> allCapabilities) {
		super(aName, id, aPassword, aPhone, aEmail, aRepairSystem);    
	}
	
	public MechanicDto() {
	}
	
	public List<TimeSlot> getTimeSlots() {
		return timeSlots;
	}
	
	public List<Service> getServices() {
		return services;
	}
	
	public List<Appointment> getAppointments() {
	    return appointments;
	}
}
