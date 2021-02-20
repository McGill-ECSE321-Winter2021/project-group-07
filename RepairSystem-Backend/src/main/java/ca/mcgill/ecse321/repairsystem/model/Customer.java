package main.java.ca.mcgill.ecse321.repairsystem.model;
import java.util.*;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Entity;

@Entity
public class Customer extends User
{
  
  private String creditHash;
  
  public String getCreditHash() {
    return creditHash;
  }

  public void setCreditHash(String aCreditHash)
  {
    creditHash = aCreditHash;
  }
  
  private String debitHash;
  
  public String getDebitHash() {
    return debitHash;
  }

  public void setDebitHash(String aDebitHash)
  {
    debitHash = aDebitHash;
  }
  
  private String address;
  
  public String getAddress(){
    return address;
  }

  public void setAddress(String aAddress)
  {
    address = aAddress;
  }
  
  private List<Appointment> appointments;
 
  @OneToMany(cascade={CascadeType.ALL})
  public List<Appointment> getAppointments()
  {
    return appointments;
  }
  
  public void setAppointments(List<Appointment> appointmentList) {
    appointments = appointmentList;
  }

  private List<Car> cars;
  
  @OneToMany(cascade={CascadeType.ALL})
  public List<Car> getCars()
  {
    return cars;
  }
  
  public void setCars(List<Car> carList){
    cars = carList;
  }
  
}