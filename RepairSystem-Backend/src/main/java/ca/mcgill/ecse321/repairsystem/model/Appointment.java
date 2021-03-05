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

  public Appointment(Customer customer, int id, TimeSlot time, List<Mechanic> mechanics, Car car, List<Image> image, List<Service> services, String note, AppointmentStatus status) {
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
  
  public Appointment() {
  }
  
  public enum AppointmentStatus { CarReceived, InRepair, Completed };

  private int Id;
  
  @Id
  public int getId() {
	  return this.Id;
  }
  
  public void setId(int aId) {
	  this.Id = aId;
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

  private TimeSlot timeSlot;
  
  @ManyToOne
  public TimeSlot getTimeSlot()
  {
    return timeSlot;
  }
  
  public void setTimeSlot(TimeSlot appTime){
	  timeSlot = appTime;
  }
  
  private List<Mechanic> mechanics;

  @ManyToMany
  public List<Mechanic> getMechanics()
  {
    return mechanics;
  }
  
  public void setMechanics(List<Mechanic> mechanicList) {
    mechanics = mechanicList;
  }
  
  private Customer customer;
 
  @ManyToOne()
  public Customer getCustomer() {
    return customer;
  }
  
  public void setCustomer(Customer c) {
    customer = c;
  }
  
  private List<Service> services;
  
  @ManyToMany()
  public List<Service> getServices() {
    return services;
  }
  
  public void setServices(List<Service> serviceList){
    services = serviceList;
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