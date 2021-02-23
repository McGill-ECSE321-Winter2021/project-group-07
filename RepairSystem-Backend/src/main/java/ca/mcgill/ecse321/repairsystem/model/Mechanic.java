package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;


@Entity
public class Mechanic extends Person{


	public Mechanic(String aName, int id, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem, Service... allCapabilities)
	{
	  super(aName, id, aPassword, aPhone, aEmail, aRepairSystem);    
	}
	
	public Mechanic() {
		
	}

	
	private int id;
	@Id
	public int getId()
	{
		return id;
	}

	public void setId(int aId)
	{
		id = aId;

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
  
    return this.appointments;
  }
  
  public void setAppointments(List<Appointment> appointment) {
	  this.appointments=appointment;
  }

 

 

 






}