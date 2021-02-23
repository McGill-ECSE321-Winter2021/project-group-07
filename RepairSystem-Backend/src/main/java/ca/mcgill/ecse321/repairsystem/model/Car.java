package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;

@Entity
public class Car
{

  public enum CarType { Sedan, Coupe, Sports, Hatchback, Minivan, StationWagon, Convertible, Truck, SUV, Other }

  private int CarId;
  
  @Id
  public int getCarId() {
	  return this.CarId;
  }
  
  public void setCarId(int aId) {
	  this.CarId = aId;
  }
   
  private CarType type;
  
  public CarType getType(){
    return type;
  }
  
  public void setType(CarType aType)
  {
    type = aType;
  }

  private boolean winterTires;
  
  public boolean getWinterTires(){
    return winterTires;
  }
  
  public void setWinterTires(boolean aWinterTires)
  {
    winterTires = aWinterTires;
  }
  
  private int numOfKilometers;
  
  public int getNumOfKilometers(){
    return numOfKilometers;
  }

  public void setNumOfKilometers(int aNumOfKilometers)
  {
    numOfKilometers = aNumOfKilometers;
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
  
  private List<Appointment> appointments;
  
  @OneToMany(cascade={CascadeType.ALL})
  public List<Appointment> getAppointments()
  {
    return appointments;
  }
  
  public void setAppointments(List<Appointment> appointmentList){
    appointments = appointmentList;
  }

}
  