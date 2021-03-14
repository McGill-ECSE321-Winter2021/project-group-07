package ca.mcgill.ecse321.repairsystem.dto;

public class CarDto {
	private int carId;
	private CarType carType;
	private Customer customer;
	private boolean winterTires;
	private int numOfKilometers;
	private List<Appointment> appointments;
	private CarType carType;

	public CarDto(int id, CarType type, boolean winterTires, int numOfKm, List<Appointment> appointments, Customer customer) {
		carId = id;
		this.carType = type;
		this.winterTires = winterTires;
		numOfKilometers = numOfKm;
		this.appointments = appointments;
		this.customer = customer;
	}
		  
	public CarDto() {
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public int getId() {
		return carId;
	}
	
	public CarType getCarType(){
		return carType;
	}
	  
	public boolean getWinterTires(){
		return winterTires;
	}
	  
	  
	public int getNumOfKilometers(){
	    return numOfKilometers;
	}
	  
	public Customer getCustomer() {
	    return customer;
	}
	  
	public List<Appointment> getAppointments() {
	    return appointments;
	}
}
