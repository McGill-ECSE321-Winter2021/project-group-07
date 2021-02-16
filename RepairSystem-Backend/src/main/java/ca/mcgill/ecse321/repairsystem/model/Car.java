package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;

// line 75 "model.ump"
// line 139 "model.ump"
public class Car
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum CarType { Sedan, Coupe, Sports, Hatchback, MiniVan, StationWagon, Convertible, Truck, SUV, Other }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Car Attributes
  private CarType type;
  private boolean winterTires;
  private int numOfKilometers;

  //Car Associations
  private Customer customer;
  private List<Appointment> appointments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Car(CarType aType, boolean aWinterTires, int aNumOfKilometers, Customer aCustomer)
  {
    type = aType;
    winterTires = aWinterTires;
    numOfKilometers = aNumOfKilometers;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create car due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    appointments = new ArrayList<Appointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(CarType aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public boolean setWinterTires(boolean aWinterTires)
  {
    boolean wasSet = false;
    winterTires = aWinterTires;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumOfKilometers(int aNumOfKilometers)
  {
    boolean wasSet = false;
    numOfKilometers = aNumOfKilometers;
    wasSet = true;
    return wasSet;
  }

  public CarType getType()
  {
    return type;
  }

  public boolean getWinterTires()
  {
    return winterTires;
  }

  public int getNumOfKilometers()
  {
    return numOfKilometers;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetMany */
  public Appointment getAppointment(int index)
  {
    Appointment aAppointment = appointments.get(index);
    return aAppointment;
  }

  public List<Appointment> getAppointments()
  {
    List<Appointment> newAppointments = Collections.unmodifiableList(appointments);
    return newAppointments;
  }

  public int numberOfAppointments()
  {
    int number = appointments.size();
    return number;
  }

  public boolean hasAppointments()
  {
    boolean has = appointments.size() > 0;
    return has;
  }

  public int indexOfAppointment(Appointment aAppointment)
  {
    int index = appointments.indexOf(aAppointment);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeCar(this);
    }
    customer.addCar(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Appointment addAppointment(String aNote, Appointment.AppointmentStatus aStatus, TimeSlot aAppointmentTime, Customer aCustomer, RepairSystem aRepairSystem, Mechanic[] allMechanics, Service[] allRequireServices)
  {
    return new Appointment(aNote, aStatus, aAppointmentTime, aCustomer, aRepairSystem, this, allMechanics, allRequireServices);
  }

  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    Car existingCar = aAppointment.getCar();
    boolean isNewCar = existingCar != null && !this.equals(existingCar);
    if (isNewCar)
    {
      aAppointment.setCar(this);
    }
    else
    {
      appointments.add(aAppointment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAppointment(Appointment aAppointment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAppointment, as it must always have a car
    if (!this.equals(aAppointment.getCar()))
    {
      appointments.remove(aAppointment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAppointmentAt(Appointment aAppointment, int index)
  {  
    boolean wasAdded = false;
    if(addAppointment(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAppointmentAt(Appointment aAppointment, int index)
  {
    boolean wasAdded = false;
    if(appointments.contains(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAppointmentAt(aAppointment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeCar(this);
    }
    for(int i=appointments.size(); i > 0; i--)
    {
      Appointment aAppointment = appointments.get(i - 1);
      aAppointment.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "winterTires" + ":" + getWinterTires()+ "," +
            "numOfKilometers" + ":" + getNumOfKilometers()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}