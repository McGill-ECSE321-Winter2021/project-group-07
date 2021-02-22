package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Appointment
{
	
  public enum AppointmentStatus { CarReceived, InRepair, Completed };

  private int appointmentId;
  
  @Id
  public int getAppointmentId() {
	  return this.appointmentId;
  }
  
  public void setAppointmentId(int aId) {
	  this.appointmentId = aId;
  }
  
  private String note;
  
  public String getNote()
  {
    return note;
  }
  
  public void setNote(String aNote)
  {
    note = aNote;
  }
  
  private AppointmentStatus status;
  
  public AppointmentStatus getStatus()
  {
    return status;
  }
  
  public void setStatus(AppointmentStatus aStatus)
  {
    status = aStatus;
  }

  private TimeSlot appointmentTime;
  
  @ManyToOne
  public TimeSlot getAppointmentTime()
  {
    return appointmentTime;
  }
  
  public void setAppointmentTime(TimeSlot appTime){
    appointmentTime = appTime;
  }
  
  private List<Mechanic> mechanics;

  @ManyToMany
  public List<Mechanic> getMechanics()
  {
    return mechanics;
  }
  
  public void setMechanics(List<Mechanic> mechanicList){
    mechanics = mechanicList;
  }
  
  private Customer customer;
 
  @ManyToOne()
  public Customer getCustomer()
  {
    return customer;
  }
  
  public void setCustomer(Customer c){
    customer = c;
  }
  
  private List<Service> requiredServices;
  
  @ManyToMany()
  public List<Service> getRequiredServices()
  {
    return requiredServices;
  }
  
  public void setRequiredServices(List<Service> serviceList){
    requiredServices = serviceList;
  }
  
  private List<Image> images;
  
  @OneToMany(cascade={CascadeType.ALL})
  public List<Image> getImages()
  {
    return images;
  }
  
  public void setImages(List<Image> imageList){
    images = imageList;
  }
  

  private Car car;
  
  @ManyToOne()
  public Car getCar()
  {
    return car;
  }
  
  public void setCar(Car c) {
    car = c;
  }
  
}