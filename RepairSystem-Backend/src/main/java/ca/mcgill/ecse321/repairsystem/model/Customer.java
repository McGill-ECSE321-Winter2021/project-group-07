package ca.mcgill.ecse321.repairsystem.model;
import java.util.*;

// line 23 "model.ump"
// line 103 "model.ump"
public class Customer extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  private String creditHash;
  private String debitHash;
  private String address;

  //Customer Associations
  private List<Appointment> appointments;
  private List<Car> cars;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aName, int aId, String aPassword, int aPhone, String aEmail, RepairSystem aRepairSystem, String aCreditHash, String aDebitHash, String aAddress)
  {
    super(aName, aId, aPassword, aPhone, aEmail, aRepairSystem);
    creditHash = aCreditHash;
    debitHash = aDebitHash;
    address = aAddress;
    appointments = new ArrayList<Appointment>();
    cars = new ArrayList<Car>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCreditHash(String aCreditHash)
  {
    boolean wasSet = false;
    creditHash = aCreditHash;
    wasSet = true;
    return wasSet;
  }

  public boolean setDebitHash(String aDebitHash)
  {
    boolean wasSet = false;
    debitHash = aDebitHash;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public String getCreditHash()
  {
    return creditHash;
  }

  public String getDebitHash()
  {
    return debitHash;
  }

  public String getAddress()
  {
    return address;
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
  /* Code from template association_GetMany */
  public Car getCar(int index)
  {
    Car aCar = cars.get(index);
    return aCar;
  }

  public List<Car> getCars()
  {
    List<Car> newCars = Collections.unmodifiableList(cars);
    return newCars;
  }

  public int numberOfCars()
  {
    int number = cars.size();
    return number;
  }

  public boolean hasCars()
  {
    boolean has = cars.size() > 0;
    return has;
  }

  public int indexOfCar(Car aCar)
  {
    int index = cars.indexOf(aCar);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Appointment addAppointment(String aNote, Appointment.AppointmentStatus aStatus, TimeSlot aAppointmentTime, RepairSystem aRepairSystem, Car aCar, Mechanic[] allMechanics, Service[] allRequireServices)
  {
    return new Appointment(aNote, aStatus, aAppointmentTime, this, aRepairSystem, aCar, allMechanics, allRequireServices);
  }

  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    Customer existingCustomer = aAppointment.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aAppointment.setCustomer(this);
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
    //Unable to remove aAppointment, as it must always have a customer
    if (!this.equals(aAppointment.getCustomer()))
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCars()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Car addCar(Car.CarType aType, boolean aWinterTires, int aNumOfKilometers)
  {
    return new Car(aType, aWinterTires, aNumOfKilometers, this);
  }

  public boolean addCar(Car aCar)
  {
    boolean wasAdded = false;
    if (cars.contains(aCar)) { return false; }
    Customer existingCustomer = aCar.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aCar.setCustomer(this);
    }
    else
    {
      cars.add(aCar);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCar(Car aCar)
  {
    boolean wasRemoved = false;
    //Unable to remove aCar, as it must always have a customer
    if (!this.equals(aCar.getCustomer()))
    {
      cars.remove(aCar);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCarAt(Car aCar, int index)
  {  
    boolean wasAdded = false;
    if(addCar(aCar))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCars()) { index = numberOfCars() - 1; }
      cars.remove(aCar);
      cars.add(index, aCar);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCarAt(Car aCar, int index)
  {
    boolean wasAdded = false;
    if(cars.contains(aCar))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCars()) { index = numberOfCars() - 1; }
      cars.remove(aCar);
      cars.add(index, aCar);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCarAt(aCar, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=appointments.size(); i > 0; i--)
    {
      Appointment aAppointment = appointments.get(i - 1);
      aAppointment.delete();
    }
    for(int i=cars.size(); i > 0; i--)
    {
      Car aCar = cars.get(i - 1);
      aCar.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "creditHash" + ":" + getCreditHash()+ "," +
            "debitHash" + ":" + getDebitHash()+ "," +
            "address" + ":" + getAddress()+ "]";
  }
}