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
  
  public Car(int id, CarType type, boolean winterTires, int numOfKm, Customer customer) {
	 carId = id;
	 this.carType = type;
	 this.winterTires = winterTires;
	 numOfKilometers = numOfKm;
	 this.appointments = new ArrayList<Appointment>();
	 this.customer = customer;
  }
  
  public Car() {
	  
  }
	
  public enum CarType { Sedan, Coupe, Sports, Hatchback, Minivan, StationWagon, Convertible, Truck, SUV, Other }

  private int carId;
  
  @Id
  public int getId() {
	  return this.carId;
  }
  
  public void setId(int aId) {
	  this.carId = aId;
  }
   
  private CarType carType;
  
  public CarType getCarType(){
    return carType;
  }
  
  public void setCarType(CarType aType)
  {
    carType = aType;
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
  
  public void addAppointment(Appointment appointment) {
	  appointments.add(appointment);
  }

}
  