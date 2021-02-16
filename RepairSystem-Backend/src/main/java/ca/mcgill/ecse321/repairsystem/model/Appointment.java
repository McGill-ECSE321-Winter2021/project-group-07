/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/


import java.util.*;

// line 44 "model.ump"
// line 118 "model.ump"
public class Appointment
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum AppointmentStatus { CarReceived, InRepair, Completed }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointment Attributes
  private String note;
  private AppointmentStatus status;

  //Appointment Associations
  private TimeSlot appointmentTime;
  private List<Mechanic> mechanics;
  private Customer customer;
  private List<Service> requireServices;
  private List<Image> images;
  private RepairSystem repairSystem;
  private Car car;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Appointment(String aNote, AppointmentStatus aStatus, TimeSlot aAppointmentTime, Customer aCustomer, RepairSystem aRepairSystem, Car aCar, Mechanic[] allMechanics, Service[] allRequireServices)
  {
    note = aNote;
    status = aStatus;
    boolean didAddAppointmentTime = setAppointmentTime(aAppointmentTime);
    if (!didAddAppointmentTime)
    {
      throw new RuntimeException("Unable to create appointment due to appointmentTime. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    mechanics = new ArrayList<Mechanic>();
    boolean didAddMechanics = setMechanics(allMechanics);
    if (!didAddMechanics)
    {
      throw new RuntimeException("Unable to create Appointment, must have at least 1 mechanics. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create appointment due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    requireServices = new ArrayList<Service>();
    boolean didAddRequireServices = setRequireServices(allRequireServices);
    if (!didAddRequireServices)
    {
      throw new RuntimeException("Unable to create Appointment, must have at least 1 requireServices. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    images = new ArrayList<Image>();
    boolean didAddRepairSystem = setRepairSystem(aRepairSystem);
    if (!didAddRepairSystem)
    {
      throw new RuntimeException("Unable to create appointment due to repairSystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCar = setCar(aCar);
    if (!didAddCar)
    {
      throw new RuntimeException("Unable to create appointment due to car. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNote(String aNote)
  {
    boolean wasSet = false;
    note = aNote;
    wasSet = true;
    return wasSet;
  }

  public boolean setStatus(AppointmentStatus aStatus)
  {
    boolean wasSet = false;
    status = aStatus;
    wasSet = true;
    return wasSet;
  }

  public String getNote()
  {
    return note;
  }

  public AppointmentStatus getStatus()
  {
    return status;
  }
  /* Code from template association_GetOne */
  public TimeSlot getAppointmentTime()
  {
    return appointmentTime;
  }
  /* Code from template association_GetMany */
  public Mechanic getMechanic(int index)
  {
    Mechanic aMechanic = mechanics.get(index);
    return aMechanic;
  }

  public List<Mechanic> getMechanics()
  {
    List<Mechanic> newMechanics = Collections.unmodifiableList(mechanics);
    return newMechanics;
  }

  public int numberOfMechanics()
  {
    int number = mechanics.size();
    return number;
  }

  public boolean hasMechanics()
  {
    boolean has = mechanics.size() > 0;
    return has;
  }

  public int indexOfMechanic(Mechanic aMechanic)
  {
    int index = mechanics.indexOf(aMechanic);
    return index;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetMany */
  public Service getRequireService(int index)
  {
    Service aRequireService = requireServices.get(index);
    return aRequireService;
  }

  public List<Service> getRequireServices()
  {
    List<Service> newRequireServices = Collections.unmodifiableList(requireServices);
    return newRequireServices;
  }

  public int numberOfRequireServices()
  {
    int number = requireServices.size();
    return number;
  }

  public boolean hasRequireServices()
  {
    boolean has = requireServices.size() > 0;
    return has;
  }

  public int indexOfRequireService(Service aRequireService)
  {
    int index = requireServices.indexOf(aRequireService);
    return index;
  }
  /* Code from template association_GetMany */
  public Image getImage(int index)
  {
    Image aImage = images.get(index);
    return aImage;
  }

  public List<Image> getImages()
  {
    List<Image> newImages = Collections.unmodifiableList(images);
    return newImages;
  }

  public int numberOfImages()
  {
    int number = images.size();
    return number;
  }

  public boolean hasImages()
  {
    boolean has = images.size() > 0;
    return has;
  }

  public int indexOfImage(Image aImage)
  {
    int index = images.indexOf(aImage);
    return index;
  }
  /* Code from template association_GetOne */
  public RepairSystem getRepairSystem()
  {
    return repairSystem;
  }
  /* Code from template association_GetOne */
  public Car getCar()
  {
    return car;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAppointmentTime(TimeSlot aAppointmentTime)
  {
    boolean wasSet = false;
    if (aAppointmentTime == null)
    {
      return wasSet;
    }

    TimeSlot existingAppointmentTime = appointmentTime;
    appointmentTime = aAppointmentTime;
    if (existingAppointmentTime != null && !existingAppointmentTime.equals(aAppointmentTime))
    {
      existingAppointmentTime.removeAppointment(this);
    }
    appointmentTime.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfMechanicsValid()
  {
    boolean isValid = numberOfMechanics() >= minimumNumberOfMechanics();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMechanics()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addMechanic(Mechanic aMechanic)
  {
    boolean wasAdded = false;
    if (mechanics.contains(aMechanic)) { return false; }
    mechanics.add(aMechanic);
    if (aMechanic.indexOfAppointment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMechanic.addAppointment(this);
      if (!wasAdded)
      {
        mechanics.remove(aMechanic);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeMechanic(Mechanic aMechanic)
  {
    boolean wasRemoved = false;
    if (!mechanics.contains(aMechanic))
    {
      return wasRemoved;
    }

    if (numberOfMechanics() <= minimumNumberOfMechanics())
    {
      return wasRemoved;
    }

    int oldIndex = mechanics.indexOf(aMechanic);
    mechanics.remove(oldIndex);
    if (aMechanic.indexOfAppointment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMechanic.removeAppointment(this);
      if (!wasRemoved)
      {
        mechanics.add(oldIndex,aMechanic);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setMechanics(Mechanic... newMechanics)
  {
    boolean wasSet = false;
    ArrayList<Mechanic> verifiedMechanics = new ArrayList<Mechanic>();
    for (Mechanic aMechanic : newMechanics)
    {
      if (verifiedMechanics.contains(aMechanic))
      {
        continue;
      }
      verifiedMechanics.add(aMechanic);
    }

    if (verifiedMechanics.size() != newMechanics.length || verifiedMechanics.size() < minimumNumberOfMechanics())
    {
      return wasSet;
    }

    ArrayList<Mechanic> oldMechanics = new ArrayList<Mechanic>(mechanics);
    mechanics.clear();
    for (Mechanic aNewMechanic : verifiedMechanics)
    {
      mechanics.add(aNewMechanic);
      if (oldMechanics.contains(aNewMechanic))
      {
        oldMechanics.remove(aNewMechanic);
      }
      else
      {
        aNewMechanic.addAppointment(this);
      }
    }

    for (Mechanic anOldMechanic : oldMechanics)
    {
      anOldMechanic.removeAppointment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMechanicAt(Mechanic aMechanic, int index)
  {  
    boolean wasAdded = false;
    if(addMechanic(aMechanic))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMechanics()) { index = numberOfMechanics() - 1; }
      mechanics.remove(aMechanic);
      mechanics.add(index, aMechanic);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMechanicAt(Mechanic aMechanic, int index)
  {
    boolean wasAdded = false;
    if(mechanics.contains(aMechanic))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMechanics()) { index = numberOfMechanics() - 1; }
      mechanics.remove(aMechanic);
      mechanics.add(index, aMechanic);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMechanicAt(aMechanic, index);
    }
    return wasAdded;
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
      existingCustomer.removeAppointment(this);
    }
    customer.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfRequireServicesValid()
  {
    boolean isValid = numberOfRequireServices() >= minimumNumberOfRequireServices();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRequireServices()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addRequireService(Service aRequireService)
  {
    boolean wasAdded = false;
    if (requireServices.contains(aRequireService)) { return false; }
    requireServices.add(aRequireService);
    if (aRequireService.indexOfAppointment(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aRequireService.addAppointment(this);
      if (!wasAdded)
      {
        requireServices.remove(aRequireService);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeRequireService(Service aRequireService)
  {
    boolean wasRemoved = false;
    if (!requireServices.contains(aRequireService))
    {
      return wasRemoved;
    }

    if (numberOfRequireServices() <= minimumNumberOfRequireServices())
    {
      return wasRemoved;
    }

    int oldIndex = requireServices.indexOf(aRequireService);
    requireServices.remove(oldIndex);
    if (aRequireService.indexOfAppointment(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aRequireService.removeAppointment(this);
      if (!wasRemoved)
      {
        requireServices.add(oldIndex,aRequireService);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setRequireServices(Service... newRequireServices)
  {
    boolean wasSet = false;
    ArrayList<Service> verifiedRequireServices = new ArrayList<Service>();
    for (Service aRequireService : newRequireServices)
    {
      if (verifiedRequireServices.contains(aRequireService))
      {
        continue;
      }
      verifiedRequireServices.add(aRequireService);
    }

    if (verifiedRequireServices.size() != newRequireServices.length || verifiedRequireServices.size() < minimumNumberOfRequireServices())
    {
      return wasSet;
    }

    ArrayList<Service> oldRequireServices = new ArrayList<Service>(requireServices);
    requireServices.clear();
    for (Service aNewRequireService : verifiedRequireServices)
    {
      requireServices.add(aNewRequireService);
      if (oldRequireServices.contains(aNewRequireService))
      {
        oldRequireServices.remove(aNewRequireService);
      }
      else
      {
        aNewRequireService.addAppointment(this);
      }
    }

    for (Service anOldRequireService : oldRequireServices)
    {
      anOldRequireService.removeAppointment(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRequireServiceAt(Service aRequireService, int index)
  {  
    boolean wasAdded = false;
    if(addRequireService(aRequireService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRequireServices()) { index = numberOfRequireServices() - 1; }
      requireServices.remove(aRequireService);
      requireServices.add(index, aRequireService);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRequireServiceAt(Service aRequireService, int index)
  {
    boolean wasAdded = false;
    if(requireServices.contains(aRequireService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRequireServices()) { index = numberOfRequireServices() - 1; }
      requireServices.remove(aRequireService);
      requireServices.add(index, aRequireService);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRequireServiceAt(aRequireService, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfImages()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Image addImage()
  {
    return new Image(this);
  }

  public boolean addImage(Image aImage)
  {
    boolean wasAdded = false;
    if (images.contains(aImage)) { return false; }
    Appointment existingAppointment = aImage.getAppointment();
    boolean isNewAppointment = existingAppointment != null && !this.equals(existingAppointment);
    if (isNewAppointment)
    {
      aImage.setAppointment(this);
    }
    else
    {
      images.add(aImage);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeImage(Image aImage)
  {
    boolean wasRemoved = false;
    //Unable to remove aImage, as it must always have a appointment
    if (!this.equals(aImage.getAppointment()))
    {
      images.remove(aImage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addImageAt(Image aImage, int index)
  {  
    boolean wasAdded = false;
    if(addImage(aImage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfImages()) { index = numberOfImages() - 1; }
      images.remove(aImage);
      images.add(index, aImage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveImageAt(Image aImage, int index)
  {
    boolean wasAdded = false;
    if(images.contains(aImage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfImages()) { index = numberOfImages() - 1; }
      images.remove(aImage);
      images.add(index, aImage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addImageAt(aImage, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setRepairSystem(RepairSystem aRepairSystem)
  {
    boolean wasSet = false;
    if (aRepairSystem == null)
    {
      return wasSet;
    }

    RepairSystem existingRepairSystem = repairSystem;
    repairSystem = aRepairSystem;
    if (existingRepairSystem != null && !existingRepairSystem.equals(aRepairSystem))
    {
      existingRepairSystem.removeAppointment(this);
    }
    repairSystem.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCar(Car aCar)
  {
    boolean wasSet = false;
    if (aCar == null)
    {
      return wasSet;
    }

    Car existingCar = car;
    car = aCar;
    if (existingCar != null && !existingCar.equals(aCar))
    {
      existingCar.removeAppointment(this);
    }
    car.addAppointment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    TimeSlot placeholderAppointmentTime = appointmentTime;
    this.appointmentTime = null;
    if(placeholderAppointmentTime != null)
    {
      placeholderAppointmentTime.removeAppointment(this);
    }
    ArrayList<Mechanic> copyOfMechanics = new ArrayList<Mechanic>(mechanics);
    mechanics.clear();
    for(Mechanic aMechanic : copyOfMechanics)
    {
      aMechanic.removeAppointment(this);
    }
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeAppointment(this);
    }
    ArrayList<Service> copyOfRequireServices = new ArrayList<Service>(requireServices);
    requireServices.clear();
    for(Service aRequireService : copyOfRequireServices)
    {
      aRequireService.removeAppointment(this);
    }
    while (images.size() > 0)
    {
      Image aImage = images.get(images.size() - 1);
      aImage.delete();
      images.remove(aImage);
    }
    
    RepairSystem placeholderRepairSystem = repairSystem;
    this.repairSystem = null;
    if(placeholderRepairSystem != null)
    {
      placeholderRepairSystem.removeAppointment(this);
    }
    Car placeholderCar = car;
    this.car = null;
    if(placeholderCar != null)
    {
      placeholderCar.removeAppointment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "note" + ":" + getNote()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "status" + "=" + (getStatus() != null ? !getStatus().equals(this)  ? getStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "appointmentTime = "+(getAppointmentTime()!=null?Integer.toHexString(System.identityHashCode(getAppointmentTime())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "repairSystem = "+(getRepairSystem()!=null?Integer.toHexString(System.identityHashCode(getRepairSystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "car = "+(getCar()!=null?Integer.toHexString(System.identityHashCode(getCar())):"null");
  }
}