package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;


@Entity
public class Mechanic extends Person{


	public Mechanic(String aName, int id, String aPassword, int aPhone, String aEmail, List<Service> allCapabilities)
	{
	  super(aName, id, aPassword, aPhone, aEmail);    
	}
	
	public Mechanic() {
	}

  //Mechanic Associations
  private List<TimeSlot> timeSlots;
  
  @ManyToMany
  public List<TimeSlot> getTimeSlots()
  {
    return this.timeSlots;
  }

 public void setTimeSlots(List<TimeSlot> workHours) {
	 this.timeSlots = workHours;
 }
  
  private List<Service> services;
  
  @ManyToMany
  public List<Service> getServices()
  {
	  return this.services;
  }

  public void setServices(List<Service> service) {
	  this.services = service;
  }
  
  private List<Appointment> appointments;
  
  @ManyToMany
  public List<Appointment> getAppointments()
  {
    return this.appointments;
  }
  
  public void setAppointments(List<Appointment> appointment) {
	  this.appointments=appointment;
  }
  
  public void addService(Service service) {
	  services.add(service);
  }
  
  public void removeService(Service service) {
	  services.remove(service);
  }
  
  public void addAppointment(Appointment appointment) {
	  appointments.add(appointment);
  }

}