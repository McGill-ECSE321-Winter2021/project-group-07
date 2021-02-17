/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

@Entity
public class Mechanic extends User
{

	public Mechanic(String aName, int aId, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem, Service... allCapabilities)
	{
	  super(aName, aId, aPassword, aPhone, aEmail, aRepairSystem);    
	}

  public String getName() {
	  //will invoke the parent class getName() method;
	  return super.getName();
  }
  
  @Id
  public int getID() {
	  return super.getId();
  }
  
  public void setID(int ID) {
	  super.setId(ID);
  }
  
  public String getPassword() {
	  return super.getPassword();
  }
  
  public void setPassword(String password) {
	  super.setPassword(password);
  }
  public int getPhone() {
	  return super.getPhone();
  }
  
  public void setPhone(int phone) {
	  super.setPhone(phone);
  }
  
  public String getEmail() {
	  return super.getEmail();
  }
  
  public void setEmail(String email) {
	  super.setEmail(email);
  }
  
  //Mechanic Associations
  private List<TimeSlot> workHours;
  
  @ManyToMany
  public List<TimeSlot> getWorkHours()
  {
    return this.workHours;
  }

 public void setWorkHours(List<TimeSlot> workHours) {
	 this.workHours = workHours;
 }
  
  
  private List<Service> capabilities;
  @ManyToMany
  public List<Service> getCapabilities()
  {
	  return this.capabilities;
  }

  public void setCapabilities(List<Service> service) {
	  this.capabilities = service;
  }
  
  private List<Appointment> appointments;
  
  @ManyToMany
  public List<Appointment> getAppointments()
  {
    List<Appointment> newAppointments = Collections.unmodifiableList(appointments);
    return newAppointments;
  }
  
  public void setAppointments(List<Appointment> appointment) {
	  this.appointments=appointment;
  }

 

 

 






}