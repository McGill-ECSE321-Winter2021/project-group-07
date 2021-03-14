package ca.mcgill.ecse321.dto;

public class AppointmentDto {
	private Customer customer;
	private int id;
	private AppointmentStatus status;
	private Note note;
	private TimeSlot timeslot;
	private List<Mechanic> mechanics;
	private Car car;
	private List<Image> images;
	private List<Service> services;
	
	public AppointmentDto(Customer customer, int id, TimeSlot time, List<Mechanic> mechanics, Car car, List<Image> images, List<Service> services, String note, AppointmentStatus status) {
		  this.customer = customer;
		  this.Id = id;
		  this.status = status;
		  this.note = note;
		  this.timeSlot = time;
		  this.mechanics = mechanics;
		  this.customer = customer;
		  this.images = images;
		  this.services = services;
		  this.car = car;
	}
	
	public AppointmentDto() {
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public int getId() {
		return id;
	}
	
	public AppointmentStatus getStatus() {
		return aPassword;
	}
	
	public Note getNote() {
		return aEmail;
	}

	public TimeSlot getTimeSlot() {
		return timeslot;
	}
	
	public List<Mechanic> getMechanics() {
		return mechanics;
	}
	
	public Car getCar() {
		return car;
	}
	
	public List<Image> getImages() {
		return images;
	}
	
	public List<Service> getServices() {
		return services;
	}
}
